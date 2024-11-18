package edu.eci.cvds.Library;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.Optional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import edu.eci.cvds.Library.service.*;

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

        libro1 = new Libro();
        libro1.setId("1");
        libro1.setNombreLibro("Libro Uno");

        libro2 = new Libro();
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
