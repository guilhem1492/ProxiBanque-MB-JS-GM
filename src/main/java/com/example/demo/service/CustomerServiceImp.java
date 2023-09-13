package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.ConseillerRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.model.Conseiller;
import com.example.demo.model.Customer;

import ch.qos.logback.core.net.server.Client;

@Service
public class CustomerServiceImp implements CustomerService {

	private CustomerRepository customerRepository;
	private ConseillerRepository conseillerRepository;

	public CustomerServiceImp(CustomerRepository customerRepository,ConseillerRepository conseillerRepository) {
		this.customerRepository = customerRepository;
		this.conseillerRepository = conseillerRepository;
	}

	@Override
	public Iterable<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return   customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer, Long id) {
		Optional<Conseiller> conseillerOptional = conseillerRepository.findById(id);
		if(conseillerOptional.isPresent()) {
			Conseiller conseiller = conseillerOptional.get();
			customer.setConseiller(conseiller);
		}
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Client> getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
