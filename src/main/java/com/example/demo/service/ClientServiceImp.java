package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ConseillerRepository conseillerRepository;
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private RandomCodeGeneratorService codeGenerator;

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
		
	
		Client client2 = clientRepository.save(client);
		
		
				
		CompteCourant compteCourant = new CompteCourant("cc", codeGenerator.generateRandomCode(), 0, LocalDate.now());
		CompteEpargne compteEpargne = new CompteEpargne("ce", codeGenerator.generateRandomCode(), 0, LocalDate.now());
		
		
		
		compteCourant.setClient(client2);
		compteEpargne.setClient(client2);
				
		
		client2.setCompteCourant(compteCourant);
		client2.setCompteEpargne(compteEpargne);
		
		compteRepository.save(compteCourant);
		compteRepository.save(compteEpargne);
		
		return client2;
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

//	@Override
//	public String deleteClientById(Long id) throws Exception {
//		try {
//			Client client = clientRepository.findById(id).orElse(null);
//			String messageReponse = "";
//
//			if (client.getCompteCourant().getSolde() == 0 && client.getCompteEpargne().getSolde() == 0) {
//				clientRepository.deleteById(id);
//				messageReponse = "Client supprimé.";
//				return messageReponse;
//			} else {
//				messageReponse = "Les comptes du client doivent être à 0.";
//				throw new Exception(messageReponse);
//			}
//
//		} catch (Exception e) {
//			return e.getMessage();
//		}
//
//	}

}
