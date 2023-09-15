package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.entity.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long>{
	
	
	Conseiller findByLoginAndPassword(String login, String password);

}
