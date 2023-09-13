package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long> {

}
