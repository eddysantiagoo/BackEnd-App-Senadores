package com.senado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senado.model.Relacion;

@Repository
public interface RelacionRepository extends JpaRepository<Relacion, Integer>{

}
