package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Compte;
import com.example.demo.service.CompteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comptes")
public class CompteController {

	@Autowired
	private CompteService compteService;

//	public CompteController(CompteService compteService) {
//		this.compteService = compteService;
//	}

	@GetMapping
	Iterable<Compte> getComptes() {
		return compteService.getAllCompte();
	}

	@PostMapping("/{id}")
	Compte postCompte(@Valid @RequestBody Compte c, @PathVariable Long id) {
		Compte newCompte = compteService.createCompte(c.getType(), c.getSolde());
		return compteService.saveCompte(newCompte, id);
	}

	@PostMapping("/virement")
	public void virement(@RequestBody VirementDTO virementDTO) {
		compteService.virementCompte(virementDTO);
	}
}