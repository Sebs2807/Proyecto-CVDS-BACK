package edu.eci.cvds.Library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.*;
import java.util.Arrays;


@SpringBootTest(classes = LibraryApplication.class)
class LibraryApplicationModelTests {

	private Libro libro;

	@BeforeEach
	public void setUp() {
		libro = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador", "2005");
	}

	@Test
	void testSetGetId() {
		libro.setId("1");
		assertEquals("1", libro.getId());
	}

	@Test
	void testSetGetNombreLibro() {
		libro.setNombreLibro("El Principito");
		assertEquals("El Principito", libro.getNombreLibro());
	}

	@Test
	void testSetGetAutor() {
		libro.setAutor("Antoine de Saint-Exupéry");
		assertEquals("Antoine de Saint-Exupéry", libro.getAutor());
	}

	@Test
	void testSetGetEditor() {
		libro.setEditor("Editorial XYZ");
		assertEquals("Editorial XYZ", libro.getEditor());
	}

	@Test
	void testSetGetEdicion() {
		libro.setEdicion("3");
		assertEquals("3", libro.getEdicion());
	}

	@Test
	void testSetGetIsbn() {
		libro.setIsbn("978-3-16-148410-0");
		assertEquals("978-3-16-148410-0", libro.getIsbn());
	}

	@Test
	void testSetGetSinopsis() {
		libro.setSinopsis("Un niño conoce a un aviador perdido en el desierto.");
		assertEquals("Un niño conoce a un aviador perdido en el desierto.", libro.getSinopsis());
	}

	@Test
	void testSetGetEjemplares() {
		Ejemplar ejemplar1 = new Ejemplar("Bueno", false);
		Ejemplar ejemplar2 = new Ejemplar("bueno", false);
		libro.setEjemplares(Arrays.asList(ejemplar1, ejemplar2));
		assertEquals(2, libro.getEjemplares().size());
	}

	// @Test
    // void testGeneradorQrBar() {
    //     QrGenerator qrGenerator = new QrGenerator();

    //     qrGenerator.generadorQrBar("https://example.com");

    //     File qrFile = new File("qrcode.svg");
    //     File barcodeFile = new File("barcode.svg");

    //     assertTrue(qrFile.exists(), "QR Code file should exist");
    //     assertTrue(barcodeFile.exists(), "Barcode file should exist");

    //     qrFile.delete();
    //     barcodeFile.delete();
    // }
}
