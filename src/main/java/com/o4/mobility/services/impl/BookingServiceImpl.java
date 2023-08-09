package com.o4.mobility.services.impl;

import com.o4.mobility.common.dtos.BookingStatus;
import com.o4.mobility.common.dtos.BooleanResponse;
import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.exceptions.Errors;
import com.o4.mobility.common.exceptions.RecordNotFoundException;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dao.BookingRepository;
import com.o4.mobility.dao.BookingSpecification;
import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.*;
import com.o4.mobility.services.BookingService;
import com.o4.mobility.services.event.MobilityEventPublisher;
import com.o4.mobility.services.mappers.BookingMapper;
import com.o4.mobility.services.validators.BookingValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl extends BusinessService<BookingEntity> implements BookingService {

    private final MobilityEventPublisher publisher;
    private static final BookingStatus DEFAULT_BOOKING_STATUS = BookingStatus.PENDING;
    private final BookingRepository repository;
    private final BookingStateMachine stateMachine;

    public BookingServiceImpl(MobilityEventPublisher publisher, BookingRepository repository, BookingStateMachine stateMachine) {
        this.publisher = publisher;
        this.repository = repository;
        this.stateMachine = stateMachine;
    }

    @Override
    @Transactional
    public Booking save(BookingRequest booking) {
        BookingValidator.validate(booking);

        BookingEntity entity = BookingMapper.mapRequest(booking);
        entity.setBookingId(null);
        entity.setStatus(DEFAULT_BOOKING_STATUS);
        repository.save(entity);

        publisher.publishCustomEvent("||| A new booking created: "+ entity.getBookingId() +" ||||");

        return BookingMapper.map(entity);
    }

    @Override
    public Booking update(Long bookingId, BookingRequest booking) {
        BookingValidator.validate(bookingId, booking);

        BookingEntity entity = fetchBooking(bookingId);
        BookingMapper.overwrite(entity, booking);

        repository.save(entity);

        return BookingMapper.map(entity);
    }


    @Override
    public Booking findById(Long bookingId) {
        if (ValueUtils.isPositiveNumber(bookingId)) {
            throw new BadInputException("Booking id is missing or not valid positive integer");
        }

        log.info("Query database for booking {}", bookingId);
        BookingEntity entity = fetchBooking(bookingId);

        return BookingMapper.map(entity);
    }

    private BookingEntity fetchBooking(Long bookingId) {
        return repository.findById(bookingId)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public BooleanResponse deleteById(Long id) {
        repository.deleteById(id);

        return BooleanResponse.success();
    }

    @Override
    public Booking updateStatus(Long id, String status) {
        validateId(id);
        BookingEntity booking = fetchBooking(id);
        stateMachine.validateTransition(booking, status);
        booking.setStatus(BookingStatus.valueOf(status));
        repository.save(booking);

        return BookingMapper.map(booking);
    }

    @Override
    public BookingListResponse list(BookingListRequest request) {
        validateRequest(request);

        return search(request);
    }


    public BookingListResponse search(BookingListRequest request) {
        BookingSpecification specs = new BookingSpecification(request);
        Pageable pageable = createPage(request);
        Page<BookingEntity> resultPage = repository.findAll(specs, pageable);

        BookingListResponse response = new BookingListResponse();
        response.setRequest(request);
        response.setRecords(BookingMapper.map(resultPage.getContent()));
        response.setPageNo(resultPage.getNumber() + 1);
        response.setCurrentRecordsCount(resultPage.getNumberOfElements());
        response.setTotalRecords(repository.count(specs));
        response.setRecordPerPage(pageable.getPageSize());
        response.setTotalPages(resultPage.getTotalPages());

        return response;
    }

    private void validateRequest(BookingListRequest request) {
        if (null == request) {
            throw new BadInputException("Request body is required");
        }

        if (request.getStatus() != null && !BookingStatus.contains(request.getStatus())) {
            throw new BadInputException(Errors.BAD_ENUM_VALUE, "Invalid Booking status");
        }
    }
}
