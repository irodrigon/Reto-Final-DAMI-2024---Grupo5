package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Administrador;

public class AdministradorTest {

	private Administrador adminTest;

	@BeforeEach
	public void setUp() {
		adminTest = new Administrador("12345678L", "Adrian", "Garcia", "1234", null, null, null);
	}

	@Test
	public void isEqualsTest() {
		adminTest = new Administrador("12345678L", "Adrian", "Garcia", "1234", null, null, null);
		assertEquals("12345678L", adminTest.getDni());
		assertEquals("Adrian", adminTest.getNombre());
		assertEquals("Garcia", adminTest.getApellido());
		assertEquals("1234", adminTest.getPassword());
		assertNull(adminTest.getFotografia());
		assertNull(adminTest.getFechaPrimerLog());
		assertNull(adminTest.getFechaUltimoLog());

	}

}
