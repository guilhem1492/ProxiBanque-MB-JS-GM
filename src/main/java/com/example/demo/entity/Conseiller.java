package com.example.demo.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Conseiller {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "Name field can't be empty")
	private String nom;
	
	private String prenom;

	@OneToMany(mappedBy = "conseiller", cascade = { CascadeType.PERSIST })
	private Set<Client> clients = new HashSet<Client>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "agence_id")
	private Agence agence;

	public Conseiller(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Conseiller [id=" + id + ", nom=" + nom + ", customers=" + clients + ", agence=" + agence + "]";
	}

}
