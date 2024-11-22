package edu.eci.cvds.Library.repository;

import edu.eci.cvds.Library.model.Subcategoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubcategoriaRepository extends MongoRepository<Subcategoria, Integer> {

}