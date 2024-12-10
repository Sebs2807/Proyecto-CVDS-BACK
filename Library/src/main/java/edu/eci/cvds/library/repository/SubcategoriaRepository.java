package edu.eci.cvds.library.repository;

import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.model.Subcategoria;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio para la entidad Subcategoria, que permite interactuar con la base
 * de datos MongoDB.
 * Extiende la interfaz {@link MongoRepository} para proporcionar acceso CRUD a
 * los documentos de la colección "subcategorias".
 */
public interface SubcategoriaRepository extends MongoRepository<Subcategoria, String> {

    /**
     * Busca una subcategoría por su nombre.
     * 
     * Este método busca una subcategoría cuyo campo `nombre` coincida con el valor
     * proporcionado como parámetro.
     * 
     * @param nombreSubcategoria nombre de la subcategoría a buscar.
     * @return la subcategoría que coincide con el nombre, o null si no se encuentra
     *         ninguna coincidencia.
     */
    Subcategoria findSubcategoriaByNombre(String nombreSubcategoria);

    List<Subcategoria> findByCategoriasContaining(Categoria categoria);

}
