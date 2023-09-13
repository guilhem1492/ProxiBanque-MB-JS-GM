package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Conseiller;
import com.example.demo.service.ConseillerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conseillers")
public class ConseillerController {

	@Autowired
	private ConseillerService conseillerService;

//	public ConseillerController(ConseillerService conseillerService) {
//		this.conseillerService = conseillerService;
//	}

	@GetMapping
	Iterable<Conseiller> getConseillers() {

		return conseillerService.getAllConseiller();
	}

	@PostMapping("/{id}")
	Conseiller postConseiller(@Valid @RequestBody Conseiller c, @PathVariable Long id) {
		return conseillerService.saveConseiller(c, id);
	}

	@DeleteMapping
	void deleteConseiller(@PathVariable Long id) {
		conseillerService.deleteConseillerById(id);
	}
}
