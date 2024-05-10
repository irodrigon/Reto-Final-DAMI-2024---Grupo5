package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Tipo;
import model.Arsenal;

public class ArsenalTest {

	private Arsenal arsenalTest;

	@BeforeEach
	public void setUp() {
		arsenalTest = new Arsenal(25, null,"Guantes anticorte",Tipo.valueOf("ARMADURA"),"Útiles contra armas blancas");
	}

	@Test
	public void isEqualsTest() {
		arsenalTest = new Arsenal(25, null,"Guantes anticorte",Tipo.valueOf("ARMADURA"),"Útiles contra armas blancas");
		assertEquals(25, arsenalTest.getId_arsenal());
		assertNull();
		

	}
