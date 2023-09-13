/*
 * package com.example.demo.repository;
 * 
 * import org.springframework.stereotype.Component;
 * 
 * import com.example.demo.entity.Agence; import com.example.demo.entity.Client;
 * import com.example.demo.entity.Conseiller; import
 * com.example.demo.service.AgenceService;
 * 
 * import jakarta.annotation.PostConstruct;
 * 
 * @Component public class DataBaseInit {
 * 
 * private ClientRepository clientRepository; private ConseillerRepository
 * conseillerRepository; private AgenceRepository agenceRepository; private
 * AgenceService agenceService;
 * 
 * public DataBaseInit(ClientRepository customerRepository, ConseillerRepository
 * conseillerRepository, AgenceRepository agenceRepository,AgenceService
 * agenceService) { this.clientRepository = customerRepository;
 * this.conseillerRepository = conseillerRepository; this.agenceRepository =
 * agenceRepository; this.agenceService = agenceService; }
 * 
 * @PostConstruct private void loadData() { Conseiller conseiller = new
 * Conseiller("Jean Dupont");
 * 
 * Agence agence = agenceService.createAgence("Marie Dupond") ;
 * 
 * // Créer deux clients et les associer au conseiller Client client1 = new
 * Client("Client 1"); Client client2 = new Client("Client 2");
 * 
 * client1.setConseiller(conseiller); client2.setConseiller(conseiller);
 * 
 * conseiller.getClients().add(client1); conseiller.getClients().add(client2);
 * 
 * conseiller.setAgence(agence); agence.getConseillers().add(conseiller);
 * 
 * // Sauvegarder le conseiller et ses clients agenceRepository.save(agence);
 * 
 * } }
 * 
 */