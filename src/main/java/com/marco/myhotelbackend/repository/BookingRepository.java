package com.marco.myhotelbackend.repository;

import org.springframework.stereotype.Repository;

import com.marco.myhotelbackend.models.Booking;
import com.marco.myhotelbackend.models.Room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

	@Query("SELECT b FROM Booking b WHERE b.externalID=:externalID")
	public Booking findByexternalId(@Param("externalID") String externalID);
}
