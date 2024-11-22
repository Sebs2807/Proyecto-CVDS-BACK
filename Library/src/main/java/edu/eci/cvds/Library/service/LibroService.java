package edu.eci.cvds.Library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 
     * @param libro Objeto libro a crear.
     * @return el libro creado.
     */
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Obtiene todos los libros almacenados en la base de datos.
     * 
     * @return lista de todos los libros.
     */
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    /**
     * Obtiene un libro por su ID.
     * 
     * @param id ID del libro a buscar.
     * @return un Optional con el libro encontrado o vacío si no existe.
     */
    public Optional<Libro> obtenerLibroPorId(String id) {
        return libroRepository.findById(id);
    }

    /**
     * Elimina un libro por su ID.
     * 
     * @param id ID del libro a eliminar.
     */
    public void eliminarLibro(String id) {
        libroRepository.deleteById(id);
    }

    /**
     * Actualiza un libro ya existente en la base de datos
     * 
     * @param libro Libro a actualizar
     * @return Libro actualizado
     */
    public Libro actualizarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // public void cargarLibrosDesdeJson(String rutaArchivo) {
    //     ObjectMapper mapper = new ObjectMapper();
    //     try {

    //         List<Libro> libros = mapper.readValue(new File(rutaArchivo), new TypeReference<List<Libro>>() {
    //         });

    //         libroRepository.saveAll(libros);
    //     } catch (IOException e) {

    //         e.printStackTrace();
    //         throw new RuntimeException("Error al cargar los libros desde el archivo JSON", e);
    //     }
    // }

}
