package edu.eci.cvds.Library.subcategoria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.repository.CategoriaRepository;
import edu.eci.cvds.library.repository.SubcategoriaRepository;
import edu.eci.cvds.library.service.SubcategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class SubcategoriaServiceTest {

    @Mock
    private SubcategoriaRepository subcategoriaRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private SubcategoriaService subcategoriaService;

    private Subcategoria subcategoria1;
    private Subcategoria subcategoria2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        subcategoria1 = new Subcategoria("Ficción");
        subcategoria1.setId("1");

        subcategoria2 = new Subcategoria("Ciencia Ficción");
        subcategoria2.setId("2");
    }

    @Test
    void testObtenerTodasLasSubcategorias() {
        when(subcategoriaRepository.findAll()).thenReturn(Arrays.asList(subcategoria1, subcategoria2));

        List<Subcategoria> subcategorias = subcategoriaService.obtenerTodasLasSubcategorias();

        assertNotNull(subcategorias);
        assertEquals(2, subcategorias.size());
        assertEquals("Ficción", subcategorias.get(0).getNombre());
        assertEquals("Ciencia Ficción", subcategorias.get(1).getNombre());
    }

    @Test
    void testObtenerSubcategoriaPorId() {
        when(subcategoriaRepository.findById("1")).thenReturn(Optional.of(subcategoria1));

        Optional<Subcategoria> subcategoriaOpt = subcategoriaService.obtenerSubcategoriaPorId("1");

        assertTrue(subcategoriaOpt.isPresent());
        assertEquals("Ficción", subcategoriaOpt.get().getNombre());
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
    void testEliminarSubcategoria() {
        doNothing().when(subcategoriaRepository).deleteById("1");

        subcategoriaService.eliminarSubcategoria("1");

        verify(subcategoriaRepository, times(1)).deleteById("1");
    }

    @Test
    void testObtenerSubcategoriasPorCategoria() {
        String categoriaId = "123";
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        
        List<Subcategoria> subcategorias = Arrays.asList(new Subcategoria("Subcategoria 1"), new Subcategoria("Subcategoria 2"));

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));
        when(subcategoriaRepository.findByCategoriasContaining(categoria)).thenReturn(subcategorias);

        List<Subcategoria> resultado = subcategoriaService.obtenerSubcategoriasPorCategoria(categoriaId);

        assertEquals(2, resultado.size());
        assertEquals("Subcategoria 1", resultado.get(0).getNombre());
        assertEquals("Subcategoria 2", resultado.get(1).getNombre());

        verify(categoriaRepository, times(1)).findById(categoriaId);
        verify(subcategoriaRepository, times(1)).findByCategoriasContaining(categoria);
    }

    @Test
    void testObtenerSubcategoriasPorCategoriaCategoriaNoEncontrada() {
        String categoriaId = "123";
        
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            subcategoriaService.obtenerSubcategoriasPorCategoria(categoriaId);
        });
    }

    @Test
    void testObtenerSubcategoriaPorNombre() {
        String nombreSubcategoria = "Ficción";
        Subcategoria subcategoriaEsperada = new Subcategoria(nombreSubcategoria);
        subcategoriaEsperada.setId("1");

        when(subcategoriaRepository.findSubcategoriaByNombre(nombreSubcategoria)).thenReturn(subcategoriaEsperada);

        Subcategoria subcategoriaResultante = subcategoriaService.obtenerSubcategoriaPorNombre(nombreSubcategoria);

        assertNotNull(subcategoriaResultante);
        assertEquals(nombreSubcategoria, subcategoriaResultante.getNombre());

        verify(subcategoriaRepository, times(1)).findSubcategoriaByNombre(nombreSubcategoria);
    }

}