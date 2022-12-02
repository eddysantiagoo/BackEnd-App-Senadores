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

import com.senado.model.Partido;
import com.senado.repository.PartidoRepository;

@Controller
@RequestMapping("/partidos")
public class PartidoController {
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@GetMapping
	public ResponseEntity<List<Partido>> getAllPartidos() {
	    try {
	      List<Partido> partidos = partidoRepository.findAll();

	      return new ResponseEntity<>(partidos, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<Partido> getPartidoById(@PathVariable Integer id) {
	    Optional<Partido> partidoData = partidoRepository.findById(id);

	    if (partidoData.isPresent()) {
	      return new ResponseEntity<>(partidoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping()
	  public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
	    try {
	    	Partido _partido = partidoRepository.save(partido);
	      return new ResponseEntity<>(_partido, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Partido> updatePartido(@PathVariable Integer id, @RequestBody Partido partido) {
	    Optional<Partido> partidoData = partidoRepository.findById(id);

	    if (partidoData.isPresent()) {
	    	Partido _partido = partidoData.get();
	    	_partido.setNombre(partido.getNombre());
	      
	      return new ResponseEntity<>(partidoRepository.save(_partido), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deletePartido(@PathVariable Integer id) {
	    try {
	    	partidoRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	      
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


}
