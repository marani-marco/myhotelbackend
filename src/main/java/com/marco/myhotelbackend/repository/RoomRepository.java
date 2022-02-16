package com.marco.myhotelbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marco.myhotelbackend.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	@Query("SELECT r FROM Room r WHERE r.roomCapacity>=:people")
	public List<Room> roomsWithCapacityRequested(@Param("people") int people);
	
}
