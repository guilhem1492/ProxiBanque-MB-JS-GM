package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

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

	@NotEmpty(message = "Le nom du conseiller doit être renseigné.")
	private String nom;

	private String prenom;
	private Boolean gradeGerant;
	private String login;
	private String password;

	@OneToMany(mappedBy = "conseiller", cascade = { CascadeType.PERSIST })
	private Set<Client> clients = new HashSet<Client>();

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
