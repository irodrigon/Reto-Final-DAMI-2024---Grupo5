package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.Criminal;

public class CriminalTest {

	private Criminal criminalTest;

	@BeforeEach
	public void setUp() {
		criminalTest = new Criminal("87654321L", "Alberto", "Manchón", "4321", null,"Sabe artes marciales.","12345678A");
				
	}

	@Test
	public void isEqualsTest() {
		criminalTest = new Criminal("87654321L", "Alberto", "Manchón", "4321", null,"Sabe artes marciales.","12345678A");
		assertEquals("87654321L",criminalTest.getDni());
		assertEquals("Alberto",criminalTest.getNombre());
		assertEquals("Manchón",criminalTest.getApellido());
		assertEquals("4321",criminalTest.getPassword());
		assertNull(criminalTest.getFotografia());
		assertEquals("Sabe artes marciales.",criminalTest.getDescripcion());
		assertEquals("12345678A",criminalTest.getDni_policia());
		}
}
