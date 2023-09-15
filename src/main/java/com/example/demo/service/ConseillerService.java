package com.example.demo.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Conseiller;

public interface ConseillerService {

	Iterable<Conseiller> getAllConseiller();

	Conseiller saveConseiller(Conseiller conseiller, Long id);

	Optional<Conseiller> getConseillerById(Long id);

	void deleteConseillerById(Long id);

	Conseiller updateConseiller(Conseiller conseiller);

	ConseillerDTO getConseillerByLoginByPassword(String login, String password);

}
