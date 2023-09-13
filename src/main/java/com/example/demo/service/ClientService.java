package com.example.demo.service;


import com.example.demo.entity.Client;

public interface ClientService {

	Iterable<Client> getAllClients();

	Client saveClient(Client client, Long id);

	Client getClientById(Long id);

	void deleteClientById(Long id);

	Client updateClient(Client customer);
}
