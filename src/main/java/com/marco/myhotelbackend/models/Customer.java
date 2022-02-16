package com.marco.myhotelbackend.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Customer {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="customerID")
	private int customerID;
	
	@Column(name="customerFirstName")
	private String customerFirstName;
	
	@Column(name="customerLastName")
	private String customerLastName;
	
	@Column(name="customerPhone")
	private String customerPhone;
	
	@Column(name="customerEmail")
	private String customerEmail;
	
	@Column(name="customerBirthDate")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate customerBirthDate;
	
	@Column(name="customerCity")
	private String customerCity;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public LocalDate getCustomerBirthDate() {
		return customerBirthDate;
	}

	public void setCustomerBirthDate(LocalDate customerBirthDate) {
		this.customerBirthDate = customerBirthDate;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

}
