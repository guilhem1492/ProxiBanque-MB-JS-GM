package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Agence;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.repository.ConseillerRepository;

@Service
public class ConseillerServiceimp implements ConseillerService {

	@Autowired
	private ConseillerRepository conseillerRepository;

	@Autowired
	private AgenceRepository agenceRepository;

//	public ConseillerServiceimp(ConseillerRepository conseillerRepository,AgenceRepository agenceRepository) {
//		this.conseillerRepository = conseillerRepository;
//		this.agenceRepository =agenceRepository;
//	}

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
	public Conseiller updateConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConseillerDTO getConseillerByLoginByPassword(String login, String password) {
		Conseiller conseiller = conseillerRepository.findByLoginAndPassword(login, password);
		ConseillerDTO conseillerDTO = new ConseillerDTO();

		// Copier les propriétés de l'entité Conseiller dans l'objet ConseillerDTO
		conseillerDTO.setId(conseiller.getId());
		conseillerDTO.setNom(conseiller.getNom());
		conseillerDTO.setPrenom(conseiller.getPrenom());
		conseillerDTO.setGradeGerant(conseiller.getGradeGerant());
		conseillerDTO.setAgence(conseiller.getAgence());

		return conseillerDTO;
	}

}
