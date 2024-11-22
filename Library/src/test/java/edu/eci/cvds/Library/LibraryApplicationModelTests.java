package edu.eci.cvds.Library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.model.QrGenerator;
import edu.eci.cvds.library.*;
import java.io.File;

import java.util.Date;
import java.util.Arrays;

@SpringBootTest(classes = LibraryApplication.class)
class LibraryApplicationModelTests {

	private Libro libro;

	@BeforeEach
	public void setUp() {
		libro = new Libro();
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
	void testSetGetColeccion() {
		libro.setColeccion("Clásicos");
		assertEquals("Clásicos", libro.getColeccion());
	}

	@Test
	void testSetGetEditor() {
		libro.setEditor("Editorial XYZ");
		assertEquals("Editorial XYZ", libro.getEditor());
	}

	@Test
	void testSetGetEdicion() {
		libro.setEdicion(3);
		assertEquals(3, libro.getEdicion());
	}

	@Test
	void testSetGetIsbn() {
		libro.setIsbn("978-3-16-148410-0");
		assertEquals("978-3-16-148410-0", libro.getIsbn());
	}

	@Test
	void testSetGetNombreCategoria() {
		libro.setNombreCategoria("Ficción");
		assertEquals("Ficción", libro.getNombreCategoria());
	}

	@Test
	void testSetGetNombreSubcategoria() {
		libro.setNombreSubcategoria("Novela");
		assertEquals("Novela", libro.getNombreSubcategoria());
	}

	@Test
	void testSetGetSinopsis() {
		libro.setSinopsis("Un niño conoce a un aviador perdido en el desierto.");
		assertEquals("Un niño conoce a un aviador perdido en el desierto.", libro.getSinopsis());
	}

	@Test
	void testSetGetFechaIngreso() {
		Date fechaIngreso = new Date();
		libro.setFechaIngreso(fechaIngreso);
		assertEquals(fechaIngreso, libro.getFechaIngreso());
	}

	@Test
	void testSetGetEjemplares() {
		Ejemplar ejemplar1 = new Ejemplar();
		Ejemplar ejemplar2 = new Ejemplar();
		libro.setEjemplares(Arrays.asList(ejemplar1, ejemplar2));
		assertEquals(2, libro.getEjemplares().size());
	}

	@Test
    void testGeneradorQrBar() {
        QrGenerator qrGenerator = new QrGenerator();

        qrGenerator.generadorQrBar("https://example.com");

        File qrFile = new File("qrcode.svg");
        File barcodeFile = new File("barcode.svg");

        assertTrue(qrFile.exists(), "QR Code file should exist");
        assertTrue(barcodeFile.exists(), "Barcode file should exist");

        qrFile.delete();
        barcodeFile.delete();
    }
}
