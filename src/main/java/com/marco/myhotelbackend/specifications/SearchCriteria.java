package com.marco.myhotelbackend.specifications;

import java.time.LocalDate;

public class SearchCriteria {

	private String key;
	private String operation;
	private Object value;
	private LocalDate date;
	
	public SearchCriteria(String key, String operation, Object value) {
		
		this.key = key;
		this.operation = operation;
		this.value = value;
		
	}
	
	public SearchCriteria(String key, String operation, LocalDate date) {
		this.key = key;
		this.operation = operation;
		this.date = date;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
