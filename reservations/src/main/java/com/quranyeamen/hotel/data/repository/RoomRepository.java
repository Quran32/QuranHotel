package com.quranyeamen.hotel.data.repository;
import com.quranyeamen.hotel.data.entity.RoomDE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<RoomDE, Long> {

	
}
