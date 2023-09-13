package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Agence;
import com.example.demo.service.AgenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agences")
public class AgenceController {

	@Autowired
	private AgenceService agenceService;

//	public AgenceController(AgenceService agenceService) {
//		this.agenceService = agenceService;
//	}

	@GetMapping
	Iterable<Agence> getAgences() {

		return agenceService.getAllAgences();
	}

	@PostMapping
	Agence postAgence(@Valid @RequestBody Agence a) {
		return agenceService.saveAgence(a);
	}

	@GetMapping("/{id}")
	Optional<Agence> getAgenceById(@PathVariable Long id) {
		return agenceService.getAgenceById(id);
	}

	@DeleteMapping("/{id}")
	void deleteAgence(@PathVariable Long id) {
		agenceService.deleteAgenceById(id);
	}
}
