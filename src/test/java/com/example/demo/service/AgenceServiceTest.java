package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Agence;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.utils.TestUtils;

@SpringBootTest
public class AgenceServiceTest {

    @MockBean
    private AgenceRepository agenceRepository;

    @Autowired
    private AgenceServiceImp agenceService;
    
    @MockBean
    private RandomCodeGeneratorServiceimp codeGeneratorServiceimp;

    @Test
    void createAgence() {
    	String codeGenerated = "xx12xx12";
        String agenceName = "Test Agence";
        
        when(codeGeneratorServiceimp.generateRandomCode()).thenReturn(codeGenerated);
        Agence agence = agenceService.createAgence(agenceName);

       verify(codeGeneratorServiceimp, times(1)).generateRandomCode();
    }

    @Test
    void getAllAgences() {
        Agence agence = TestUtils.getAgence();
        when(agenceRepository.findAll()).thenReturn(List.of(agence));

        List<Agence> agences = (List<Agence>) agenceService.getAllAgences();

        assertEquals(agences.size(), 1);
    }

    @Test
    void saveAgence() {
        Agence agence = TestUtils.getAgence();
        when(agenceRepository.save(any(Agence.class))).thenReturn(agence);

        Agence savedAgence = agenceService.saveAgence(agence);

        assertNotNull(savedAgence);
        assertEquals(agence.getName(), savedAgence.getName());
        assertNotNull(savedAgence.getCodeAlpha());
        assertNotNull(savedAgence.getCreationDate());
    }

    @Test
    void getAgenceById() {
        Agence agence = TestUtils.getAgence();
        when(agenceRepository.findById(agence.getId())).thenReturn(Optional.of(agence));

        Optional<Agence> retrievedAgence = agenceService.getAgenceById(agence.getId());

        verify(agenceRepository, times(1)).findById(agence.getId());
    }

    @Test
    void deleteAgenceById() {
        Agence agence = TestUtils.getAgence();
        agenceService.deleteAgenceById(agence.getId());
        verify(agenceRepository, times(1)).deleteById(agence.getId());
        
    }

}