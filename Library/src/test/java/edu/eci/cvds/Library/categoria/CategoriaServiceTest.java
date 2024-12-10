package edu.eci.cvds.Library.categoria;

import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.repository.CategoriaRepository;
import edu.eci.cvds.library.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria1;
    private Categoria categoria2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoria1 = new Categoria("Ficción");
        categoria1.setId("1");

        categoria2 = new Categoria("Ciencia Ficción");
        categoria2.setId("2");
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
