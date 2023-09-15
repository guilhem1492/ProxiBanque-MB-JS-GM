package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;

public interface ClientService {

	Iterable<ClientDTO> getAllClients();

	Optional<ClientDTO> getClientById(Long id);

	Client saveClient(Client client, Long id);

	void deleteClientById(Long id);

	// String deleteClientById(Long id) throws Exception;

	ClientDTO updateClient(ClientDTO clientDTO);
}
