package com.o4.mobility.dao;

import com.o4.mobility.common.dtos.BookingStatus;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dao.entities.booking.BookingEntity;
import com.o4.mobility.dtos.BookingListRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecification implements Specification<BookingEntity> {
    private final BookingListRequest request;

    public BookingSpecification(BookingListRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<BookingEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate finalPredicate = builder.conjunction(); // Initialize the finalPredicate
        if (request.getCustomerId() != null) {
            Predicate condition1 = builder.equal(root.get("customerId"), request.getCustomerId());
            finalPredicate = builder.and(finalPredicate, condition1);
        }

        if (!ValueUtils.empty(request.getStatus())) {
            Predicate condition2 = builder.equal(root.get("status"), BookingStatus.valueOf(request.getStatus()));
            finalPredicate = builder.and(finalPredicate, condition2);
        }

        return finalPredicate;
    }
}
