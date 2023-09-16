package com.example.demo.dto;

import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

	private Long Id;

	@NotEmpty(message = "Le nom du client doit être renseigné.")
	private String nom;

	@NotEmpty(message = "Le prénom du client doit être renseigné.")
	private String prenom;

	private String adresse;

	private String codePostal;
	private String ville;
	private String tel;
	private Long conseiller_id;

	private CompteCourant compteCourant;

	private CompteEpargne compteEpargne;

}