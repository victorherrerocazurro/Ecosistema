package com.ejemplo.negocio;

import java.util.List;

import com.ejemplo.modelo.Persona;

public interface ServicioBusquedaParejas {

	Persona encontrarIdeal(Persona usuario);

	List<Persona> encontrarAfines(Persona usuario);

}
