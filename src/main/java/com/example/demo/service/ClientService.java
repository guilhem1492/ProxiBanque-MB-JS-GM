package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Client;

public interface ClientService {

	Iterable<Client> getAllClients();

	Client saveClient(Client client, Long id);

	Optional<Client> getClientById(Long id);

	void deleteClientById(Long id);

	Client updateClient(Client customer);
}
