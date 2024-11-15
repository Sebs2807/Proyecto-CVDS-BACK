package edu.eci.cvds.Library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    /**
     * Crea un nuevo libro en la base de datos.
     * @param libro Objeto libro a crear.
     * @return el libro creado.
     */
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Obtiene todos los libros almacenados en la base de datos.
     * @return lista de todos los libros.
     */
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    /**
     * Obtiene un libro por su ID.
     * @param id ID del libro a buscar.
     * @return un Optional con el libro encontrado o vacío si no existe.
     */
    public Optional<Libro> obtenerLibroPorId(String id) {
        return libroRepository.findById(id);
    }

    /**
     * Elimina un libro por su ID.
     * @param id ID del libro a eliminar.
     */
    public void eliminarLibro(String id) {
        libroRepository.deleteById(id);
    }

    public void cargarLibrosDesdeJson(String rutaArchivo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Leer el archivo JSON y convertirlo a una lista de libros
            List<Libro> libros = mapper.readValue(new File(rutaArchivo), new TypeReference<List<Libro>>(){});
            
            // Guardar todos los libros en la base de datos
            libroRepository.saveAll(libros);
        } catch (IOException e) {
            // Manejo adecuado de excepciones
            e.printStackTrace();  // O puedes usar un logger
            throw new RuntimeException("Error al cargar los libros desde el archivo JSON", e);
        }
    }
    
}
