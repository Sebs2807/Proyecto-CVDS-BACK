package edu.eci.cvds.Library;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.service.SubcategoriaService;

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
	void testEliminarSubcategoria() {
		subcategoriaService.eliminarSubcategoria("1");
		Optional<Subcategoria> subcategoria = subcategoriaService.obtenerSubcategoriaPorId("1");
		assertFalse(subcategoria.isPresent());
	}

}
