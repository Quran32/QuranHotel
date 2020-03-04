package com.quranyeamen.hotel.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class RoomDE {

	@Id
	@Column(name = "ROOM_ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long roomId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ROOM_NUMBER")
	private String roomNumber;
	@Column(name = "BED_INFO")
	private String bedInfo;
	
	
}
