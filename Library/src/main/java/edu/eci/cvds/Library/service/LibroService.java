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

    /**
     * Busca los libros que coinciden en el titulo
     * @param titulo Titulo del libro a buscar
     * @return Libros que coinciden en el título
     */
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
    
    /**
     * Busca los libros que coinciden en el autor
     * @param titulo Nombre del autor del libro a buscar
     * @return Libros que coinciden en el título
     */
    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    /**
     * Busca los libros que coinciden en la categoria
     * @param titulo Categoria del libro a buscar
     * @return Libros que coinciden en la categoria
     */
    public List<Libro> buscarLibrosPorCategoria(String categoria) {
        return libroRepository.findByCategoria(categoria);
    }

    /**
     * Busca los libros que coinciden en el año de publicación
     * @param titulo Año de publicación del libro a buscar
     * @return Libros que coinciden en el año de publicación
     */
    public List<Libro> buscarLibrosPorAnioPublicacion(Integer anioPublicacion) {
        return libroRepository.findByAnioPublicacion(anioPublicacion);
    }
}
