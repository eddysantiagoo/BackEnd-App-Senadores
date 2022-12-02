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

import com.senado.model.Relacion;
import com.senado.repository.RelacionRepository;

@RestController
@RequestMapping("/relaciones")
public class RelacionController {
	
	@Autowired
	private RelacionRepository relacionRepository;
	
	@GetMapping
	public ResponseEntity<List<Relacion>> getAllRelacion() {
	    try {
	      List<Relacion> relaciones = relacionRepository.findAll();

	      return new ResponseEntity<>(relaciones, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Relacion> getRelacionById(@PathVariable Integer id) {
	    Optional<Relacion> relacionData = relacionRepository.findById(id);

	    if (relacionData.isPresent()) {
	      return new ResponseEntity<>(relacionData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping()
	  public ResponseEntity<Relacion> createRelacion(@RequestBody Relacion relacion) {
	    try {
	    	Relacion _relacion = relacionRepository.save(relacion);
	      return new ResponseEntity<>(_relacion, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Relacion> updateRelacion(@PathVariable Integer id, @RequestBody Relacion relacion) {
	    Optional<Relacion> relacionData = relacionRepository.findById(id);

	    if (relacionData.isPresent()) {
	    	Relacion _relacion = relacionData.get();
	    	_relacion.setIdSenador(relacion.getIdSenador());
	    	_relacion.setIdProyecto(relacion.getIdProyecto());
	      
	      return new ResponseEntity<>(relacionRepository.save(_relacion), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteRelacion(@PathVariable Integer id) {
	    try {
	    	relacionRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	      
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
