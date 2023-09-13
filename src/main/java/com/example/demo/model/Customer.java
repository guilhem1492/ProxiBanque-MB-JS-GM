package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Customer name field can't be empty")
	private String name;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "conseiller_id")
	private Conseiller conseiller;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "compte_courant_id", referencedColumnName = "id")
	@JsonManagedReference
	private CompteCourant compteCourant;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "compte_epargne_id", referencedColumnName = "id")
	@JsonManagedReference
	private CompteEpargne compteEpargne;

	public Customer() {
	}

	public Customer(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	
	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", conseiller=" + conseiller + ", compteCourant="
				+ compteCourant + ", compteEpargne=" + compteEpargne + "]";
	}

}
