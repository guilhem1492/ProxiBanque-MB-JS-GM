package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class CompteCourant extends Compte {

	@JsonBackReference
	@OneToOne(mappedBy = "compte")
	private Client client;

	public CompteCourant() {
		super();
	}

	public CompteCourant(String type, String numCompte, int solde, LocalDate creationDate) {
		super(type, numCompte, solde, creationDate);
	}

}
