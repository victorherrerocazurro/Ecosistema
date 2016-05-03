package com.ejemplo.persistencia;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.dbcp.BasicDataSource;

import com.ejemplo.modelo.Persona;

public class PersistenciaTest extends TestCase {
	//este es el SUT (suject under test)
	private PersonaDao personaDao;

	protected void setUp() throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/pruebas");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		PersonaDaoJDBCImpl personaDaoImpl = new PersonaDaoJDBCImpl();
		personaDaoImpl.setDataSource(dataSource);
		personaDao=personaDaoImpl;
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPersonaDao() throws Exception {
		List<Persona>personas=Arrays.asList(
				new Persona(null, "pepe", 78, 1.79f, Persona.HOMBRE),
				new Persona(null, "luis", 88, 1.72f, Persona.HOMBRE),
				new Persona(null, "ana", 71, 1.72f, Persona.MUJER),
				new Persona(null, "pepa", 28, 1.79f, Persona.MUJER));
		
		int tInicial=personaDao.listar().size();
		
		for (Persona persona : personas) {
			personaDao.guardar(persona);
		}
		
		int tFinal=personaDao.listar().size();
		
		assertEquals(tInicial+personas.size(), tFinal);
		
		//TODO hay que recrear la tabla y los autoincrementables
		//en cada ejecuion
		//Persona persona = personaDao.buscar(1L);
		//assertNotNull(persona);
		
		
	}
}
