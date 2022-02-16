package com.marco.myhotelbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.myhotelbackend.models.BookingCustomer;
import com.marco.myhotelbackend.repository.BookingCustomerRepository;

@Service
public class BookingCustomerService {

	@Autowired
	BookingCustomerRepository bookingCustomerRepository;
	
	public void save(int bookingID, int customerID) {
		
		BookingCustomer bookingCustomer = new BookingCustomer(bookingID, customerID);
		bookingCustomerRepository.save(bookingCustomer);
		
	}
	
	public List<Integer> getCustomerIDsFromBookingID(int bookingID){
		
		return bookingCustomerRepository.getCustomerIDsFromBookingID(bookingID);
	}
	
}
