package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Conseiller;


public interface ConseillerService {

	Iterable<Conseiller> getAllConseiller();
	Conseiller saveConseiller(Conseiller customer,Long id);
	Optional<Conseiller> getConseillerById(Long id);
	void deleteConseillerById(Long id);
	Conseiller updateConseiller(Conseiller customer);
}
