package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.entity.Agence;
import com.example.demo.service.AgenceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenceControllerTest {

	private static String ALL_AGENCE_ROUTE = "/agences";

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	private AgenceService agenceService;

	@Test
    void getAgences_ok() throws Exception {
        when(agenceService.getAllAgences()).thenReturn(List.of(new Agence(), new Agence()));

        this.webTestClient.get()
                .uri(ALL_AGENCE_ROUTE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Agence.class)
                .hasSize(2);
    }

	@Test
	void postAgence_ok() throws Exception {
		Agence agence = new Agence();
		agence.setNom("bob");
		when(agenceService.saveAgence(any(Agence.class))).thenReturn(agence);

		this.webTestClient.post().uri(ALL_AGENCE_ROUTE).contentType(MediaType.APPLICATION_JSON).bodyValue(agence)
				.exchange().expectStatus().isOk().expectBody(Agence.class);
	}

	@Test
	void getAgenceById_ok() throws Exception {
		Long agenceId = 1L;
		Agence agence = new Agence();
		when(agenceService.getAgenceById(agenceId)).thenReturn(Optional.of(agence));

		this.webTestClient.get().uri(ALL_AGENCE_ROUTE + "/" + agenceId).exchange().expectStatus().isOk()
				.expectBody(Agence.class);
	}

	@Test
	void deleteAgence_ok() throws Exception {
		Long agenceId = 1L;
		this.webTestClient.delete().uri(ALL_AGENCE_ROUTE + "/" + agenceId).exchange().expectStatus().isOk();
	}
}
