package com.senado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relaciones")
public class Relacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idSenador;
	private Integer idProyecto;
	
	public Relacion() {
		super();
	}

	public Relacion(Integer id, Integer idSenador, Integer idProyecto) {
		super();
		this.id = id;
		this.idSenador = idSenador;
		this.idProyecto = idProyecto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdSenador() {
		return idSenador;
	}

	public void setIdSenador(Integer idSenador) {
		this.idSenador = idSenador;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	@Override
	public String toString() {
		return "Relacion [id=" + id + ", idSenador=" + idSenador + ", idProyecto=" + idProyecto + "]";
	}
	
	

}
