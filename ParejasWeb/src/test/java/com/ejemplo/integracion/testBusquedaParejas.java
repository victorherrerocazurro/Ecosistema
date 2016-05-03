package com.ejemplo.integracion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class testBusquedaParejas extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:9090/Parejas/");
		//selenium = new DefaultSelenium("localhost", 4444, "*iehta", "http://localhost:9090/Parejas/");
		
		selenium.start();
	}

	@Test
	public void testTestBusquedaParejas() throws Exception {
		selenium.open("/Parejas/");
		selenium.type("name=nombre", "fernando");
		selenium.type("name=edad", "79");
		selenium.type("name=altura", "1.79");
		selenium.type("name=sexo", "H");
		selenium.click("name=buscar");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(""));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
