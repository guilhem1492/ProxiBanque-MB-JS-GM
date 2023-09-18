package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.VirementDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CompteRepository;
import com.example.demo.utils.TestUtils;

@SpringBootTest
public class CompteServiceTest {

    @MockBean
    private CompteRepository compteRepository;

    @Autowired
    private CompteServiceImp compteService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    void createCompte() {
        String type = "cc";
        int solde = 1000;

        Compte compte = compteService.createCompte(type, solde);

        assertNotNull(compte);
        assertTrue(compte instanceof CompteCourant);
        assertEquals(type, compte.getType());
        assertNotNull(compte.getNumCompte());
        assertEquals(solde, compte.getSolde());
        assertNotNull(compte.getCreationDate());
    }

    @Test
    void getAllComptes() {
        Compte compte = TestUtils.getCompte();
        when(compteRepository.findAll()).thenReturn(List.of(compte));

        List<Compte> comptes = (List<Compte>) compteService.getAllCompte();

        assertEquals(comptes.size(), 1);
    }

    @Test
    void saveCompte() {
        Compte compte = TestUtils.getCompte();
        Client client = TestUtils.getClient();
        compte.setClient(client);
        
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(compteRepository.save(any(Compte.class))).thenReturn(compte);

        Compte savedCompte = compteService.saveCompte(compte, client.getId());

        assertNotNull(savedCompte);
        assertEquals(client, savedCompte.getClient());
    }

    @Test
    void getCompteById() {
        Compte compte = TestUtils.getCompte();
        when(compteRepository.findById(compte.getId())).thenReturn(Optional.of(compte));

        Optional<Compte> retrievedCompte = compteService.getCompteById(compte.getId());

       verify(compteRepository,times(1)).findById(compte.getId());
    }


}