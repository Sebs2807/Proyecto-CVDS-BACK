package edu.eci.cvds.Library.repository;

import edu.eci.cvds.Library.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LibroRepository extends MongoRepository<Libro, String> {
    // Buscar libros por título
    List<Libro> findByTitulo(String titulo);
    // Busca libros por autor
    List<Libro> findByAutor(String autor);
    // Busca libros por categoria
    List<Libro> findByCategoria(String categoria);
    // Busca libros por año de publicación
    List<Libro> findByAnioPublicacion(Integer anioPublicacion);
}
