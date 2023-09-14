package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long>{
	
	@Query(value = "SELECT t from Conseiller t WHERE t.login = :login AND t.password = :password")
	Conseiller findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
