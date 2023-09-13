package com.example.demo.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Customer;
import com.example.demo.service.ConseillerService;
import com.example.demo.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customers")
public class CustomertController {

	private final CustomerService customerService;
	
	public CustomertController(CustomerService customerService,ConseillerService conseillerService) {
		// TODO Auto-generated constructor stub
		this.customerService=customerService;
	}
	
	@GetMapping
	Iterable<Customer> getCustomers() {

		return customerService.getAllCustomers();
	}
	
	@PostMapping("/{id}")
	Customer postCustomer(@Valid @RequestBody Customer c, @PathVariable Long id) {

		return customerService.saveCustomer(c,id);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
	}
}
