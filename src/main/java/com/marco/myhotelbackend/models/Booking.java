package com.marco.myhotelbackend.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Booking {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="bookingID")
	private int bookingID;
	
	@Column(name="dateStart")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateStart;
	
	@Column(name="dateEnd")
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateEnd;
	
	@Column(name="roomID")
	private int roomID;
	
	@Column(name="dateArrive")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dateArrive;
	
	@Column(name="datePostponed")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime datePostponed;
	
	@Column(name="note")
	private String note;
	
	@Column(name="wasPaid")
	private Boolean wasPaid;
	
	@Column(name="finalPrice")
	private Double finalPrice;
	
	@Column(name="externalID")
	private String externalID;

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public LocalDateTime getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(LocalDateTime dateArrive) {
		this.dateArrive = dateArrive;
	}

	public LocalDateTime getDatePostponed() {
		return datePostponed;
	}

	public void setDatePostponed(LocalDateTime datePostponed) {
		this.datePostponed = datePostponed;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getWasPaid() {
		return wasPaid;
	}

	public void setWasPaid(Boolean wasPaid) {
		this.wasPaid = wasPaid;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getExternalID() {
		return externalID;
	}

	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}
	
}
