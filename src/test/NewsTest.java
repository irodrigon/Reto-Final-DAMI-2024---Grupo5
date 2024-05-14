package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.News;

public class NewsTest {

	private News newsTest;

	@BeforeEach
	public void setUp() {
		newsTest = new News(45, null, "Disturbios en Madrid.","La policía se ha movilizado...","12345678H");
	}

	@Test
	public void isEqualsTest() {
		newsTest = new News(45, null, "Disturbios en Madrid.","La policía se ha movilizado...","12345678H");
		assertEquals(45, newsTest.getId_noticia());
		assertNull(newsTest.getFoto_noticia());
		assertEquals("Disturbios en Madrid.", newsTest.getTitulo());
		assertEquals("La policía se ha movilizado...", newsTest.getDescripcion());
		assertEquals("12345678H", newsTest.getDni_administrador());
	}
	
}
