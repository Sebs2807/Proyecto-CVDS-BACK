package edu.eci.cvds.Library.libro;

import edu.eci.cvds.library.configuration.RestTemplateConfig;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.repository.LibroRepository;
import edu.eci.cvds.library.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Import(RestTemplateConfig.class)
class LibroServiceTest {


    @Mock
    private LibroRepository libroRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private LibroService libroService;

    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        libro1 = crearLibro("1", "Libro Uno", "Santiago Diaz", "hola mundo", "123456xx", "La historia de un programador", "2005");
        libro2 = crearLibro("2", "Libro Dos", "Santiago Diaz", "hola mundo", "654321yy", "La historia de un programador", "2010");
    }

    private Libro crearLibro(String id, String nombreLibro, String autor, String nombre, String isbn, String descripcion, String año) {
        Libro libro = new Libro(nombre, autor, "Norma", id, isbn, descripcion, año);
        libro.setId(id);
        libro.setNombreLibro(nombreLibro);
        return libro;
    }

    @Test
    void testObtenerLibrosPorNombre() {
        when(libroRepository.findByNombreLibroContainingIgnoreCase("hola mundo"))
                .thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> libros = libroService.obtenerLibrosPorNombre("hola mundo");

        assertNotNull(libros);
        assertEquals(2, libros.size());
        assertEquals("Libro Uno", libros.get(0).getNombreLibro());
        assertEquals("Libro Dos", libros.get(1).getNombreLibro());
        verify(libroRepository, times(1)).findByNombreLibroContainingIgnoreCase("hola mundo");
    }

    @Test
    void testObtenerLibroPorIsbn() {
        when(libroRepository.findByIsbn("123456xx")).thenReturn(Optional.of(libro1));

        Optional<Libro> libroOpt = libroService.obtenerLibroPorIsbn("123456xx");

        assertTrue(libroOpt.isPresent());
        assertEquals("Libro Uno", libroOpt.get().getNombreLibro());
        verify(libroRepository, times(1)).findByIsbn("123456xx");
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
    void testObtenerLibroPorId() {
        when(libroRepository.findById("1")).thenReturn(Optional.of(libro1));

        Optional<Libro> libroOpt = libroService.obtenerLibroPorId("1");

        assertTrue(libroOpt.isPresent());
        assertEquals("Libro Uno", libroOpt.get().getNombreLibro());
        verify(libroRepository, times(1)).findById("1");
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

    @Test
    void testObtenerTodosLosLibros() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Libro> page = new PageImpl<>(Arrays.asList(libro1, libro2), pageable, 2);

        when(libroRepository.findAll(pageable)).thenReturn(page);

        Page<Libro> libros = libroService.obtenerTodosLosLibros(pageable);

        assertNotNull(libros);
        assertEquals(2, libros.getTotalElements());
        assertEquals("Libro Uno", libros.getContent().get(0).getNombreLibro());
        assertEquals("Libro Dos", libros.getContent().get(1).getNombreLibro());
        verify(libroRepository, times(1)).findAll(pageable);
    }

    @Test
    void testObtenerLibrosPorAutor() {
        when(libroRepository.findByAutorContainingIgnoreCase("Santiago Diaz")).thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> libros = libroService.obtenerLibrosPorAutor("Santiago Diaz");

        assertNotNull(libros);
        assertEquals(2, libros.size());
        assertEquals("Santiago Diaz", libros.get(0).getAutor());
        assertEquals("Santiago Diaz", libros.get(1).getAutor());
        verify(libroRepository, times(1)).findByAutorContainingIgnoreCase("Santiago Diaz");
    }

    @Test
    void testFindByFieldWithRegexExcluding() {
        Libro libro1 = new Libro("Libro 1", "Autor 1", "Norma", "1", "123456xx", "Historia", "2005");
        Libro libro2 = new Libro("Libro 2", "Autor 1", "Norma", "2", "654321yy", "Ciencia", "2010");
        Pageable pageable = PageRequest.of(0, 2);

        when(mongoTemplate.find(any(Query.class), eq(Libro.class))).thenReturn(Arrays.asList(libro1, libro2));
        when(mongoTemplate.count(any(Query.class), eq(Libro.class))).thenReturn(2L);

        Page<Libro> libros = libroService.findByFieldWithRegexExcluding("nombreLibro", "Libro", pageable);

        assertNotNull(libros);
        assertEquals(2, libros.getTotalElements());
        assertEquals("Libro 1", libros.getContent().get(0).getNombreLibro());
        assertEquals("Libro 2", libros.getContent().get(1).getNombreLibro());

        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Libro.class));
        verify(mongoTemplate, times(1)).count(any(Query.class), eq(Libro.class));
    }
}
