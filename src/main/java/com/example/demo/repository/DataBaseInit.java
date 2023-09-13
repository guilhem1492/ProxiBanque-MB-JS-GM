//package com.example.demo.Repository;
//
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import com.example.demo.model.Agence;
//import com.example.demo.model.Conseiller;
//import com.example.demo.model.Customer;
//import com.example.demo.service.AgenceService;
//import com.example.demo.service.ConseillerService;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class DataBaseInit {
//
//	private CustomerRepository customerRepository;
//	private ConseillerRepository conseillerRepository;
//	private AgenceRepository agenceRepository;
//	private AgenceService agenceService;
//
//	public DataBaseInit(CustomerRepository customerRepository, ConseillerRepository conseillerRepository,
//			AgenceRepository agenceRepository,AgenceService agenceService) {
//		this.customerRepository = customerRepository;
//		this.conseillerRepository = conseillerRepository;
//		this.agenceRepository = agenceRepository;
//		this.agenceService = agenceService;
//	}

//	@PostConstruct
//	private void loadData() {
//		Conseiller conseiller = new Conseiller("Jean Dupont");
//		Agence agence = agenceService.createAgence("Marie Dupond") ;
//
//		// Créer deux clients et les associer au conseiller
//		Customer client1 = new Customer("Client 1");
//		Customer client2 = new Customer("Client 2");
//
//		client1.setConseiller(conseiller);
//		client2.setConseiller(conseiller);
//
//		conseiller.getCustomers().add(client1);
//		conseiller.getCustomers().add(client2);
//
//		conseiller.setAgence(agence);
//		agence.getConseillers().add(conseiller);
//
//		// Sauvegarder le conseiller et ses clients
//		agenceRepository.save(agence);
//
//	}
//}
//package com;
