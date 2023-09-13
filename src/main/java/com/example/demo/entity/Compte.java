package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	private Client client;

	public Compte(String type, String numCompte, int solde, LocalDate creationDate) {
		this.type = type;
		this.numCompte = numCompte;
		this.solde = solde;
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", type=" + type + ", numCompte=" + numCompte + ", solde=" + solde
				+ ", creationDate=" + creationDate + "]";
	}

}
