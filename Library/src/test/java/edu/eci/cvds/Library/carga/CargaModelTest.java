package edu.eci.cvds.Library.carga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.library.model.Carga;

class CargaModelTest {

    private Carga carga;

    @BeforeEach
    public void setUp() {
        carga = new Carga();
    }

    @Test
    void testSetGetNombreLibro() {
        carga.setNombreLibro("A");
        assertEquals("A", carga.getNombreLibro());
    }

    @Test
    void testSetGetAutor() {
        carga.setAutor("B");
        assertEquals("B", carga.getAutor());
    }

    @Test
    void testSetGetEditorial() {
        carga.setEditorial("C");
        assertEquals("C", carga.getEditorial());
    }

    @Test
    void testSetGetEdicion() {
        carga.setEdicion("D");
        assertEquals("D", carga.getEdicion());
    }

    @Test
    void testSetGetIsbn() {
        carga.setIsbn("E");
        assertEquals("E", carga.getIsbn());
    }

    @Test
    void testSetGetEstadoFisico() {
        carga.setEstadoFisico("F");
        assertEquals("F", carga.getEstadoFisico());
    }

    @Test
    void testSetGetSinopsis() {
        carga.setSinopsis("G");
        assertEquals("G", carga.getSinopsis());
    }

    @Test
    void testSetGetSubcategoria() {
        carga.setSubcategoria("H");
        assertEquals("H", carga.getSubcategoria());
    }

    @Test
    void testSetGetCategoria() {
        carga.setCategoria("I");
        assertEquals("I", carga.getCategoria());
    }

    @Test
    void testSetGetDisponibilidad() {
        carga.setDisponibilidad("J");
        assertEquals("J", carga.getDisponibilidad());
    }

    @Test
    void testSetGetAnioPublicacion() {
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
}
