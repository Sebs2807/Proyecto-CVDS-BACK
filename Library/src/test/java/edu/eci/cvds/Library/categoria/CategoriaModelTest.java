package edu.eci.cvds.Library.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.library.model.Categoria;

/**
 * Clase de prueba para el modelo Categoria.
 */
class CategoriaModelTest {

    private Categoria categoria;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        categoria = new Categoria();
    }

    /**
     * Prueba para verificar el método getId().
     */
    @Test
    void testGetId() {
        assertNull(categoria.getId(), "El ID debería ser null por defecto");
        String idEsperado = "123ABC";
        categoria.setId(idEsperado);
        assertEquals(idEsperado, categoria.getId(), "El método getId() no devuelve el ID correcto.");
    }

    /**
     * Prueba para verificar el método getNombre().
     */
    @Test
    void testGetNombre() {
        assertNull(categoria.getNombre(), "El nombre debería ser null por defecto");
        categoria = new Categoria("Ciencia Ficción");
        assertEquals("Ciencia Ficción", categoria.getNombre(), "El método getNombre() no devuelve el nombre correcto.");
    }
} 
