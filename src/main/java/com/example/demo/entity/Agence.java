package com.example.demo.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

	public Agence(String name, String codeAlpha, LocalDate creationDate) {
		this.name = name;
		this.codeAlpha = codeAlpha;
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Agence [id=" + id + ", name=" + name + ", codeAlpha=" + codeAlpha + ", creationDate=" + creationDate
				+ "]";
	}

}
