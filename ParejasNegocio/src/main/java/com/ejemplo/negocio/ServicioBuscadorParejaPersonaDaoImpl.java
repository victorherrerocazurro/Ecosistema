package com.ejemplo.negocio;


import java.util.ArrayList;
import java.util.List;

import com.ejemplo.modelo.Persona;
import com.ejemplo.persistencia.PersonaDao;

public class ServicioBuscadorParejaPersonaDaoImpl extends AbstractServicioRastreador implements
		ServicioBusquedaParejas {
	
	private PersonaDao personaDao;
	
	public Persona encontrarIdeal(Persona usuario) {
		log.debug("buscando pareja ideal para "+usuario);
		List<Persona>candidatos=personaDao.listar();
		Persona ideal = null;
		for (Persona candidato : candidatos) {
			if(usuario.isDistintoSexo(candidato)){
				if(Reglas.esIdeal(usuario,candidato)){
					ideal=candidato;
					break;
				}
				
			}
		}
		log.debug("encontrado  ideal "+ideal+" para "+usuario);
		return ideal;
	}

	public List<Persona> encontrarAfines(Persona usuario) {
		log.debug("buscando afines para "+usuario);
		List<Persona>candidatos=personaDao.listar();
		List<Persona> afines = new ArrayList<Persona>();
		for (Persona candidato : candidatos) {
			if(usuario.isDistintoSexo(candidato)){
				if(Reglas.esAfin(usuario,candidato)){
					afines.add(candidato);
				}
				
			}
		}
		log.debug("encontrados  afines "+afines+" para "+usuario);
		return afines;
	}

	public PersonaDao getPersonaDao() {
		return personaDao;
	}

	public void setPersonaDao(PersonaDao personaDao) {
		this.personaDao = personaDao;
	}
}
