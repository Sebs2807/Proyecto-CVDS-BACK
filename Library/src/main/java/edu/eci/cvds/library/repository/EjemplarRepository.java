package edu.eci.cvds.Library.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.Library.model.Ejemplar;

@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {
    
}
