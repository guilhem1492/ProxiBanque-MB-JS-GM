package com.example.demo.service;


import java.util.Optional;

import com.example.demo.model.Customer;

import ch.qos.logback.core.net.server.Client;



public interface CustomerService {

	Iterable<Customer> getAllCustomers();
	Customer saveCustomer(Customer customer, Long id);
	Optional<Client> getCustomerById(Long id);
	void deleteCustomerById(Long id);
	Customer updateCustomer(Customer customer);
}
