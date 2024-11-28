package edu.eci.cvds.Library;

import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.repository.LibroRepository;
import edu.eci.cvds.library.service.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.cvds.library.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {LibraryApplication.class})
class LibraryApplicationServiceTests {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        libro1 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador", "2005");;
        libro1.setId("1");
        libro1.setNombreLibro("Libro Uno");

        libro2 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador", "2005");;
        libro2.setId("2");
        libro2.setNombreLibro("Libro Dos");
    }

    @Test
    void testCrearLibro() {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro1);

        Libro resultado = libroService.crearLibro(libro1);

        assertNotNull(resultado);
        assertEquals("Libro Uno", resultado.getNombreLibro());
        verify(libroRepository, times(1)).save(libro1);
    }

    @Test
    void testObtenerTodosLosLibros() {
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> libros = libroService.obtenerTodosLosLibros();

        assertNotNull(libros);
        assertEquals(2, libros.size());
        assertEquals("Libro Uno", libros.get(0).getNombreLibro());
        assertEquals("Libro Dos", libros.get(1).getNombreLibro());
    }

    @Test
    void testObtenerLibroPorId() {
        when(libroRepository.findById("1")).thenReturn(Optional.of(libro1));

        Optional<Libro> libroOpt = libroService.obtenerLibroPorId("1");

        assertTrue(libroOpt.isPresent());
        assertEquals("Libro Uno", libroOpt.get().getNombreLibro());
    }

    @Test
    void testEliminarLibro() {
        doNothing().when(libroRepository).deleteById("1");

        libroService.eliminarLibro("1");

        verify(libroRepository, times(1)).deleteById("1");
    }

    @Test
    void testActualizarLibro() {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro1);

        Libro resultado = libroService.actualizarLibro(libro1);

        assertNotNull(resultado);
        assertEquals("Libro Uno", resultado.getNombreLibro());
        verify(libroRepository, times(1)).save(libro1);
    }
}
