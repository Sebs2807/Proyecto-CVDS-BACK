package edu.eci.cvds.library.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.library.model.Ejemplar;
import java.util.List;

@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {
    /**
     * @return Lista de ejemplares disponibles.
     */
    List<Ejemplar> findByDisponibleTrue();
}
