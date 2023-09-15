package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Compte;

public interface CompteService {

	Compte createCompte(String type, int solde);

	Iterable<Compte> getAllCompte();

	Compte saveCompte(Compte compte, Long id);

	Optional<Compte> getCompteById(Long id);

	void deleteCompteById(Long id);

	Compte updateCompte(Compte compte);

	public String virementComptes(VirementDTO virementDTO) throws VirementImpossibleException;
}
