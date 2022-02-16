package com.marco.myhotelbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marco.myhotelbackend.models.BookingCustomer;

@Repository
public interface BookingCustomerRepository extends JpaRepository<BookingCustomer, Integer>, JpaSpecificationExecutor<BookingCustomer> {

	@Query("SELECT bc.customerID FROM BookingCustomer bc WHERE bc.bookingID=:bookingid")
	public List<Integer> getCustomerIDsFromBookingID(@Param("bookingid") int bookingID);
	
}
