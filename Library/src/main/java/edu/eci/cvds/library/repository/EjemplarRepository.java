package edu.eci.cvds.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;

/**
 * Repositorio de la entidad Ejemplar que se conecta a la base de datos MongoDB.
 * Permite realizar operaciones CRUD sobre la colección de ejemplares.
 * Extiende la interfaz {@link MongoRepository} para proporcionar métodos
 * básicos de acceso a datos,
 * como guardar, buscar, actualizar y eliminar ejemplares en la base de datos.
 * 
 */
@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {
    /**
     * @return Lista de ejemplares disponibles.
     */
    List<Ejemplar> findByDisponibleTrue();

    @Query(value = "{'id': { $regex: ?0}}")
    Page<Ejemplar> findByIdEjemplar(String regex, Pageable pageable);

    List<Ejemplar> findByLibro(String libroId);
}
