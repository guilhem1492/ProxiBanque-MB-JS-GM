package com.example.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Agence {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Agence name field can't be empty")
	private String name;
	private String codeAlpha;
	private LocalDate creationDate;

	@JsonIgnore
	@OneToMany(mappedBy = "agence", cascade = { CascadeType.PERSIST })
	private Set<Conseiller> Conseillers = new HashSet<Conseiller>();

	public Agence() {
	}

	public Agence(String name, String codeAlpha, LocalDate creationDate) {
		this.name = name;
		this.codeAlpha = codeAlpha;
		this.creationDate = creationDate;
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

	public String getCodeAlpha() {
		return codeAlpha;
	}

	public void setCodeAlpha(String codeAlpha) {
		this.codeAlpha = codeAlpha;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Conseiller> getConseillers() {
		return Conseillers;
	}

	public void setConseillers(Set<Conseiller> conseillers) {
		Conseillers = conseillers;
	}

	@Override
	public String toString() {
		return "Agence [id=" + id + ", name=" + name + ", codeAlpha=" + codeAlpha + ", creationDate=" + creationDate
				+ "]";
	}

}
