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

	@Override
	public List<ClientDTO> getAllClients() {

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
		if (conseillerOptional.isPresent() && conseillerOptional.get().getClients().size() + 1 <= 10) {
			Conseiller conseiller = conseillerOptional.get();
			client.setConseiller(conseiller);

			Client client2 = clientRepository.save(client);

			CompteCourant compteCourant = new CompteCourant("cc", codeGenerator.generateRandomCode(), 0,
					LocalDate.now());
			CompteEpargne compteEpargne = new CompteEpargne("ce", codeGenerator.generateRandomCode(), 0,
					LocalDate.now());

			compteCourant.setClient(client2);
			compteEpargne.setClient(client2);

			client2.setCompteCourant(compteCourant);
			client2.setCompteEpargne(compteEpargne);

			compteRepository.save(compteCourant);
			compteRepository.save(compteEpargne);

			return client2;
		} else {
			return null;
		}
	}

	@Override
	public Optional<ClientDTO> getClientById(Long id) {

		Client client = clientRepository.findById(id).orElse(null);

		ClientDTO clientDTO = this.ClientToDTO(client);

		return Optional.of(clientDTO);
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

	/**
	 * Méthode qui, à un objet de type clientDTO passé en paramètre, renvoie un
	 * objet de type client avec des parametres identiques
	 * 
	 * 
	 * @return Client
	 */
	Client DTOToClient(ClientDTO clientDTO) {

		Client c = new Client();

		c.setNom(clientDTO.getNom());
		c.setPrenom(clientDTO.getPrenom());
		c.setAdresse(clientDTO.getAdresse());
		c.setCodePostal(clientDTO.getCodePostal());
		c.setVille(clientDTO.getVille());
		c.setTel(clientDTO.getTel());

		c.setConseiller(conseillerRepository.findById(clientDTO.getConseiller_id()).get());
		return c;

	}

	/**
	 * Méthode qui, à un objet de type client passé en paramètre, renvoie un objet
	 * de type clientDTO avec des parametres identiques
	 * 
	 * 
	 * @return Client
	 */
	ClientDTO ClientToDTO(Client client) {

		ClientDTO c = new ClientDTO();

		c.setId(client.getId());
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setAdresse(client.getAdresse());
		c.setCodePostal(client.getCodePostal());
		c.setVille(client.getVille());
		c.setTel(client.getTel());
		c.setCompteCourant(client.getCompteCourant());
		c.setCompteEpargne(client.getCompteEpargne());

		c.setConseiller_id(client.getConseiller().getId());
		return c;

	}

	@Override
	public String deleteClientById(Long id) throws Exception {
		try {
			Client client = clientRepository.findById(id).orElse(null);
			String messageReponse = "";

			if (client.getCompteCourant().getSolde() == 0 && client.getCompteEpargne().getSolde() == 0) {
				clientRepository.deleteById(id);
				messageReponse = "Client supprimé.";
				return messageReponse;
			} else {
				messageReponse = "Les comptes du client doivent être à 0.";
				throw new Exception(messageReponse);
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
