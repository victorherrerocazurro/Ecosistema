package com.ejemplo.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ejemplo.modelo.Persona;
import com.ejemplo.persistencia.PersonaDaoJDBCImpl;

@RunWith(Parameterized.class)
public class NegocioTest {
	private static ServicioBusquedaParejas buscador;
	
	//campos para los parametros
	private  Persona usuario;
	private  boolean tieneIdeal;
	private  String nombreIdeal;
	private  int numeroAfines;
	
	
	public NegocioTest(Persona usuario, boolean tieneIdeal, String nombreIdeal,
			int numeroAfines) {
		this.usuario = usuario;
		this.tieneIdeal = tieneIdeal;
		this.nombreIdeal = nombreIdeal;
		this.numeroAfines = numeroAfines;
	}


	@Parameters
	public static Collection cargarDatos(){
		return Arrays.asList(new Object[][]{{new Persona(null,"miguel",75,1.79f,Persona.HOMBRE),true,"ana",2},
				{new Persona(null,"lola",25,1.65f,Persona.MUJER),false,null,1}});
	}
	

	@BeforeClass
	public static void alPrincipio() throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		/*dataSource.setUrl("jdbc:mysql://localhost:3306/pruebas");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");*/
		dataSource.setUrl("jdbc:oracle:thin:@192.168.111.128:1521:xe");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUsername("pruebas");
		dataSource.setPassword("pruebas");
		
		PersonaDaoJDBCImpl personaDao = new PersonaDaoJDBCImpl();
		personaDao.setDataSource(dataSource);
		
		List<Persona> listado = personaDao.listar();
		for (Persona persona : listado) {
			personaDao.borrar(persona);
		}

		
		List<Persona>personas=Arrays.asList(
				new Persona(null, "pepe", 78, 1.79f, Persona.HOMBRE),
				new Persona(null, "luis", 88, 1.72f, Persona.HOMBRE),
				new Persona(null, "ana", 71, 1.72f, Persona.MUJER),
				new Persona(null, "pepa", 28, 1.79f, Persona.MUJER));
		
		for (Persona persona : personas) {
			personaDao.guardar(persona);
		}
		
		ServicioBuscadorParejaPersonaDaoImpl buscadorParejaImpl = 
				new ServicioBuscadorParejaPersonaDaoImpl();
		buscadorParejaImpl.setPersonaDao(personaDao);
		buscador=buscadorParejaImpl;
	}

	@AfterClass
	public static void alFinalizar() throws Exception {
	}

	@Before
	public void prepara() throws Exception {
		
	}

	@After
	public void limpiar() throws Exception {
	}

	@Test(timeout=1000)
	public void probarServicio() {
		Persona ideal=buscador.encontrarIdeal(usuario);
		assertEquals(tieneIdeal,ideal!=null);
		if(tieneIdeal)
			assertEquals(nombreIdeal,ideal.getNombre());
		List<Persona>afines=buscador.encontrarAfines(usuario);
		assertEquals(numeroAfines, afines.size());
		
		
	}
	
	@Ignore
	public void otraPrueba() throws Exception {
		assertTrue(true);
	}

}
