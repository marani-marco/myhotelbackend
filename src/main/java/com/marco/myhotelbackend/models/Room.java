package com.marco.myhotelbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Room {

	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int roomID;
	
	@Column
	private String roomName;
	
	@Column
	private int roomCapacity;
	
	@Column
	private double roomPricePerNight;
	
	@Column
	private Boolean roomOutOfService;
	
	@Column
	private String roomDescription;

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public double getRoomPricePerNight() {
		return roomPricePerNight;
	}

	public void setRoomPricePerNight(double roomPricePerNight) {
		this.roomPricePerNight = roomPricePerNight;
	}

	public Boolean getRoomOutOfService() {
		return roomOutOfService;
	}

	public void setRoomOutOfService(Boolean roomOutOfService) {
		this.roomOutOfService = roomOutOfService;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	
}
