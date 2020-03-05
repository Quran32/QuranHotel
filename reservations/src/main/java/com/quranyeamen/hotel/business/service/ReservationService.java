package com.quranyeamen.hotel.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quranyeamen.hotel.business.domain.RoomReservation;
import com.quranyeamen.hotel.data.entity.GuestDE;
import com.quranyeamen.hotel.data.entity.ReservationDE;
import com.quranyeamen.hotel.data.entity.RoomDE;
import com.quranyeamen.hotel.data.repository.GuestRepository;
import com.quranyeamen.hotel.data.repository.ReservationRepository;
import com.quranyeamen.hotel.data.repository.RoomRepository;

@Service
public class ReservationService {

	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;

	@Autowired
	public ReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository,
			RoomRepository roomRepository) {
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
	}

	public List<RoomReservation> getRoomReservationsForDate(Date date) {
		Iterable<RoomDE> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getRoomId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getRoomNumber());
			roomReservationMap.put(room.getRoomId(), roomReservation);
		});
		Iterable<ReservationDE> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
		if (null != reservations) {
			reservations.forEach(reservation -> {
				Optional<GuestDE> guestResponse = this.guestRepository.findById(reservation.getGuestId());
				if (guestResponse.isPresent()) {
					GuestDE guest = guestResponse.get();
					RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
					roomReservation.setDate(date);
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					roomReservation.setGuestId(guest.getId());
				}
			});

		}
		List <RoomReservation> roomReservations = new ArrayList<>();
		for(Long roomId:roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(roomId));
		
		}
		return roomReservations;
	}
}
