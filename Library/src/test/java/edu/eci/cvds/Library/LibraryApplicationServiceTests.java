package edu.eci.cvds.Library;

import edu.eci.cvds.library.model.Carga;
import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.repository.CategoriaRepository;
import edu.eci.cvds.library.repository.EjemplarRepository;
import edu.eci.cvds.library.repository.LibroRepository;
import edu.eci.cvds.library.repository.SubcategoriaRepository;
import edu.eci.cvds.library.service.*;
import io.jsonwebtoken.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.cvds.library.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = { LibraryApplication.class })
class LibraryApplicationServiceTests {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libro1;
    private Libro libro2;

    @Mock
    private SubcategoriaRepository subcategoriaRepository;

    @InjectMocks
    private SubcategoriaService subcategoriaService;

    private Subcategoria subcategoria1;
    private Subcategoria subcategoria2;

    @Mock
    private EjemplarRepository ejemplarRepository;

    @Mock
    private AzureBlobStorageService azureBlobStorageService;

    @InjectMocks
    private EjemplarService ejemplarService;

    private Ejemplar ejemplar1;
    private Ejemplar ejemplar2;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria1;
    private Categoria categoria2;

    @InjectMocks
    private CargaService cargaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        libro1 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador",
                "2005");
        ;
        libro1.setId("1");
        libro1.setNombreLibro("Libro Uno");

        libro2 = new Libro("hola mundo", "Santiago Diaz", "Norma", "1", "123456xx", "La historia de un programador",
                "2005");
        ;
        libro2.setId("2");
        libro2.setNombreLibro("Libro Dos");

        subcategoria1 = new Subcategoria("Ficción");
        subcategoria1.setSubcategoria("1");

        subcategoria2 = new Subcategoria("Ciencia Ficción");
        subcategoria2.setSubcategoria("2");

        ejemplar1 = new Ejemplar("Nuevo", true);
        ejemplar1.setId("1");

        ejemplar2 = new Ejemplar("Usado", false);
        ejemplar2.setId("2");

        categoria1 = new Categoria("Ficción");
        categoria1.setIdCategoria("1");

        categoria2 = new Categoria("Ciencia Ficción");
        categoria2.setIdCategoria("2");
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
    void testObtenerLibrosPorAutor() {
        when(libroRepository.findByAutorContainingIgnoreCase("Santiago Diaz"))
                .thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> libros = libroService.obtenerLibrosPorAutor("Santiago Diaz");

        assertNotNull(libros);
        assertEquals(2, libros.size());
        assertEquals("Libro Uno", libros.get(0).getNombreLibro());
        assertEquals("Libro Dos", libros.get(1).getNombreLibro());
        verify(libroRepository, times(1)).findByAutorContainingIgnoreCase("Santiago Diaz");
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

    // @Test
    // void testObtenerTodosLosLibros() {
    // when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

    // List<Libro> libros = libroService.obtenerTodosLosLibros();

    // assertNotNull(libros);
    // assertEquals(2, libros.size());
    // assertEquals("Libro Uno", libros.get(0).getNombreLibro());
    // assertEquals("Libro Dos", libros.get(1).getNombreLibro());
    // }

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

    @Test
    void testCrearOActualizarSubcategoria() {
        when(subcategoriaRepository.save(any(Subcategoria.class))).thenReturn(subcategoria1);

        Subcategoria resultado = subcategoriaService.crearOActualizarSubcategoria(subcategoria1);

        assertNotNull(resultado);
        assertEquals("Ficción", resultado.getNombre());
        verify(subcategoriaRepository, times(1)).save(subcategoria1);
    }

    @Test
    void testObtenerSubcategoriaPorNombre() {
        when(subcategoriaRepository.findSubcategoriaByNombre("Ficción")).thenReturn(subcategoria1);

        Subcategoria resultado = subcategoriaService.obtenerSubcategoriaPorNombre("Ficción");

        assertNotNull(resultado);
        assertEquals("Ficción", resultado.getNombre());
        verify(subcategoriaRepository, times(1)).findSubcategoriaByNombre("Ficción");
    }

    @Test
    void testCrearOActualizarCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria1);

        Categoria resultado = categoriaService.crearOActualizarCategoria(categoria1);

        assertNotNull(resultado);
        assertEquals("Ficción", resultado.getNombre());
        verify(categoriaRepository, times(1)).save(categoria1);
    }

    @Test
    void testObtenerCategoriaPorId() {
        when(categoriaRepository.findById("1")).thenReturn(Optional.of(categoria1));

        Optional<Categoria> categoriaOpt = categoriaService.obtenerCategoriaPorId("1");

        assertTrue(categoriaOpt.isPresent());
        assertEquals("Ficción", categoriaOpt.get().getNombre());
    }

    @Test
    void testObtenerCategoriaPorNombre() {
        when(categoriaRepository.findCategoriaByNombre("Ficción")).thenReturn(categoria1);

        Categoria resultado = categoriaService.obtenerCategoriaPorNombre("Ficción");

        assertNotNull(resultado);
        assertEquals("Ficción", resultado.getNombre());
        verify(categoriaRepository, times(1)).findCategoriaByNombre("Ficción");
    }

    @Test
    void testEliminarCategoria() {
        doNothing().when(categoriaRepository).deleteById("1");

        categoriaService.eliminarCategoria("1");

        verify(categoriaRepository, times(1)).deleteById("1");
    }

    @Test
    void testObtenerTodasLasCategorias() {
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();

        assertNotNull(categorias);
        assertEquals(2, categorias.size());
        assertEquals("Ficción", categorias.get(0).getNombre());
        assertEquals("Ciencia Ficción", categorias.get(1).getNombre());
    }
}
