package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;

/**
 * ClientService.java Interface permettant l'implémentation des méthodes de CRUD
 * pour les objets de type client
 * 
 * @author Mathieu B. Guilhem M. Julien S.
 * @since 09-12-2023
 */
public interface ClientService {

	Iterable<ClientDTO> getAllClients();

	Optional<ClientDTO> getClientById(Long id);

	Client saveClient(Client client, Long id);

	String deleteClientById(Long id) throws Exception;

	ClientDTO updateClient(ClientDTO clientDTO);

}
