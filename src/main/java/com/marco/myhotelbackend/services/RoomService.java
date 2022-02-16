package com.marco.myhotelbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.myhotelbackend.models.Room;
import com.marco.myhotelbackend.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;

	public List<Room> roomsWithCapacityRequested(int people) {
		
		return roomRepository.roomsWithCapacityRequested(people);
		
	}
	
	public Optional<Room> getRoomByID(int roomID) {
		
		return roomRepository.findById(roomID);
		
	}
	
}
