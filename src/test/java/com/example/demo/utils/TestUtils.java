package com.example.demo.utils;

import java.sql.Date;
import java.time.LocalDate;

import com.example.demo.entity.Agence;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Conseiller;

public class TestUtils {

	public static Client getClient() {
		Client client = new Client();
		
		client.setId(1L);
		client.setNom("bob");
		client.setPrenom("Dylan");
		client.setCompteCourant(null);
		client.setCompteEpargne(null);
		client.setTel("0222222222");
		client.setAdresse("11 rue st Louis");
		client.setCodePostal("35000");
		client.setVille("Rennes");
		client.setConseiller(null);
		
		return client;
	}
	
	public static Conseiller getConseiller() {
		Conseiller conseiller = new Conseiller();

		conseiller.setId(1L);
		conseiller.setNom("bob"); 
		conseiller.setPrenom("Dylan");
		conseiller.setClients(null);
		conseiller.setAgence(null);
		conseiller.setGradeGerant(false);
		conseiller.setLogin("bd");
		conseiller.setPassword("1234");
		
		return conseiller;
	}
	
	public static Agence getAgence() {
		Agence agence = new Agence();
		agence.setId(1L);
		agence.setConseillers(null);
		agence.setName("bob");
		agence.setCreationDate(LocalDate.now());
		agence.setCodeAlpha("cdf21f4d");
		return agence;
	}
	
	
	public static Compte getCompte() {
		Compte compte = new Compte();
		compte.setId(3L);
		compte.setType("cc");
		compte.setNumCompte("g45rd45f");
		compte.setSolde(1000);
		compte.setCreationDate(LocalDate.now());
		compte.setClient(null);
		return compte;
	}
	
	
	public static Compte getCompteCourant() {
		Compte compteCourant = new Compte();
		compteCourant.setId(1L);
		compteCourant.setType("cc");
		compteCourant.setNumCompte("g45fd45f");
		compteCourant.setSolde(1000);
		compteCourant.setCreationDate(LocalDate.now());
		compteCourant.setClient(null);
		return compteCourant;
	}
	
	public static Compte getCompteEpargne() {
		Compte compteEparne = new Compte();
		compteEparne.setId(2L);
		compteEparne.setType("ce");
		compteEparne.setNumCompte("g45gd45f");
		compteEparne.setSolde(1000);
		compteEparne.setCreationDate(LocalDate.now());
		compteEparne.setClient(null);
		return compteEparne;
	}
}
