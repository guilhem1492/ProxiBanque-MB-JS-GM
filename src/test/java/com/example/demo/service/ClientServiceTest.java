package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.ClientRepository;
import com.example.demo.utils.TestUtils;

@SpringBootTest()
public class ClientServiceTest {

	@MockBean
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientServiceImp clientService;
	
	@Test
	void getAllClientService() {
		
		Client client = TestUtils.getClient();
		Conseiller conseiller = new Conseiller();
		conseiller.setId(1L);;
		conseiller.setNom("dd");
		conseiller.setPrenom("dupont");
				  
		conseiller.setLogin("JD");
		conseiller.setPassword("1234");
		conseiller.setGradeGerant(false);
		client.setConseiller(conseiller);
		
		when(clientRepository.findAll()).thenReturn(List.of(client));
		
		assertEquals(clientService.getAllClients().size(), 1);
	}
}
