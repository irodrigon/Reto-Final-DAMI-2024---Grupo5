package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Rango;
import model.Policia;

public class PoliciaTest {

	private Policia policiaTest;

	@BeforeEach
	public void setUp() {
		policiaTest = new Policia("87654321O", "Martín", "Estrada", "9876", null, "CAPITAN");
				
	}

	@Test
	public void isEqualsTest() {
		policiaTest = new Policia("87654321O", "Martín", "Estrada", "9876", null, "CAPITAN");
		assertEquals("87654321O", policiaTest.getDni());
		assertEquals("Martín",policiaTest.getNombre());
		assertEquals("Estrada",policiaTest.getApellido());
		assertEquals("9876",policiaTest.getPassword());
		assertNull(policiaTest.getFotografia());
		assertEquals("CAPITAN",policiaTest.getRango());
	}
	
}
