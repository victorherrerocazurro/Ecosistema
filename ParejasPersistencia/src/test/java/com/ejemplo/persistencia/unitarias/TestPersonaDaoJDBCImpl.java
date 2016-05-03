package com.ejemplo.persistencia.unitarias;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.Assertion;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.xml.sax.InputSource;

import com.ejemplo.modelo.Persona;
import com.ejemplo.persistencia.PersonaDaoJDBCImpl;

public class TestPersonaDaoJDBCImpl extends DatabaseTestCase {

	private IDataSet loadedDataSet;
	
	private PersonaDaoJDBCImpl sut = new PersonaDaoJDBCImpl();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/parejas");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		sut.setDataSource(dataSource);
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = 
		    DriverManager.getConnection("jdbc:mysql://localhost/parejas", "root", "root");
		return new DatabaseConnection(jdbcConnection);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		loadedDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("db/input.xml")));
		return loadedDataSet;
	}
	
	//Metodos de Test
	public void testGuardarPersona() throws Exception
	{
		//Datos de prueba
		Persona persona = new Persona(1L, "Victor", 38, 1.85f, 'M');
		IDataSet dataSetEsperado = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("db/expected.xml")));
		
		//Ejecutamos el test
		sut.guardar(persona);
		
		//Recuperamos el estado en el que esta la Base de datos despues de la operacion
		String[] tablas = {"personas"};
		IDataSet realDataSet = getConnection().createDataSet(tablas);
		
		//Ejectumos los asertos
		Assertion.assertEquals(dataSetEsperado, realDataSet);
	}

}
