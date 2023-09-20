package com.example.demo.service;

import java.util.Optional;
import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Conseiller;

/**
 * CompteService.java Interface permettant l'implémentation des méthodes de CRUD
 * pour les objets de type conseiller
 * 
 * @author Mathieu B. Guilhem M. Julien S.
 * @since 09-12-2023
 */

public interface ConseillerService {

	Iterable<Conseiller> getAllConseiller();

	Conseiller saveConseiller(Conseiller conseiller, Long id);

	Optional<Conseiller> getConseillerById(Long id);

	void deleteConseillerById(Long id);


	/**
	 * Méthode qui à un nom d'utilisateur et un mot de passe, va interroger la base
	 * de données. Puis retourner un conseiller si les mot de passe et nom
	 * d'utilisateur correspondent à une même entrée
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	ConseillerDTO getConseillerByLoginByPassword(String login, String password);

}
