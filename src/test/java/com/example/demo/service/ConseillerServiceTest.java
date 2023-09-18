package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Agence;
import com.example.demo.entity.Conseiller;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.repository.ConseillerRepository;
import com.example.demo.utils.TestUtils;

@SpringBootTest
public class ConseillerServiceTest {

    @MockBean
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ConseillerServiceimp conseillerService;

    @MockBean
    private AgenceRepository agenceRepository;

    @Test
    void getAllConseillers() {
        Conseiller conseiller = TestUtils.getConseiller();
        when(conseillerRepository.findAll()).thenReturn(List.of(conseiller));

        List<Conseiller> conseillers = (List<Conseiller>) conseillerService.getAllConseiller();
        
        assertEquals(conseillers.size(), 1);
    }

    @Test
    void saveConseiller() {
        Conseiller conseiller = TestUtils.getConseiller();
        Agence agence = TestUtils.getAgence();
        conseiller.setAgence(agence);
        
        when(agenceRepository.findById(agence.getId())).thenReturn(Optional.of(agence));
        when(conseillerRepository.save(conseiller)).thenReturn(conseiller);

        Conseiller savedConseiller = conseillerService.saveConseiller(conseiller, agence.getId());

        verify(agenceRepository, times(1)).findById(agence.getId());
        verify(conseillerRepository, times(1)).save(conseiller);
        
    }

    @Test
    void getConseillerById() {
        Conseiller conseiller = TestUtils.getConseiller();
        when(conseillerRepository.findById(conseiller.getId())).thenReturn(Optional.of(conseiller));

        Optional<Conseiller> retrievedConseiller = conseillerService.getConseillerById(conseiller.getId());

        verify(conseillerRepository, times(1)).findById(conseiller.getId());
    }

    @Test
    void deleteConseillerById() {
        Conseiller conseiller = TestUtils.getConseiller();
        conseillerService.deleteConseillerById(conseiller.getId());

        verify(conseillerRepository, times(1)).deleteById(conseiller.getId());
    }

    @Test
    void getConseillerByLoginByPassword() {
        Conseiller conseiller = TestUtils.getConseiller();
        String login = "testLogin";
        String password = "testPassword";
        
        when(conseillerRepository.findByLoginAndPassword(login, password)).thenReturn(conseiller);

        ConseillerDTO conseillerDTO = conseillerService.getConseillerByLoginByPassword(login, password);

        verify(conseillerRepository,times(1)).findByLoginAndPassword(login, password);
    }
}