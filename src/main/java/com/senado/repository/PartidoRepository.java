package com.senado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senado.model.Partido;

@Repository
public interface PartidoRepository  extends JpaRepository<Partido, Integer>{

}
