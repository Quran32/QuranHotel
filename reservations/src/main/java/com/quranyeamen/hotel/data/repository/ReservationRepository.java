package com.quranyeamen.hotel.data.repository;

import com.quranyeamen.hotel.data.entity.ReservationDE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationDE, Long> {
    List<ReservationDE> findByDate(Date date);
}