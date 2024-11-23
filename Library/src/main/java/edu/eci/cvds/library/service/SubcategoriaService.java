package edu.eci.cvds.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import edu.eci.cvds.library.model.Subcategoria;
import edu.eci.cvds.library.repository.SubcategoriaRepository;

@Service
public class SubcategoriaService {

	private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
	public SubcategoriaService(SubcategoriaRepository subcategoriaRepository){
		this.subcategoriaRepository = subcategoriaRepository;
	}

	/**
	 * Crea o actualiza una subcategoría en la base de datos.
	 * 
	 * @param subcategoria Objeto subcategoría a crear o actualizar.
	 * @return la subcategoría creada o actualizada.
	 */
	public Subcategoria crearOActualizarSubcategoria(Subcategoria subcategoria) {
		return subcategoriaRepository.save(subcategoria);
	}

	/**
	 * Obtiene todas las subcategorías almacenadas en la base de datos.
	 * 
	 * @return lista de todas las subcategorías.
	 */
	public List<Subcategoria> obtenerTodasLasSubcategorias() {
		return subcategoriaRepository.findAll();
	}

	/**
	 * Obtiene una subcategoría por su ID.
	 * 
	 * @param idSubcategoria ID de la subcategoría a buscar.
	 * @return un Optional con la subcategoría encontrada o vacío si no existe.
	 */
	public Optional<Subcategoria> obtenerSubcategoriaPorId(String idSubcategoria) {
		return subcategoriaRepository.findById(idSubcategoria);
	}

	/**
	 * Elimina una subcategoría por su ID.
	 * 
	 * @param idSubcategoria ID de la subcategoría a eliminar.
	 */
	public void eliminarSubcategoria(String idSubcategoria) {
		subcategoriaRepository.deleteById(idSubcategoria);
	}
}