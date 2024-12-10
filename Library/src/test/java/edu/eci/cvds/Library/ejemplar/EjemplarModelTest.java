package edu.eci.cvds.Library.ejemplar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.library.model.Ejemplar;

class EjemplarModelTest {

    private Ejemplar ejemplar;

    @BeforeEach
    public void setUp() {
        ejemplar = new Ejemplar("Nuevo", true);  // Crea un ejemplar con estado "Nuevo" y disponible
        ejemplar.setId("1");  // Asigna un ID de ejemplo
    }

    @Test
    void testSetGetEstado() {
        ejemplar.setEstado("Dañado");
        assertEquals("Dañado", ejemplar.getEstado(), "El estado debe coincidir con el asignado.");
    }

    @Test
    void testSetGetDisponible() {
        ejemplar.setDisponible(false);
        assertFalse(ejemplar.isDisponible(), "El ejemplar debe estar marcado como no disponible.");
        
        ejemplar.setDisponible(true);
        assertTrue(ejemplar.isDisponible(), "El ejemplar debe estar marcado como disponible.");
    }

    @Test
    void testSetGetCodigoBarras() {
        ejemplar.setCodigoBarras("codigo123");
        assertEquals("codigo123", ejemplar.getCodigoBarras(), "El código de barras debe coincidir con el asignado.");
    }

    @Test
    void testDisponibilidadYEstado() {
        ejemplar.setDisponible(false);
        ejemplar.setEstado("Dañado");

        assertFalse(ejemplar.isDisponible(), "El ejemplar debe estar no disponible después de cambiar la disponibilidad.");
        assertEquals("Dañado", ejemplar.getEstado(), "El estado debe haber sido actualizado.");
    }

    @Test
    void testSetLibro() {
        ejemplar.setLibro(null);
        assertNull(ejemplar.getLibro(), "El libro debe ser null después de asignarlo como null.");
    }
}
