package com.marco.myhotelbackend.requestwrapper;

import java.util.List;

import com.marco.myhotelbackend.models.Booking;
import com.marco.myhotelbackend.models.Customer;

public class BookingCustomerRequestWrapper {
	
	private Booking booking;
	private List<Customer> customers;
	
	
	public BookingCustomerRequestWrapper() {
	}
	
	public BookingCustomerRequestWrapper(Booking booking, List<Customer> customers) {
		this.booking = booking;
		this.customers = customers;
	}
	
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
}
