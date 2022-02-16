package com.marco.myhotelbackend.models;

import java.io.Serializable;

public class BookingCustomerId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4365355313165232176L;
	private int bookingID;
	private int customerID;
	
	public BookingCustomerId() {
	}
	
	public BookingCustomerId(int bookingID, int customerID) {
		this.bookingID = bookingID;
		this.customerID = customerID;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}
