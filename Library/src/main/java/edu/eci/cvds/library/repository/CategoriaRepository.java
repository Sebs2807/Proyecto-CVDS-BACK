package edu.eci.cvds.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.eci.cvds.library.model.Categoria;

/**
 * Repositorio de la entidad Categoria que se conecta a la base de datos
 * MongoDB.
 * Permite realizar operaciones CRUD sobre la colección de categorías.
 * Extiende la interfaz {@link MongoRepository} para proporcionar métodos
 * básicos de acceso a datos,
 * como guardar, buscar, actualizar y eliminar entidades de la base de datos.
 */
public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    /**
     * Busca una categoría por su nombre.
     * 
     * @param nombre El nombre de la categoría a buscar.
     * @return La categoría con el nombre proporcionado, o null si no se encuentra.
     */
    Categoria findCategoriaByNombre(String nombre);
}
