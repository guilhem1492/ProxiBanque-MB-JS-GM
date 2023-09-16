package com.example.demo.dto;

import com.example.demo.entity.Agence;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConseillerDTO {

	private Long id;
	private String nom;
	private String prenom;
	private Boolean gradeGerant;
	private Agence agence;

}