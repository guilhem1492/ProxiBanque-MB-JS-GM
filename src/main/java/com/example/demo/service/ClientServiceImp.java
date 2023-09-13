package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ConseillerRepository conseillerRepository;

//	public ClientServiceImp(ClientRepository clientRepository, ConseillerRepository conseillerRepository) {
//		this.clientRepository = clientRepository;
//		this.conseillerRepository = conseillerRepository;
//	}

	@Override
	public Iterable<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client saveClient(Client client, Long id) {
		Optional<Conseiller> conseillerOptional = conseillerRepository.findById(id);
		if (conseillerOptional.isPresent()) {
			Conseiller conseiller = conseillerOptional.get();
			client.setConseiller(conseiller);
		}
		return clientRepository.save(client);
	}

	@Override
	public Optional<Client> getClientById(Long id) {
		return Optional.empty();
	}

	@Override
	public void deleteClientById(Long id) {
		clientRepository.deleteById(id);

	}

	@Override
	public Client updateClient(Client client) {
		Client existingClient = clientRepository.findById(client.getId()).get();
		existingClient.setNom(client.getNom());
		existingClient.setPrenom(client.getPrenom());
		existingClient.setTel(client.getTel());
		existingClient.setAdresse(client.getAdresse());
		existingClient.setCodePostal(client.getCodePostal());
		existingClient.setVille(client.getVille());
		Client updatedClient = clientRepository.save(existingClient);
		return updatedClient;
	}

}
