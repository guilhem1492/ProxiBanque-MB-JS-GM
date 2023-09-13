package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
