package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Compte;

/**
 * CompteService.java Interface permettant l'implémentation des méthodes de CRUD
 * pour les objets de type compte
 * 
 * @author Mathieu B. Guilhem M. Julien S.
 * @since 09-12-2023
 */

public interface CompteService {

	Compte createCompte(String type, int solde);

	Iterable<Compte> getAllCompte();

	Compte saveCompte(Compte compte, Long id);

	Optional<Compte> getCompteById(Long id);

	void deleteCompteById(Long id);

	Compte updateCompte(Compte compte);

	/**
	 * Méthode qui, à un enregistrement de virement, effectue le virement en base de
	 * données ou non. Cela en fonction de la situation des comptes renseignés
	 * (comptes courants/épargnes), leurs soldes et leur propriétaire. Puis renvoie
	 * une chaine de caractères avec un message de validation ou d'erreur.
	 * 
	 * @param virementDTO
	 * @return String
	 * @throws VirementImpossibleException
	 * @see virementComptes
	 */
	public String virementComptes(VirementDTO virementDTO) throws VirementImpossibleException;
}
