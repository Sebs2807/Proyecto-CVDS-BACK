package edu.eci.cvds.Library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.Library.model.Libro;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {
}
