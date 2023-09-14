package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteRepository;

@Service
public class CompteServiceImp implements CompteService {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private RandomCodeGeneratorService randomCodeGeneratorService;

	@Autowired
	private ClientRepository clientRepository;

//	public CompteServiceImp(CompteRepository compteRepository, RandomCodeGeneratorService randomCodeGeneratorService,
//			ClientRepository clientRepository) {
//		this.compteRepository = compteRepository;
//		this.randomCodeGeneratorService = randomCodeGeneratorService;
//		this.clientRepository = clientRepository;
//	}

	public Compte createCompte(String type, int solde) {
		String numCompte = randomCodeGeneratorService.generateRandomCode();
		LocalDate creationDate = LocalDate.now();

		Compte compte;
		if ("cc".equalsIgnoreCase(type)) {
			compte = new CompteCourant(type, numCompte, solde, creationDate);
		} else if ("ce".equalsIgnoreCase(type)) {
			compte = new CompteEpargne(type, numCompte, solde, creationDate);
		} else {
			throw new IllegalArgumentException("Invalid type: " + type);
		}

		return compte;
	}

	@Override
	public Iterable<Compte> getAllCompte() {
		return compteRepository.findAll();
	}

	@Override
	public Compte saveCompte(Compte compte, Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();
			compte.setClient(client);
			if (compte instanceof CompteCourant) {
				client.setCompteCourant((CompteCourant) compte);
			} else if (compte instanceof CompteEpargne) {
				client.setCompteEpargne((CompteEpargne) compte);
			}
		}
		return compteRepository.save(compte);
	}

	@Override
	public Optional<Compte> getCompteById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteCompteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Compte updateCompte(Compte compte) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void virementCompte(VirementDTO virementDTO) {
//		Optional<Compte> optionalCompteSource = compteRepository.findById(virementDTO.idSource());
//		Optional<Compte> optionalCompteDestinataire = compteRepository.findById(virementDTO.idDestination());
//		if (optionalCompteSource.isPresent() && optionalCompteDestinataire.isPresent()) {
//			Compte compteSource = optionalCompteSource.get();
//			Compte compteDestinataire = optionalCompteDestinataire.get();
//			if (compteSource.getSolde() >= virementDTO.montant() && virementDTO.montant() > 0) {
//				compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
//				compteDestinataire.setSolde(compteDestinataire.getSolde() + virementDTO.montant());
//
//				compteRepository.save(compteSource);
//				compteRepository.save(compteDestinataire);
//			}
//		}
//		;
//
//	}

	public String virementCompte(VirementDTO virementDTO) throws SimpleException {
		String messageReponse;

		try {

			if (virementDTO.montant() > 0) {
				Optional<Compte> optionalCompteSource = compteRepository.findById(virementDTO.idSource());
				Optional<Compte> optionalCompteDestinataire = compteRepository.findById(virementDTO.idDestination());

				if (optionalCompteSource.isPresent() && optionalCompteDestinataire.isPresent()) {
					Compte compteSource = optionalCompteSource.get();
					Compte compteDestinataire = optionalCompteDestinataire.get();

					if (compteSource instanceof CompteCourant && compteDestinataire instanceof CompteCourant) {
						if (compteSource.getSolde() - virementDTO.montant() >= -1000) {
							compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
							compteDestinataire.setSolde(compteDestinataire.getSolde() + virementDTO.montant());

							compteRepository.save(compteSource);
							compteRepository.save(compteDestinataire);

						} else {
							messageReponse = "Solde insuffisant";
							throw new SimpleException(messageReponse);
						}

					} else {
						messageReponse = "Seuls les virements de comptes courants à comptes courants sont autorisés";
						throw new SimpleException(messageReponse);
					}

				}
				messageReponse = "Virement effectué avec succès !";
				return messageReponse;
			} else {
				messageReponse = "Le montant du virement doit être positif";
				throw new SimpleException(messageReponse);

			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
