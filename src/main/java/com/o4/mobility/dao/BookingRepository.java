
package com.o4.mobility.dao;

import com.o4.mobility.dao.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}
