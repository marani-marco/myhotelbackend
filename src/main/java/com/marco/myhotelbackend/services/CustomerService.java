package com.marco.myhotelbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marco.myhotelbackend.models.Customer;
import com.marco.myhotelbackend.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public void saveUpdateList(List<Customer> customers){
		
		customerRepository.saveAll(customers);
		
	}
	
	public void saveUpdate(Customer customer){
		
		customerRepository.save(customer);
		
	}
	
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}
	
	public Optional<Customer> getByID(int customerID) {
		
		return customerRepository.findById(customerID);
		
	}
	
	public List<Customer> getByIDs(List<Integer> customerIDs) {
		
		return customerRepository.findAllById(customerIDs);
		
	}
	
	
}
