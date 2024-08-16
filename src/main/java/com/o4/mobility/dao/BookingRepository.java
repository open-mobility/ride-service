
package com.o4.mobility.dao;

import com.o4.mobility.common.dtos.BookingStatus;
import com.o4.mobility.dao.entities.booking.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    List<BookingEntity> getAllByCustomerIdAndStatus(Long customerId, BookingStatus status);
}
