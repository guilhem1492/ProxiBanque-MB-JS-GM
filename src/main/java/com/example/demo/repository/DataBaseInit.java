/*

package com.example.demo.repository;

import java.time.LocalDate;


import org.springframework.stereotype.Component;

import com.example.demo.entity.Agence;
import com.example.demo.entity.Client;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.example.demo.entity.Conseiller;
import com.example.demo.service.AgenceService;

import jakarta.annotation.PostConstruct;

@Component
public class DataBaseInit {

	private ClientRepository clientRepository;
	private ConseillerRepository conseillerRepository;
	private AgenceRepository agenceRepository;
	private AgenceService agenceService;

	public DataBaseInit(ClientRepository customerRepository, ConseillerRepository conseillerRepository,
			AgenceRepository agenceRepository, AgenceService agenceService) {
		this.clientRepository = customerRepository;
		this.conseillerRepository = conseillerRepository;
		this.agenceRepository = agenceRepository;
		this.agenceService = agenceService;
	}

	@PostConstruct
	private void loadData() {
		Conseiller conseiller = new Conseiller("Jean Dupont");

		conseiller.setLogin("JD");
		conseiller.setPassword("1234");
		conseiller.setGradeGerant(false);

		Agence agence = agenceService.createAgence("Marie Dupond");

		Client client1 = new Client("Client 1");
		Client client2 = new Client("Client 2");

		client1.setConseiller(conseiller);
		client2.setConseiller(conseiller);

		conseiller.getClients().add(client1);
		conseiller.getClients().add(client2);

		conseiller.setAgence(agence);
		agence.getConseillers().add(conseiller);

		CompteCourant CompteC1 = new CompteCourant("cc", "0123", -6000, LocalDate.now());
		CompteCourant CompteC2 = new CompteCourant("cc", "0124", 6000, LocalDate.now());
		CompteEpargne CompteE1 = new CompteEpargne("ce", "0125", -6000, LocalDate.now());
		CompteEpargne CompteE2 = new CompteEpargne("ce", "0126", 6000, LocalDate.now());

		CompteC1.setClient(client1);
		CompteC2.setClient(client2);
		CompteE1.setClient(client1);
		CompteE2.setClient(client2);

		client1.setCompteCourant(CompteC1);
		client1.setCompteEpargne(CompteE1);

		client2.setCompteCourant(CompteC2);
		client2.setCompteEpargne(CompteE2);

		agenceRepository.save(agence);

	}
}
*/