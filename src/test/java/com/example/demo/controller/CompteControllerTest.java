package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Compte;
import com.example.demo.service.CompteService;
import com.example.demo.service.VirementImpossibleException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompteControllerTest {

    private static String ALL_COMPTE_ROUTE = "/comptes";
    private static String VIREMENT_ROUTE = "/comptes/virement";

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private CompteService compteService;

    @Test
    void getComptes_ok() throws Exception {
        when(compteService.getAllCompte()).thenReturn(List.of(new Compte(), new Compte()));

        this.webTestClient.get()
                .uri(ALL_COMPTE_ROUTE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Compte.class)
                .hasSize(2);
    }

    @Test
    void postCompte_ok() throws Exception {
        Long ownerId = 1L;
        Compte compte = new Compte();
        compte.setType("cc");
        when(compteService.saveCompte(any(Compte.class), eq(ownerId))).thenReturn(compte);

        this.webTestClient.post()
                .uri(ALL_COMPTE_ROUTE + "/" + ownerId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(compte)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Compte.class);
    }

    @Test
    void virement_ok() throws Exception {
        VirementDTO virementDTO = new VirementDTO(null, null, 0);
        when(compteService.virementComptes(any(VirementDTO.class))).thenReturn("Virement réussi."); 
        this.webTestClient.post()
                .uri(VIREMENT_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(virementDTO)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Virement réussi.");
    }

    @Test
    void virement_badRequest() throws Exception {
        VirementDTO virementDTO = new VirementDTO(1L, 2L, 0);
        doThrow(VirementImpossibleException.class).when(compteService).virementComptes(any());
        this.webTestClient.post()
                .uri(VIREMENT_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(virementDTO)
                .exchange()
                .expectStatus()
                .is5xxServerError();
        
        verify(compteService, times(1)).virementComptes(any());
    }
}
