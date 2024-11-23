package edu.eci.cvds.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Crea o actualiza una categoría en la base de datos.
     * 
     * @param categoria Objeto categoría a crear o actualizar.
     * @return la categoría creada o actualizada.
     */
    public Categoria crearOActualizarCategoria(Categoria subcategoria) {
        return categoriaRepository.save(subcategoria);
    }

    /**
     * Obtiene todas las categorías almacenadas en la base de datos.
     * 
     * @return lista de todas las categorías.
     */
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     * 
     * @param idCategoria ID de la categoría a buscar.
     * @return un Optional con la categoría encontrada o vacío si no existe.
     */
    public Optional<Categoria> obtenerCategoriaPorId(String idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    /**
     * Elimina una categoría por su ID.
     * 
     * @param idCategoria ID de la categoría a eliminar.
     */
    public void eliminarCategoria(String idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}