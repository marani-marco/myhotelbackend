package com.marco.myhotelbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table
@IdClass(BookingCustomerId.class)
public class BookingCustomer {
	
	public BookingCustomer() {
	}

	
	public BookingCustomer(int bookingID, int customerID) {
		this.bookingID = bookingID;
		this.customerID = customerID;
	}

	@Id
	@Column(name="bookingID")
	private int bookingID;
	
	@Id
	@Column(name="customerID")
	private int customerID;

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
