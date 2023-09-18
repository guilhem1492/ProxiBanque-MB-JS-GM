package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Agence;

/**
 * AgenceService.java Interface permettant l'implémentation des méthodes de CRUD
 * pour les objets de type Agence
 * 
 * @author Mathieu B. Guilhem M. Julien S.
 * @since 09-12-2023
 */

public interface AgenceService {

	Agence createAgence(String nom);

	Iterable<Agence> getAllAgences();

	Agence saveAgence(Agence agence);

	Optional<Agence> getAgenceById(Long id);

	void deleteAgenceById(Long id);

	Agence updateAgence(Agence agence);
}
