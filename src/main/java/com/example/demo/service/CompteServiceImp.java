package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.CompteRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.dto.VirementDTO;
import com.example.demo.model.Compte;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.model.Customer;

@Service
public class CompteServiceImp implements CompteService {
	
	private CompteRepository compteRepository;
	private RandomCodeGeneratorService randomCodeGeneratorService;
	private CustomerRepository customerRepository;
	
	
	public CompteServiceImp(CompteRepository compteRepository,RandomCodeGeneratorService randomCodeGeneratorService,CustomerRepository customerRepository) {
		this.compteRepository = compteRepository;
		this.randomCodeGeneratorService = randomCodeGeneratorService;
		this.customerRepository =customerRepository;
	}
	
	public Compte createCompte(String type, int solde) {
		String numCompte = randomCodeGeneratorService.generateRandomCode();
        LocalDate creationDate = LocalDate.now();

        Compte compte;
        if ("cc".equalsIgnoreCase(type)) {
            compte = new CompteCourant(type, numCompte, solde, creationDate);
        } else if ("ce".equalsIgnoreCase(type)) {
            compte = new CompteEpargne(type, numCompte, solde, creationDate);
        } else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }

        return compte;
    }
	
	@Override
	public Iterable<Compte> getAllCompte() {
		return compteRepository.findAll();
	}

	@Override
	public Compte saveCompte(Compte compte, Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
           compte.setCustomer(customer);
           if (compte instanceof CompteCourant) {
               customer.setCompteCourant((CompteCourant) compte);
           } else if (compte instanceof CompteEpargne) {
               customer.setCompteEpargne((CompteEpargne) compte);
           }
        }
        return compteRepository.save(compte);
	}

	@Override
	public Optional<Compte> getCompteById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteCompteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte updateCompte(Compte compte) {
		// TODO Auto-generated method stub
		return null;
	}

	public void virementCompte(VirementDTO virementDTO) {
		Optional<Compte> optionalCompteSource = compteRepository.findById(virementDTO.idSource());
		Optional<Compte> optionalCompteDestinataire = compteRepository.findById(virementDTO.idDestination());
		if(optionalCompteSource.isPresent() && optionalCompteDestinataire.isPresent()) {
			Compte compteSource = optionalCompteSource.get();
			Compte compteDestinataire = optionalCompteDestinataire.get();
			if (compteSource.getSolde() >= virementDTO.montant() && virementDTO.montant() > 0) {
				compteSource.setSolde(compteSource.getSolde() - virementDTO.montant());
				compteDestinataire.setSolde(compteDestinataire.getSolde()+ virementDTO.montant());  
				
				compteRepository.save(compteSource);
				compteRepository.save(compteDestinataire);
	        } 
		};
		
		
	}
}
