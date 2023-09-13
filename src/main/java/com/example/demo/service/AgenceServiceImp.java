package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AgenceRepository;

import com.example.demo.model.Agence;

@Service
public class AgenceServiceImp implements AgenceService{

	private AgenceRepository agenceRepository;
	private RandomCodeGeneratorService randomCodeGeneratorService;

	public AgenceServiceImp(AgenceRepository agenceRepository, RandomCodeGeneratorService randomCodeGeneratorService) {
		this.agenceRepository = agenceRepository;
		this.randomCodeGeneratorService = randomCodeGeneratorService;
	}
	
	
	public Agence createAgence(String name) {
        String codeAlpha = randomCodeGeneratorService.generateRandomCode();
        LocalDate creationDate = LocalDate.now();
        return new Agence(name, codeAlpha, creationDate);
    }
	
	@Override
	public Iterable<Agence> getAllAgences() {
		// TODO Auto-generated method stub
		return agenceRepository.findAll();
	}

	@Override
	public Agence saveAgence(Agence agence) {
		Agence newAgence = createAgence(agence.getName());
		return agenceRepository.save(newAgence);
	}

	@Override
	public Optional<Agence> getAgenceById(Long id) {
		return agenceRepository.findById(id);
	}

	@Override
	public void deleteAgenceById(Long id) {
		agenceRepository.deleteById(id);
		
	}

	@Override
	public Agence updateAgence(Agence agence) {
		// TODO Auto-generated method stub
		return null;
	}

}
