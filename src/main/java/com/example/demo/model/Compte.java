package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Compte {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Compte type field can't be empty")
	private String type;
	private String numCompte;
	@NotNull(message = "Compte solde field can't be null")
	private int solde;
	private LocalDate creationDate;
	
	@OneToOne
	private Customer customer;

	public Compte() {
	}

	public Compte(String type, String numCompte, int solde, LocalDate creationDate) {
		this.type = type;
		this.numCompte = numCompte;
		this.solde = solde;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", type=" + type + ", numCompte=" + numCompte + ", solde=" + solde
				+ ", creationDate=" + creationDate + "]";
	}

	

}
