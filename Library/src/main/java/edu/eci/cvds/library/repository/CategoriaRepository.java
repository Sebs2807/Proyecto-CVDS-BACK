package edu.eci.cvds.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.library.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

}