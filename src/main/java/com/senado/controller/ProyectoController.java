package com.senado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senado.model.Proyecto;
import com.senado.repository.ProyectoRepository;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
	
	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@GetMapping
	public ResponseEntity<List<Proyecto>> getAllProyectos() {
	    try {
	      List<Proyecto> proyectos = proyectoRepository.findAll();

	      return new ResponseEntity<>(proyectos, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Proyecto> getProyectoById(@PathVariable Integer id) {
	    Optional<Proyecto> proyectoData = proyectoRepository.findById(id);

	    if (proyectoData.isPresent()) {
	      return new ResponseEntity<>(proyectoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping()
	  public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto proyecto) {
	    try {
	    	Proyecto _proyecto = proyectoRepository.save(proyecto);
	      return new ResponseEntity<>(_proyecto, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Proyecto> updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyecto) {
	    Optional<Proyecto> proyectoData = proyectoRepository.findById(id);

	    if (proyectoData.isPresent()) {
	    	Proyecto _proyecto = proyectoData.get();
	    	_proyecto.setNombre(proyecto.getNombre());
	      
	      return new ResponseEntity<>(proyectoRepository.save(_proyecto), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteProyecto(@PathVariable Integer id) {
	    try {
	    	proyectoRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	      
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
