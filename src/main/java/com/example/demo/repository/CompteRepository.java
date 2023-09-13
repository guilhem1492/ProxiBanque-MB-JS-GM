package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
