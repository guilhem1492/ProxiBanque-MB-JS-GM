package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customers")
public class ClientController {

	@Autowired
	private ClientService clientService;

//	public ClientController(ClientService customerService,ConseillerService conseillerService) {
//		this.clientService=customerService;
//	}

	@GetMapping
	Iterable<Client> getCustomers() {

		return clientService.getAllClients();
	}

	@GetMapping("/{id}")
	Client getClientById(@PathVariable Long id) {

		return clientService.getClientById(id);
	}
	
	@PostMapping("/{id}")
	Client postCustomer(@Valid @RequestBody Client c, @PathVariable Long id) {

		return clientService.saveClient(c, id);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable Long id) {
		clientService.deleteClientById(id);
	}
}
