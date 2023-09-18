package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.ConseillerRepository;
import com.example.demo.utils.TestUtils;

@SpringBootTest()
public class ClientServiceTest {

	@MockBean
	private ClientRepository clientRepository;

	@Autowired
	private ClientServiceImp clientService;

	@MockBean
	private ConseillerRepository conseillerRepository;

	@MockBean
	private CompteRepository compteRepository;

	@Test
	void getAllClientService() {

		Client client = TestUtils.getClient();
		Conseiller conseiller = TestUtils.getConseiller();
		client.setConseiller(conseiller);
		when(clientRepository.findAll()).thenReturn(List.of(client));

		assertEquals(clientService.getAllClients().size(), 1);
	}

	@Test
	void saveClientService() {
		Client client = TestUtils.getClient();
		Conseiller conseiller = TestUtils.getConseiller();
		Compte compteCourant = TestUtils.getCompteCourant();
		Compte compteEpargne = TestUtils.getCompteEpargne();
		client.setConseiller(conseiller);

		when(conseillerRepository.findById(conseiller.getId())).thenReturn(Optional.of(conseiller));
		when(clientRepository.save(client)).thenReturn(client);
		when(compteRepository.save(compteCourant)).thenReturn(compteCourant);
		when(compteRepository.save(compteEpargne)).thenReturn(compteEpargne);

		Client savedClient = clientService.saveClient(client, conseiller.getId());

		verify(clientRepository, times(1)).save(client);
		verify(conseillerRepository, times(1)).findById(conseiller.getId());
		verify(compteRepository, times(2)).save(any(Compte.class));

	}

	@Test
	void getClientById() {

		Client client = TestUtils.getClient();
		Conseiller conseiller = TestUtils.getConseiller();
		client.setConseiller(conseiller);

		when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

		Optional<ClientDTO> clientOptional = clientService.getClientById(client.getId());

		verify(clientRepository, times(1)).findById(client.getId());
	}

//	@Test
//	void deleteClientById() {
//
//		Client client = TestUtils.getClient();
//		clientService.deleteClientById(client.getId());
//		verify(clientRepository, times(1)).deleteById(client.getId());
//
//	}

	@Test
	void updateClient() {
		Client client = TestUtils.getClient();
		Conseiller conseiller = TestUtils.getConseiller();
		client.setConseiller(conseiller);

		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(1L);
		clientDTO.setNom("NouveauNom");

		when(clientRepository.findById(clientDTO.getId())).thenReturn(Optional.of(client));
		when(clientRepository.save(any())).thenReturn(client);

		ClientDTO updatedClientDTO = clientService.updateClient(clientDTO);

		verify(clientRepository, times(1)).findById(clientDTO.getId());
		verify(clientRepository, times(1)).save(any());

	}
}
