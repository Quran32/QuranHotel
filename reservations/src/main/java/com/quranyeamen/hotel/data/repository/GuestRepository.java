package com.quranyeamen.hotel.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.quranyeamen.hotel.data.entity.GuestDE;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<GuestDE, Long> {

}