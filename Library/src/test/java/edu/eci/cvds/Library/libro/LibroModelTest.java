package edu.eci.cvds.Library.libro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.library.model.Libro;

class LibroModelTest {

    private Libro libro1;

    @BeforeEach
    public void setUp() {
        libro1 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador", "2005");
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
    void testSetGetAutor() {
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
