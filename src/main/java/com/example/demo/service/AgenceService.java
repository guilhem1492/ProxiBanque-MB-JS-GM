package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Agence;

public interface AgenceService {

	Agence createAgence(String nom);

	Iterable<Agence> getAllAgences();

	Agence saveAgence(Agence agence);

	Optional<Agence> getAgenceById(Long id);

	void deleteAgenceById(Long id);

	Agence updateAgence(Agence agence);
}
