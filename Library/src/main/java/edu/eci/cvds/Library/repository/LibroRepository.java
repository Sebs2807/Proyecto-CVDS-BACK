package edu.eci.cvds.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.library.model.Libro;

public interface LibroRepository extends MongoRepository<Libro, String> {
}
