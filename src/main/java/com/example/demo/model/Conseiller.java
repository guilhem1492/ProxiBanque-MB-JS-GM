package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Conseiller {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Customer name field can't be empty")
	private String name;

	@OneToMany(mappedBy = "conseiller", cascade = { CascadeType.PERSIST })
	private Set<Customer> customers = new HashSet<Customer>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "agence_id")
	private Agence agence;

	public Conseiller() {
	}

	public Conseiller(String name) {
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

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Override
	public String toString() {
		return "Conseiller [id=" + id + ", name=" + name + ", customers=" + customers + ", agence=" + agence + "]";
	}

}
