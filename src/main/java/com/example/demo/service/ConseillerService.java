package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Conseiller;

public interface ConseillerService {

	Iterable<Conseiller> getAllConseiller();

	Conseiller saveConseiller(Conseiller conseiller, Long id);

	Optional<Conseiller> getConseillerById(Long id);

	void deleteConseillerById(Long id);

	Conseiller updateConseiller(Conseiller conseiller);

	Conseiller getConseillerByLoginByPassword(String login, String password);

}
