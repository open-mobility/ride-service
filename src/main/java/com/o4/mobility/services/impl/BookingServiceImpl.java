package com.o4.mobility.services.impl;

import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.exceptions.RecordNotFoundException;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dao.BookingRepository;
import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.services.BookingService;
import com.o4.mobility.services.mappers.BookingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = BookingMapper.map(booking);
        entity.setBookingId(null);
        repository.save(entity);

        return BookingMapper.map(entity);
    }

    @Override
    public Booking update(Booking booking) {
        BookingEntity entity = fetchBooking(booking.getBookingId());
        BookingMapper.mapTo(entity, booking);

        repository.save(entity);

        return BookingMapper.map(entity);
    }

    @Override
    public Booking findById(Long bookingId) {
        if (!ValueUtils.isId(bookingId)) {
            throw new BadInputException("Booking id is missing or not valid positive integer");
        }

        BookingEntity entity = fetchBooking(bookingId);

        return BookingMapper.map(entity);
    }

    private BookingEntity fetchBooking(Long bookingId) {
        return repository.findById(bookingId)
                .orElseThrow(RecordNotFoundException::new);
    }


    @Override
    public List<Booking> list() {
        return null;
    }
}
