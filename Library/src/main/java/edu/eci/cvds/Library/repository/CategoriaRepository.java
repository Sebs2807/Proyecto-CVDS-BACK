package edu.eci.cvds.Library.repository;

import edu.eci.cvds.Library.model.Subcategoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.Library.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {


}