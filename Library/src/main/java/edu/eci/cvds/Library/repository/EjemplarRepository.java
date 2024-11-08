package edu.eci.cvds.Library.repository;

import edu.eci.cvds.Library.model.Ejemplar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {
    
}
