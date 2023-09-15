package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

//	public ClientController(ClientService customerService,ConseillerService conseillerService) {
//		this.clientService=customerService;
//	}

	@GetMapping()
    Iterable<ClientDTO> getClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    Optional<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping()
    Client postClient(@Valid @RequestBody Client c) {

        return clientService.saveClient(c, 0L);
    }

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }

	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") final Long id, @RequestBody ClientDTO client) {
		client.setId(id);
		ClientDTO updatedClient = clientService.updateClient(client);
		return new ResponseEntity<>(updatedClient, HttpStatus.OK);
	}

}
