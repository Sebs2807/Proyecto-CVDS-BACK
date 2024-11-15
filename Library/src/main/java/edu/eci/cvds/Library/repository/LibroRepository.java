package edu.eci.cvds.Library.repository;

import edu.eci.cvds.Library.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LibroRepository extends MongoRepository<Libro, String> {

}
