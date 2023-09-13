package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Customer's name field can't be empty")
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

	public Client(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", conseiller=" + conseiller + ", compteCourant=" + compteCourant
				+ ", compteEpargne=" + compteEpargne + "]";
	}

}
