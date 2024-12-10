package edu.eci.cvds.Library.busqueda;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import edu.eci.cvds.library.configuration.RestTemplateConfig;
import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.service.LibroService;
import edu.eci.cvds.library.service.EjemplarService;
import edu.eci.cvds.library.service.BusquedaService;

import java.util.List;

@Import(RestTemplateConfig.class)
class BusquedaServiceTest {

    @Mock
    private LibroService libroService;

    @Mock
    private EjemplarService ejemplarService;

    private BusquedaService busquedaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        busquedaService = new BusquedaService(libroService, ejemplarService);
    }

    @Test
    void testBuscarLibrosPorAtributo() {
        String regex = "Spring";
        String parametro = "nombreLibro";
        int page = 0;
        int size = 10;

        List<Libro> libros = List.of(new Libro("Spring in Action", "12345", "norma", "La pola", "231-982-X", "Una en un millon", "2020"));
        Page<Libro> pageResult = new PageImpl<>(libros, PageRequest.of(page, size), libros.size());

        when(libroService.findByFieldWithRegexExcluding(parametro, regex, PageRequest.of(page, size)))
                .thenReturn(pageResult);

        Page<Libro> result = busquedaService.buscarLibrosPorAtributo(regex, parametro, page, size);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());  
        assertEquals("Spring in Action", result.getContent().get(0).getNombreLibro());
    }

    @Test
    void testBuscarEjemplar() {
        String idEjemplar = "12345";
        int page = 0;
        int size = 10;

        Ejemplar ejemplar = new Ejemplar("buen estado", false);
        ejemplar.setId(idEjemplar);

        List<Ejemplar> ejemplares = List.of(ejemplar);
        Page<Ejemplar> pageResult = new PageImpl<>(ejemplares, PageRequest.of(page, size), ejemplares.size());

        when(ejemplarService.findById(idEjemplar, PageRequest.of(page, size)))
                .thenReturn(pageResult);

        Page<Ejemplar> result = busquedaService.buscarEjemplar(idEjemplar, page, size);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("12345", result.getContent().get(0).getId());
    }
}
