package com.senado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senado.model.Senador;

@Repository
public interface SenadorRepository extends JpaRepository<Senador, Integer>{

}
