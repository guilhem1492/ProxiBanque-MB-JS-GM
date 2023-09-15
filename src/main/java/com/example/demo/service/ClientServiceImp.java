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
		
		Client client = clientRepository.findById(id).orElse(null);
		
		ClientDTO clientDTO = this.ClientToDTO(client);
		
		
		
		return Optional.of(clientDTO);
	}

	@Override
	public void deleteClientById(Long id) {
		clientRepository.deleteById(id);

	}

	@Override
	public ClientDTO updateClient(ClientDTO clientDTO) {
		
		Client existingClient = clientRepository.findById(clientDTO.getId()).get();
		
		existingClient.setNom(clientDTO.getNom());
		existingClient.setPrenom(clientDTO.getPrenom());
		existingClient.setTel(clientDTO.getTel());
		existingClient.setAdresse(clientDTO.getAdresse());
		existingClient.setCodePostal(clientDTO.getCodePostal());
		existingClient.setVille(clientDTO.getVille());
		
		Client updatedClient = clientRepository.save(existingClient);
		
		ClientDTO updatedClientDTO = this.ClientToDTO(updatedClient);
		
		return updatedClientDTO;
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
