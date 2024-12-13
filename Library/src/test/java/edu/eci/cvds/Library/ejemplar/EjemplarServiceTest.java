package edu.eci.cvds.Library.ejemplar;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.repository.EjemplarRepository;
import edu.eci.cvds.library.service.EjemplarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EjemplarServiceTest {

    @Mock
    private EjemplarRepository ejemplarRepository;

    @InjectMocks
    private EjemplarService ejemplarService;

    private Ejemplar ejemplar1;
    private Ejemplar ejemplar2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ejemplar1 = new Ejemplar("Nuevo", true);
        ejemplar1.setId("1");

        ejemplar2 = new Ejemplar("Usado", false);
        ejemplar2.setId("2");
    }

    @Test
    void testObtenerEjemplarPorId() {
        when(ejemplarRepository.findById("1")).thenReturn(Optional.of(ejemplar1));

        Optional<Ejemplar> ejemplarOpt = ejemplarService.obtenerEjemplarPorId("1");

        assertTrue(ejemplarOpt.isPresent());
        assertEquals("Nuevo", ejemplarOpt.get().getEstado());
    }

    // @Test
    // void testCrearEjemplar() {
    //     // Simulamos que el método save devuelve el ejemplar1
    //     when(ejemplarRepository.save(any(Ejemplar.class))).thenReturn(ejemplar1);
        
    //     // Llamamos al servicio para crear el ejemplar
    //     Ejemplar resultado = ejemplarService.crearEjemplar(ejemplar1);

    //     // Aseguramos que el resultado no sea null
    //     assertNotNull(resultado);

    //     // Aseguramos que el estado del ejemplar sea "Nuevo"
    //     assertEquals("Nuevo", resultado.getEstado());

    //     // Verificamos que se haya llamado al repositorio para guardar el ejemplar
    //     verify(ejemplarRepository, times(2)).save(ejemplar1); // Verificamos dos veces debido a la segunda llamada save dentro del servicio
    // }

    @Test
    void testEliminarEjemplar() {
        doNothing().when(ejemplarRepository).deleteById("1");

        ejemplarService.eliminarEjemplar("1");

        verify(ejemplarRepository, times(1)).deleteById("1");
    }
}
