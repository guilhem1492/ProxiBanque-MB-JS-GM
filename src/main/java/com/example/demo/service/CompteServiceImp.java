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
		
		return compteRepository.findById(id);
	}

	@Override
	public void deleteCompteById(Long id) {
		

	}

	@Override
	public Compte updateCompte(Compte compte) {
		
		return null;
	}

	/**
	 * Méthode qui, à un enregistrement de virement, effectue le virement en base de
	 * données ou non. Cela en fonction de la situation des comptes renseignés
	 * (comptes courants/épargnes), leurs soldes et leur propriétaire. Puis renvoie
	 * un message de validation ou d'erreur.
	 * 
	 * @param virementDTO
	 * @return String
	 * @throws VirementImpossibleException
	 * @see virementComptes
	 */
	public String virementComptes(VirementDTO virementDTO) throws VirementImpossibleException {
		try {
			String messageReponse = "";

			if (virementDTO.montant() >= 1 && virementDTO.montant() <= 10000) {

				Optional<Compte> optionalCompteSource = compteRepository.findById(virementDTO.idSource());
				Optional<Compte> optionalCompteDestinataire = compteRepository.findById(virementDTO.idDestination());

				if (optionalCompteSource.isPresent() && optionalCompteDestinataire.isPresent()
						&& !optionalCompteSource.get().getId().equals(optionalCompteDestinataire.get().getId())) {
					Compte compteSource = optionalCompteSource.get();
					Compte compteDestinataire = optionalCompteDestinataire.get();

					if (compteSource instanceof CompteCourant && compteDestinataire instanceof CompteCourant) {
						return virementExterne(virementDTO, messageReponse, compteSource, compteDestinataire);
					} else if (compteSource.getClient().equals(compteDestinataire.getClient())) {
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
				messageReponse = "Le montant du virement doit être un entier compris entre 1 et 10000 euros.";
				throw new VirementImpossibleException(messageReponse);

			}

		} catch (VirementImpossibleException e) {
			return e.getMessage();
		}

	}

	/**
	 * Méthode qui, à un enregistrement de virement d'un compte courant à un autre
	 * compte courant, effectue le virement en base de données ou non. Cela en
	 * fonction de leurs soldes. Puis renvoie un message de validation ou d'erreur.
	 * 
	 * @param virementDTO, messageResponse, compteSource, compteDestinataire
	 * @return String
	 * @throws VirementImpossibleException
	 * @see virementComptes
	 */
	public String virementExterne(VirementDTO virementDTO, String messageReponse, Compte compteSource,
			Compte compteDestinataire) throws VirementImpossibleException {

		return saveTransaction(virementDTO, messageReponse, compteSource, compteDestinataire, -1000);

	}

	/**
	 * Méthode qui, à un enregistrement de virement d'un compte courant à un compte
	 * épargne, effectue le virement en base de données ou non. Cela en fonction de
	 * leurs soldes. Puis renvoie un message de validation ou d'erreur.
	 * 
	 * @param virementDTO, messageResponse, compteSource, compteDestinataire
	 * @return String
	 * @throws VirementImpossibleException
	 * @see virementComptes
	 */
	public String virementInterne(VirementDTO virementDTO, String messageReponse, Compte compteSource,
			Compte compteDestinataire) throws VirementImpossibleException {

		if (compteSource instanceof CompteCourant && compteDestinataire instanceof CompteEpargne) {
			return saveTransaction(virementDTO, messageReponse, compteSource, compteDestinataire, -1000);
		} else {
			return saveTransaction(virementDTO, messageReponse, compteSource, compteDestinataire, 0);
		}

	}

	/**
	 * Méthode qui, à un enregistrement de virement d'un compte courant à un compte
	 * épargne, effectue le virement en base de données ou non. Cela en fonction de
	 * leurs soldes. Puis renvoie un message de validation ou d'erreur.
	 * 
	 * Celle-ci est appelé par les méthodes virementExterne et virementInterne
	 * 
	 * @param virementDTO, messageResponse, compteSource, compteDestinataire
	 * @return String
	 * @throws VirementImpossibleException
	 * @see virementComptes
	 */
	public String saveTransaction(VirementDTO virementDTO, String messageReponse, Compte compteSource,
			Compte compteDestinataire, int limiteSolde) throws VirementImpossibleException {
		if (compteSource.getSolde() - virementDTO.montant() >= limiteSolde) {
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

}
