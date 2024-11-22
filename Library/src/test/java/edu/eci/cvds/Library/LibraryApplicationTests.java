package edu.eci.cvds.Library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.Library.model.Subcategoria;
import edu.eci.cvds.Library.service.SubcategoriaService;

@SpringBootTest
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private SubcategoriaService subcategoriaService;

	@Test
	void testObtenerTodasLasSubcategorias() {
		assertNotNull(subcategoriaService.obtenerTodasLasSubcategorias());
	}

	@Test
	void testObtenerSubcategoriaPorId() {
		Optional<Subcategoria> subcategoria = subcategoriaService.obtenerSubcategoriaPorId("1");
		assertTrue(subcategoria.isPresent());
	}

	@Test
	void testEliminarSubcategoria() {
		subcategoriaService.eliminarSubcategoria("1");
		Optional<Subcategoria> subcategoria = subcategoriaService.obtenerSubcategoriaPorId("1");
		assertFalse(subcategoria.isPresent());
	}

}
