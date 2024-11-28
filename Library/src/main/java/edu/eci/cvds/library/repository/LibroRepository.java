package edu.eci.cvds.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import edu.eci.cvds.library.model.Libro;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Libro, que permite interactuar con la base de
 * datos MongoDB.
 * Extiende la interfaz {@link MongoRepository} para proporcionar acceso CRUD a
 * los documentos de la colección "libros".
 * Se define un método personalizado para realizar búsquedas con múltiples
 * condiciones.
 * 
 */
@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {

    /**
     * Busca los libros que coinciden con alguno de los campos: nombre del libro,
     * autor, editor o edición.
     * 
     * Esta consulta utiliza una operación `$or` para combinar las búsquedas por los
     * diferentes campos.
     * Si se proporciona un valor para cualquiera de los parámetros, se devolverán
     * los libros que coincidan con al menos uno de esos valores.
     * 
     * @param nombreLibro nombre del libro a buscar.
     * @param autor       autor del libro a buscar.
     * @param editor      editor del libro a buscar.
     * @param edicion     edición del libro a buscar.
     * @return una lista de libros que coinciden con los criterios de búsqueda.
     */
    @Query("{ $and: [ { 'nombreLibro': ?0 }, { 'autor': ?1 }, { 'editor': ?2 }, { 'edicion': ?3 } ] }")
    List<Libro> buscarPorCualquierCampo(String nombreLibro, String autor, String editor, String edicion);

    List<Libro> findByNombreLibroContainingIgnoreCase(String nombre);

    List<Libro> findByAutorContainingIgnoreCase(String autor);

    Optional<Libro> findByIsbn(String isbn);
}
