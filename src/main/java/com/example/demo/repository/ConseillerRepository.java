package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {

	/**
	 * Méthode qui à un nom d'utilisateur et un mot de passe, va interroger la base
	 * de données. Puis retourner un conseiller si les mot de passe et nom
	 * d'utilisateur correspondent à une même entrée
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	Conseiller findByLoginAndPassword(String login, String password);

}
