package edu.eci.cvds.Library.libroDeserializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.model.LibroDeserializer;
import edu.eci.cvds.library.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class LibroDeserializerModelTest {

    private ObjectMapper objectMapper;

    @Mock
    private LibroRepository libroRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        LibroDeserializer deserializer = new LibroDeserializer();
        deserializer.libroRepository = libroRepository;
        module.addDeserializer(Libro.class, deserializer);
        objectMapper.registerModule(module);
    }

    @Test
    void testDeserializeLibroSuccess() throws JsonProcessingException {
        Libro libroEsperado = new Libro("Libro 1", "Autor 1", "Norma", "1", "123456xx", "Historia", "2005");
        libroEsperado.setId("123ABC");
        Mockito.when(libroRepository.findById("123ABC")).thenReturn(Optional.of(libroEsperado));

        String json = "\"123ABC\"";
        Libro libroDeserializado = objectMapper.readValue(json, Libro.class);

        assertEquals(libroEsperado.getId(), libroDeserializado.getId(), "El ID deserializado debe coincidir con el esperado.");
    }

    @Test
    void testDeserializeLibroNotFound() {
        Mockito.when(libroRepository.findById("456DEF")).thenReturn(Optional.empty());

        String json = "\"456DEF\"";
        assertThrows(JsonProcessingException.class, () -> {
            objectMapper.readValue(json, Libro.class);
        }, "Debe lanzar una excepción cuando el libro no se encuentra.");
    }
} 
