package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.Persona;

public class PersonaTest {

	private Persona personaTest;

	@BeforeEach
	public void setUp() {
		personaTest = new Persona("87654321K", "María", "Ortega", "5678", null);
				
	}

	@Test
	public void isEqualsTest() {
		personaTest = new Persona("87654321K", "María", "Ortega", "5678", null);
		assertEquals("87654321K", personaTest.getDni());
		assertEquals("María",personaTest.getNombre());
		assertEquals("Ortega",personaTest.getApellido());
		assertEquals("5678",personaTest.getPassword());
		assertNull(personaTest.getFotografia());
	}
}