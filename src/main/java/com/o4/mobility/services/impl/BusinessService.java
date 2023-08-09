package com.o4.mobility.services.impl;

import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dao.BookingRepository;
import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.BookingListRequest;
import com.o4.mobility.dtos.ListRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class BusinessService<T> {
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_RECORD_PER_PAGE = 20;

    public void validateId(Long id) {
        validateId(id, "A valid Id is required");
    }

    public void validateId(Long id, String message) {
        if (ValueUtils.isPositiveNumber(id)) {
            throw new BadInputException(message);
        }
    }

    public void validateString(String value) {
        if (ValueUtils.empty(value)) {
            throw new BadInputException("String value cannot be null or empty");
        }
    }

    public Pageable createPage(ListRequest request) {
        Integer currentPage = getPageNo(request);
        Integer recordPerPage = getRecordPerPage(request);

        return PageRequest.of(currentPage - 1, recordPerPage, Sort.by(Sort.Direction.DESC, "dtCreated"));
    }

    public Integer getPageNo(ListRequest request) {
        return ValueUtils.isPositiveNumber(request.getPageNo()) ? request.getPageNo() : DEFAULT_PAGE_NUMBER;
    }

    public Integer getRecordPerPage(ListRequest request) {
        return ValueUtils.isPositiveNumber(request.getRecordPerPage()) ? request.getRecordPerPage() : DEFAULT_RECORD_PER_PAGE;
    }
}
