package edu.eci.cvds.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.library.model.Libro;


@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {

    @Query("{ $or: [ { 'nombreLibro': ?0 }, { 'autor': ?1 }, { 'editor': ?2 }, { 'edicion': ?3 } ] }")
    static Libro buscarPorCualquierCampo(String nombreLibro, String autor, String editor, String edicion) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCualquierCampo'");
    }
}
