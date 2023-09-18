package com.example.demo.service;

/**
 * CompteService.java Interface permettant l'implementation de la génération
 * d'un numéro de compte unique lors de la création d'un compte
 * 
 * @author Mathieu B. Guilhem M. Julien S.
 * @since 09-12-2023
 */

public interface RandomCodeGeneratorService {

	/**
	 * Méthodes sans paramètres, qui renvoie une chaine de caractères, générées
	 * aléatoirement, pour l'identification des comptes.
	 * 
	 */
	String generateRandomCode();

}
