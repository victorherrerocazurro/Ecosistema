package com.ejemplo.persistencia;

import java.util.List;

import com.ejemplo.modelo.Persona;

public interface PersonaDao {

	void guardar(Persona persona);

	void borrar(Persona persona);

	List<Persona> listar();

	Persona buscar(Long id);

}