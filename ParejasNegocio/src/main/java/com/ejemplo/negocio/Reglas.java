package com.ejemplo.negocio;

import static java.lang.Math.abs;

import com.ejemplo.modelo.Persona;
public class Reglas {

	public static boolean esIdeal(Persona usuario, Persona candidato) {
		return abs(usuario.getEdad()-candidato.getEdad())<10
			&& abs(usuario.getAltura()-candidato.getAltura())<0.10f;
		
	}

	public static boolean esAfin(Persona usuario, Persona candidato) {
		return abs(usuario.getEdad()-candidato.getEdad())<10
			 || abs(usuario.getAltura()-candidato.getAltura())<0.10f;
	}

}
