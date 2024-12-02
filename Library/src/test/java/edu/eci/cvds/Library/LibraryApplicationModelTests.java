package edu.eci.cvds.Library;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.zxing.WriterException;
import java.io.IOException;

import edu.eci.cvds.library.model.Carga;
import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.model.CodeGenerator;
import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

@SpringBootTest(classes = LibraryApplication.class)
class LibraryApplicationModelTests {
	private Subcategoria subcategoria1;

	private Libro libro1;

	@BeforeEach
	public void setUp() {
		libro1 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador",
				"2005");
		subcategoria1 = new Subcategoria("Ficción");
	}

	@Test
	void testSetGetId() {
		libro1.setId("1");
		assertEquals("1", libro1.getId());
	}

	@Test
	void testSetGetEditor() {
		libro1.setEditor("Editorial XYZ");
		assertEquals("Editorial XYZ", libro1.getEditor());
	}

	@Test
	void testSetGetEjemplares() {
		Ejemplar ejemplar1 = new Ejemplar("Bueno", false);
		Ejemplar ejemplar2 = new Ejemplar("bueno", false);
		libro1.setEjemplares(Arrays.asList(ejemplar1, ejemplar2));
		assertEquals(2, libro1.getEjemplares().size());
	}

	// Subcategorias

	@Test
	void testSetSubcategoria() {
		subcategoria1.setSubcategoria("123");
		assertEquals("123", subcategoria1.getIdSubcategoria());
	}

	@Test
	void testSetNombre() {
		subcategoria1.setNombre("Ciencia Ficción");
		assertEquals("Ciencia Ficción", subcategoria1.getNombre());
	}

	@Test
	void testGetIdSubcategoria() {
		subcategoria1.setSubcategoria("456");
		assertEquals("456", subcategoria1.getIdSubcategoria());
	}

	@Test
	void testGetNombre() {
		assertEquals("Ficción", subcategoria1.getNombre());
	}

	@Test
	void testFindCategoria() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		Categoria categoria = new Categoria("Ciencia Ficción");
		libro.addCategoria(categoria);

		Categoria encontrada = libro.findCategoria("Ciencia Ficción");
		assertNotNull(encontrada);
		assertEquals("Ciencia Ficción", encontrada.getNombre());

		Categoria noEncontrada = libro.findCategoria("Historia");
		assertNull(noEncontrada);
	}

	@Test
	void testHaveCategoria() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		libro.addCategoria(new Categoria("Fantasía"));

		assertTrue(libro.haveCategoria("Fantasía"));
		assertFalse(libro.haveCategoria("Drama"));
	}

	@Test
	void testHaveSubcategoria() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		libro.addSubcategoria(new Subcategoria("Epic"));

		assertTrue(libro.haveSubcategoria("Epic"));
		assertFalse(libro.haveSubcategoria("Romance"));
	}

	@Test
	void testAddCategoriaL() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		Categoria categoria = new Categoria("Aventura");
		libro.addCategoria(categoria);

		assertEquals(1, libro.getCategorias().size());
		assertEquals("Aventura", libro.getCategorias().get(0).getNombre());
	}

	@Test
	void testAddEjemplar() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		Ejemplar ejemplar = new Ejemplar("Bueno", true);
		libro.addEjemplar(ejemplar);

		assertEquals(1, libro.getEjemplares().size());
		assertEquals("Bueno", libro.getEjemplares().get(0).getEstado());
	}

	@Test
	void testAddSubcategoria1() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		Subcategoria subcategoria = new Subcategoria("Suspenso");
		libro.addSubcategoria(subcategoria);

		assertEquals(1, libro.getSubcategorias().size());
		assertEquals("Suspenso", libro.getSubcategorias().get(0).getNombre());
	}

	@Test
	void testSetAnioPublicacion() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		libro.setAnioPublicacion("2022");

		assertEquals("2022", libro.getAnioPublicacion());
	}

	@Test
	void testGetCategorias() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		libro.addCategoria(new Categoria("Misterio"));

		assertNotNull(libro.getCategorias());
		assertEquals(1, libro.getCategorias().size());
	}

	@Test
	void testGetSubcategorias() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "2000");
		libro.addSubcategoria(new Subcategoria("Historias Cortas"));

		assertNotNull(libro.getSubcategorias());
		assertEquals(1, libro.getSubcategorias().size());
	}

	@Test
	void testGetAnioPublicacion() {
		Libro libro = new Libro("Libro de Prueba", "Autor Prueba", "Editor Prueba", "Edición Prueba", "ISBN123",
				"Sinopsis Prueba", "1999");

		assertEquals("1999", libro.getAnioPublicacion());
	}

	@Test
	void testConstructor() {
		Carga carga = new Carga();
		assertNotNull(carga);
	}

	@Test
	void testSetGetNombreLibro() {
		Carga carga = new Carga();
		carga.setNombreLibro("A");
		assertEquals("A", carga.getNombreLibro());
	}

	@Test
	void testSetGetAutor() {
		Carga carga = new Carga();
		carga.setAutor("B");
		assertEquals("B", carga.getAutor());
	}

	@Test
	void testSetGetEditorial() {
		Carga carga = new Carga();
		carga.setEditorial("C");
		assertEquals("C", carga.getEditorial());
	}

	@Test
	void testSetGetEdicion() {
		Carga carga = new Carga();
		carga.setEdicion("D");
		assertEquals("D", carga.getEdicion());
	}

	@Test
	void testSetGetIsbn() {
		Carga carga = new Carga();
		carga.setIsbn("E");
		assertEquals("E", carga.getIsbn());
	}

	@Test
	void testSetGetEstadoFisico() {
		Carga carga = new Carga();
		carga.setEstadoFisico("F");
		assertEquals("F", carga.getEstadoFisico());
	}

	@Test
	void testSetGetSinopsis() {
		Carga carga = new Carga();
		carga.setSinopsis("G");
		assertEquals("G", carga.getSinopsis());
	}

	@Test
	void testSetGetSubcategoria() {
		Carga carga = new Carga();
		carga.setSubcategoria("H");
		assertEquals("H", carga.getSubcategoria());
	}

	@Test
	void testSetGetCategoria() {
		Carga carga = new Carga();
		carga.setCategoria("I");
		assertEquals("I", carga.getCategoria());
	}

	@Test
	void testSetGetDisponibilidad() {
		Carga carga = new Carga();
		carga.setDisponibilidad("J");
		assertEquals("J", carga.getDisponibilidad());
	}

	@Test
	void testSetGetAnioPublicacion() {
		Carga carga = new Carga();
		carga.setAnioPublicacion("K");
		assertEquals("K", carga.getAnioPublicacion());
	}

	@Test
	void testLetraAIndiceValid() {
		assertEquals(0, Carga.letraAIndice("A"));
		assertEquals(25, Carga.letraAIndice("Z"));
		assertEquals(12, Carga.letraAIndice("M"));
	}

	@Test
	void testLetraAIndiceInvalid() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			Carga.letraAIndice("");
		});
		assertEquals("La letra debe ser una letra de la A a la Z.", exception1.getMessage());

		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			Carga.letraAIndice("AA");
		});
		assertEquals("La letra debe ser una letra de la A a la Z.", exception2.getMessage());

		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			Carga.letraAIndice("1");
		});
		assertEquals("La letra debe ser una letra de la A a la Z.", exception3.getMessage());

		Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
			Carga.letraAIndice("a");
		});
		assertEquals("La letra debe ser una letra de la A a la Z.", exception4.getMessage());
	}

	@Test
	void testGenerarCodigoBarrasSvgStream() {
		CodeGenerator codeGenerator = new CodeGenerator();
		String inputData = "1234567890";

		try {
			ByteArrayInputStream barcodeStream = codeGenerator.generarCodigoBarrasPngStream(inputData);
			assertNotNull(barcodeStream, "The barcode stream should not be null.");

			// Optionally, verify the PNG signature (first 8 bytes of a PNG file)
			byte[] pngSignature = new byte[] { (byte) 137, 80, 78, 71, 13, 10, 26, 10 };
			byte[] signatureRead = new byte[8];
			int bytesRead = barcodeStream.read(signatureRead);
			assertEquals(8, bytesRead, "Should read 8 bytes for PNG signature.");
			assertArrayEquals(pngSignature, signatureRead, "The file is not a valid PNG image.");

		} catch (WriterException | IOException e) {
			fail("Exception occurred during barcode generation: " + e.getMessage());
		}
	}

	@Test
	void testAddSubcategoria() {
		Categoria categoria = new Categoria("Literatura");
		Subcategoria subcategoria = new Subcategoria("Novela");
		categoria.addSubcategoria(subcategoria);

		assertEquals(1, categoria.getSubcategorias().size());
		assertEquals("Novela", categoria.getSubcategorias().get(0).getNombre());
	}

	@Test
	void testSetSubcategorias() {
		Categoria categoria = new Categoria("Ciencia");
		Subcategoria subcategoria3 = new Subcategoria("Física");
		Subcategoria subcategoria2 = new Subcategoria("Química");

		categoria.setSubcategorias(subcategoria3);
		categoria.setSubcategorias(subcategoria2);

		assertEquals(2, categoria.getSubcategorias().size());
		assertEquals("Física", categoria.getSubcategorias().get(0).getNombre());
		assertEquals("Química", categoria.getSubcategorias().get(1).getNombre());
	}

	@Test
	void testFindSubcategoria() {
		Categoria categoria = new Categoria("Arte");
		Subcategoria subcategoria3 = new Subcategoria("Pintura");
		Subcategoria subcategoria2 = new Subcategoria("Escultura");

		categoria.addSubcategoria(subcategoria3);
		categoria.addSubcategoria(subcategoria2);

		assertTrue(categoria.findSubcategoria("Pintura"));
		assertTrue(categoria.findSubcategoria("Escultura"));
		assertFalse(categoria.findSubcategoria("Música"));
	}

	@Test
	void testGetIdCategoria() {
		Categoria categoria = new Categoria("Historia");
		categoria.setIdCategoria("123");

		assertEquals("123", categoria.getIdCategoria());
	}

	@Test
	void testSetGetAutorL() {
		libro1.setAutor("Gabriel García Márquez");
		assertEquals("Gabriel García Márquez", libro1.getAutor());
	}

	@Test
	void testSetGetEdicionL() {
		libro1.setEdicion("Primera Edición");
		assertEquals("Primera Edición", libro1.getEdicion());
	}

	@Test
	void testSetGetIsbnL() {
		libro1.setIsbn("9783161484100");
		assertEquals("9783161484100", libro1.getIsbn());
	}

	@Test
	void testSetGetSinopsisL() {
		libro1.setSinopsis("Una sinopsis interesante del libro.");
		assertEquals("Una sinopsis interesante del libro.", libro1.getSinopsis());
	}
}
