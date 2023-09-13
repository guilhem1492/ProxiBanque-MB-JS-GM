package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.AgenceRepository;
import com.example.demo.Repository.ConseillerRepository;
import com.example.demo.model.Agence;
import com.example.demo.model.Conseiller;

@Service
public class ConseillerServiceimp implements ConseillerService {

	
	private ConseillerRepository conseillerRepository;
	private AgenceRepository agenceRepository;

	public ConseillerServiceimp(ConseillerRepository conseillerRepository,AgenceRepository agenceRepository) {
		this.conseillerRepository = conseillerRepository;
		this.agenceRepository =agenceRepository;
	}
	
	@Override
	public Iterable<Conseiller> getAllConseiller() {
		// TODO AuconseillerRepositoryto-generated method stub
		return conseillerRepository.findAll();
	}

	@Override
	public Conseiller saveConseiller(Conseiller conseiller, Long id) {
		Optional<Agence> agenceOptionnal = agenceRepository.findById(id);
		if (agenceOptionnal.isPresent()) {
			Agence agence = agenceOptionnal.get();
			conseiller.setAgence(agence);
		}

	    return conseillerRepository.save(conseiller);
	}

	@Override
	public Optional<Conseiller> getConseillerById(Long id) {
		// TODO Auto-generated method stub
		return conseillerRepository.findById(id);
	}

	@Override
	public void deleteConseillerById(Long id) {
		conseillerRepository.deleteById(id);
		
	}

	@Override
	public Conseiller updateConseiller(Conseiller customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
