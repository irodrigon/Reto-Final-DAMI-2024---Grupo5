package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Elige;

public class EligeTest {

	private Elige eligeTest;

	@BeforeEach
	public void setUp() {
		eligeTest = new Elige(12,"12345678L");
	}

	@Test
	public void isEqualsTest() {
		eligeTest = new Elige(12,"12345678L");
		assertEquals(12, eligeTest.getId_arsenal());
		assertEquals("12345678L", eligeTest.getDni_policia());

	}

}
