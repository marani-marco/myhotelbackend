package com.marco.myhotelbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.marco.myhotelbackend.models.Customer;
import com.marco.myhotelbackend.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	public void saveCustomersList(List<Customer> customers) {
		
		customerService.saveUpdateList(customers);
		
	}

}
