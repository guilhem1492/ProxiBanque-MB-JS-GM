package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
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
	public List<ClientDTO> getAllClients() {
		// TODO Auto-generated method stub
		
		List<Client> clients = clientRepository.findAll();
		
		List<ClientDTO> clientsDTOs = new ArrayList<>();
		
		clients.forEach(client -> {
					ClientDTO dto = this.ClientToDTO(client);
					clientsDTOs.add(dto);
		});
				
		return clientsDTOs;
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
	public Optional<ClientDTO> getClientById(Long id) {
		// TODO Auto-generated method stub
		
		Client a = clientRepository.findById(id).orElse(null);
		
		ClientDTO b = this.ClientToDTO(a);
		
		
		
		return Optional.of(b);
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
	
	
	
Client DTOToClient(ClientDTO clientDTO) {
		
		Client a = new Client();
		
		
		a.setNom(clientDTO.getNom());
		a.setPrenom(clientDTO.getPrenom());
		a.setAdresse(clientDTO.getAdresse());
		a.setCodePostal(clientDTO.getCodePostal());
		a.setVille(clientDTO.getVille());
		a.setTel(clientDTO.getTel());
		
				
		a.setConseiller(conseillerRepository.findById(clientDTO.getConseiller_id()).get());
		return a;
		
	}
	
	ClientDTO ClientToDTO(Client client) {
		
		ClientDTO a = new ClientDTO();
		
		a.setId(client.getId());
		a.setNom(client.getNom());
		a.setPrenom(client.getPrenom());
		a.setAdresse(client.getAdresse());
		a.setCodePostal(client.getCodePostal());
		a.setVille(client.getVille());
		a.setTel(client.getTel());
		a.setCompteCourant(client.getCompteCourant());
		a.setCompteEpargne(client.getCompteEpargne());
				
		a.setConseiller_id(client.getConseiller().getId());
		return a;

		
	}

}
