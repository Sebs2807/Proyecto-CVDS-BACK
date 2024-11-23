package edu.eci.cvds.library.repository;

import edu.eci.cvds.library.model.Subcategoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubcategoriaRepository extends MongoRepository<Subcategoria, String> {

}