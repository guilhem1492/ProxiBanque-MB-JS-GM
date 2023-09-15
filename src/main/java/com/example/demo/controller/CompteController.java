package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.service.VirementImpossibleException;

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

//	@PostMapping("/virement")
//	public void virement(@RequestBody VirementDTO virementDTO) throws virementException {
//		compteService.virementCompte(virementDTO);
//	}

	@PostMapping("/virement")
	public ResponseEntity<String> virement(@RequestBody VirementDTO virementDTO) throws VirementImpossibleException {
		String messageReponse = compteService.virementComptes(virementDTO);

		if (messageReponse == "Solde insuffisant."
				|| messageReponse == "Seuls les virements externes de comptes courants à comptes courants sont autorisés."
				|| messageReponse == "Le montant du virement doit être compris entre 1 et 10000 euros."
				|| messageReponse == "ERREUR. Les ID des deux comptes doivent être valides et différents.") {
			return new ResponseEntity<>(messageReponse, HttpStatus.BAD_REQUEST);
		} else {

			return ResponseEntity.ok(messageReponse);
		}
	}

}