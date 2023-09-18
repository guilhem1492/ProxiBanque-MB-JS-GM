package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Conseiller;
import com.example.demo.service.ConseillerService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConseillerControllerTest {

    private static String ALL_CONSEILLER_ROUTE = "/conseillers";

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private ConseillerService conseillerService;

    @Test
    void getConseillers_ok() throws Exception {
        when(conseillerService.getAllConseiller()).thenReturn(List.of(new Conseiller(), new Conseiller()));

        this.webTestClient.get()
                .uri(ALL_CONSEILLER_ROUTE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Conseiller.class)
                .hasSize(2);
    }

    @Test
    void postConseiller_ok() throws Exception {
        Long agenceId = 1L;
        Conseiller conseiller = new Conseiller();
        conseiller.setNom("bob");
        
        when(conseillerService.saveConseiller(any(Conseiller.class), eq(agenceId))).thenReturn(conseiller);

        this.webTestClient.post()
                .uri(ALL_CONSEILLER_ROUTE + "/" + agenceId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(conseiller)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Conseiller.class);
    }

    @Test
    void deleteConseiller_ok() throws Exception {
        Long conseillerId = 1L;
        doNothing().when(conseillerService).deleteConseillerById(conseillerId);

        this.webTestClient.delete()
                .uri(ALL_CONSEILLER_ROUTE + "/" + conseillerId)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getConseillersByNameByPassword_ok() throws Exception {
        String login = "exampleLogin";
        String password = "examplePassword";
        ConseillerDTO conseillerDTO = new ConseillerDTO();
        when(conseillerService.getConseillerByLoginByPassword(login, password)).thenReturn(conseillerDTO);

        this.webTestClient.get()
                .uri(ALL_CONSEILLER_ROUTE + "/auth?login=" + login + "&password=" + password)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ConseillerDTO.class);
    }
}