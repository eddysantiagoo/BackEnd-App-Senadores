package com.senado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senado.model.Senador;
import com.senado.repository.SenadorRepository;

@RestController
@RequestMapping("/senadores")
public class SenadorController {
	
	@Autowired
	private SenadorRepository senadorRepository;
	
	@GetMapping
	public ResponseEntity<List<Senador>> getAllSenadores() {
	    try {
	      List<Senador> senadores = senadorRepository.findAll();

	      return new ResponseEntity<>(senadores, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Senador> getSenadorById(@PathVariable Integer id) {
	    Optional<Senador> senadorData = senadorRepository.findById(id);

	    if (senadorData.isPresent()) {
	      return new ResponseEntity<>(senadorData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping()
	  public ResponseEntity<Senador> createSenador(@RequestBody Senador senador) {
	    try {
	    	Senador _senador = senadorRepository.save(senador);
	      return new ResponseEntity<>(_senador, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Senador> updateSenador(@PathVariable Integer id, @RequestBody Senador senador) {
	    Optional<Senador> senadorData = senadorRepository.findById(id);

	    if (senadorData.isPresent()) {
	    	Senador _senador = senadorData.get();
	      _senador.setNombre(senador.getNombre());
	      _senador.setApellidos(senador.getApellidos());
	      _senador.setDepartamento(senador.getDepartamento());
	      _senador.setPartido(senador.getPartido());
	      _senador.setActivo(senador.getActivo());
	      
	      return new ResponseEntity<>(senadorRepository.save(_senador), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteSenador(@PathVariable Integer id) {
	    try {
	      senadorRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	      
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


}
