package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.dto.ClientDTO;
import com.example.demo.service.ClientService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {
	
	private static String ALL_CLIENT_ROUTE = "/clients";
	
	@Autowired
	WebTestClient webTestClient;
	
	@MockBean
	private ClientService clientService;
	
	@Test
	void recupererDemandeDeclaration_ok() throws Exception {

      when(clientService.getAllClients()).thenReturn(List.of(new ClientDTO(),new ClientDTO()));

      this.webTestClient.get()
              .uri(ALL_CLIENT_ROUTE)
              .exchange()
              .expectStatus()
              .isOk()
              .expectBodyList(ClientDTO.class)
              .hasSize(2);
  }
	
}
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//class DefaultDemandeDeclarationApiTest {
//
//    private static final String GERER_DEMANDE_DECLARATION_ID = "/declaration/id/{id}";
//    private static final String GERER_DEMANDE_DECLARATION_REFERENCE = "/declaration/reference/{reference}";
//    private static final String RECUPERER_DEMANDES_DECLARATIONS = "/declarations";
//    private static final String CREER_DEMANDE_DECLARATION = "/declaration";
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    @MockBean
//    private DefaultDemandeDeclarationService defaultDemandeDeclarationService;
//
//    // ------------------- recupererDemandeDeclarationParId
//    @Test
//    void recupererDemandeDeclaration_ok() throws Exception {
//        // Arrange
//        when(defaultDemandeDeclarationService.recupererDemandeDeclarationParId(Mockito.anyLong(), Mockito.anyBoolean())).thenReturn(Optional.of(new DemandeDeclaration().id(20L)));
//
//        // Act & Assert
//        this.webTestClient.get()
//                .uri(b -> b.path(GERER_DEMANDE_DECLARATION_ID).queryParam("avecFichier", true).build(20L))
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody(DemandeDeclaration.class)
//                .value(demande -> {
//                    assertThat(demande.getId()).isEqualTo(20L);
//                });
//    }