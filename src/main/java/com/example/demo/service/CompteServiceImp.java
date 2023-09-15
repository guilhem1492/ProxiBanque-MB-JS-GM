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

	public String virementComptes(VirementDTO virementDTO) throws VirementImpossibleException {
		try {
			String messageReponse = "";

			if (virementDTO.montant() > 0 && virementDTO.montant() <= 10000) {

				Optional<Compte> optionalCompteSource = compteRepository.findById(virementDTO.idSource());
				Optional<Compte> optionalCompteDestinataire = compteRepository.findById(virementDTO.idDestination());

				if (optionalCompteSource.isPresent() && optionalCompteDestinataire.isPresent()
						&& optionalCompteSource.get().getId() != optionalCompteDestinataire.get().getId()) {
					Compte compteSource = optionalCompteSource.get();
					Compte compteDestinataire = optionalCompteDestinataire.get();

					if (compteSource instanceof CompteCourant && compteDestinataire instanceof CompteCourant) {
						return virementExterne(virementDTO, messageReponse, compteSource, compteDestinataire);
					} else if (compteSource.getClient() == compteDestinataire.getClient()) {
						return virementInterne(virementDTO, messageReponse, compteSource, compteDestinataire);
					} else {
						messageReponse = "Seuls les virements externes de comptes courants à comptes courants sont autorisés.";
						throw new VirementImpossibleException(messageReponse);
					}
				} else {
					messageReponse = "ERREUR. Les ID des deux comptes doivent être valides et différents.";
					throw new VirementImpossibleException(messageReponse);

				}

			} else {
				messageReponse = "Le montant du virement doit être compris entre 1 et 10000 euros.";
				throw new VirementImpossibleException(messageReponse);

			}

		} catch (VirementImpossibleException e) {
			return e.getMessage();
		}

	}

	public String virementExterne(VirementDTO virementDTO, String messageReponse, Compte compteSource,
			Compte compteDestinataire) throws VirementImpossibleException {

		if (compteSource.getSolde() - virementDTO.montant() >= -1000) {
			compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
			compteDestinataire.setSolde(compteDestinataire.getSolde() + virementDTO.montant());

			compteRepository.save(compteSource);
			compteRepository.save(compteDestinataire);

		} else {
			messageReponse = "Solde insuffisant.";
			throw new VirementImpossibleException(messageReponse);
		}

		messageReponse = "Virement effectué avec succès.";
		return messageReponse;
	}

	public String virementInterne(VirementDTO virementDTO, String messageReponse, Compte compteSource,
			Compte compteDestinataire) throws VirementImpossibleException {

		if (compteSource instanceof CompteCourant && compteDestinataire instanceof CompteEpargne) {
			if (compteSource.getSolde() - virementDTO.montant() >= -1000) {
				compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
				compteDestinataire.setSolde(compteDestinataire.getSolde() + virementDTO.montant());

				compteRepository.save(compteSource);
				compteRepository.save(compteDestinataire);

			} else {
				messageReponse = "Solde insuffisant.";
				throw new VirementImpossibleException(messageReponse);
			}

		} else if (compteSource instanceof CompteEpargne && compteDestinataire instanceof CompteCourant) {
			if (compteSource.getSolde() - virementDTO.montant() >= 0) {
				compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
				compteDestinataire.setSolde(compteDestinataire.getSolde() + virementDTO.montant());

				compteRepository.save(compteSource);
				compteRepository.save(compteDestinataire);

			} else {
				messageReponse = "Solde insuffisant.";
				throw new VirementImpossibleException(messageReponse);
			}

		}

		messageReponse = "Virement effectué avec succès.";
		return messageReponse;
	}

}
