package edu.eci.cvds.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

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

    /**
     * Obtiene todos los libros buscando por el nombre del mismo
     * 
     * @param nombre nombre del libro a buscar
     * @return Lista de todos los libros filtrados por nombre
     */
    public List<Libro> obtenerLibrosPorNombre(String nombre) {
        return libroRepository.findByNombreLibroContainingIgnoreCase(nombre);
    }

    /**
     * Obtiene todos los libros filtrados por autor
     * 
     * @param autor autor del libro a buscar
     * @return Lista de todos los libros filtrados por autores
     */
    public List<Libro> obtenerLibrosPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    /**
     * Obtiene todos los libros filtrados por isbn
     * 
     * @param isbn isbn del libro a buscar
     * @return Un Optional con el libro que corresponde al isbn de parámetro
     */
    public Optional<Libro> obtenerLibroPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn);
    }

    public Page<Libro> findByFieldWithRegexExcluding(String fieldName, String regex, Pageable pageable) {
        Criteria criteria = Criteria.where(fieldName).regex(regex, "i"); 
        Query query = new Query(criteria).with(pageable); 
        
        // Excluir los campos isbn y sinopsis
        query.fields().exclude("categorias").exclude("subcategorias").exclude("ejemplares");
        List<Libro> libros = mongoTemplate.find(query, Libro.class);
        long total = mongoTemplate.count(query, Libro.class); 

        return new PageImpl<>(libros, pageable, total);
    }
}
