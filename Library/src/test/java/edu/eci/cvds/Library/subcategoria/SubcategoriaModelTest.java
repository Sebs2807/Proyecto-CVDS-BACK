package edu.eci.cvds.Library.subcategoria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.library.model.Subcategoria;

class SubcategoriaModelTest {

    private Subcategoria subcategoria1;

    @BeforeEach
    public void setUp() {
        subcategoria1 = new Subcategoria("Ficción");
    }

    @Test
    void testSetNombre() {
        subcategoria1.setNombre("Ciencia Ficción");
        assertEquals("Ciencia Ficción", subcategoria1.getNombre());
    }

    @Test
    void testGetNombre() {
        assertEquals("Ficción", subcategoria1.getNombre());
    }
}
