package com.ejemplo.negocio.unitarias;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.ejemplo.modelo.Persona;
import com.ejemplo.negocio.ServicioBuscadorParejaPersonaDaoImpl;
import com.ejemplo.persistencia.PersonaDao;

@RunWith(Parameterized.class)
public class TestNegocio {

	private static ServicioBuscadorParejaPersonaDaoImpl sut;

	@Parameters
	public static Collection cargarDatos(){
		return Arrays.asList(new Object[][]{
			{new Persona(null,"miguel",75,1.79f,Persona.HOMBRE),true,"ana",2},
			{new Persona(null,"lola",25,1.65f,Persona.MUJER),false,null,1}
		});
	}

	//campos para los parametros
	private  Persona usuario;
	private  boolean tieneIdeal;
	private  String nombreIdeal;
	private  int numeroAfines;
	
	
	public TestNegocio(Persona usuario, boolean tieneIdeal, String nombreIdeal, int numeroAfines) {
		this.usuario = usuario;
		this.tieneIdeal = tieneIdeal;
		this.nombreIdeal = nombreIdeal;
		this.numeroAfines = numeroAfines;
	}

	@BeforeClass
	public static void alPrincipio() throws Exception {
		
		PersonaDao personaDao = Mockito.mock(PersonaDao.class);
		
		List<Persona>personas=Arrays.asList(
				new Persona(null, "pepe", 78, 1.79f, Persona.HOMBRE),
				new Persona(null, "luis", 88, 1.72f, Persona.HOMBRE),
				new Persona(null, "ana", 71, 1.72f, Persona.MUJER),
				new Persona(null, "pepa", 28, 1.79f, Persona.MUJER));
		
		Mockito.when(personaDao.listar()).thenReturn(personas);
		
		sut = new ServicioBuscadorParejaPersonaDaoImpl();
		
		sut.setPersonaDao(personaDao);
	}

	@Test(timeout=1000)
	public void probarServicio() {
		
		Persona ideal=sut.encontrarIdeal(usuario);
		
		Assert.assertEquals(tieneIdeal,ideal!=null);
		
		if(tieneIdeal){
			Assert.assertEquals(nombreIdeal,ideal.getNombre());
		}
		
		List<Persona>afines=sut.encontrarAfines(usuario);
		
		Assert.assertEquals(numeroAfines, afines.size());
	}
}
