package edu.eci.cvds.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import edu.eci.cvds.library.model.Categoria;
import edu.eci.cvds.library.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@PreAuthorize("hasRole('ADMIN')")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	/**
	 * Crea o actualiza una categoría.
	 * 
	 * @param categoria Objeto categoría a crear o actualizar.
	 * @return ResponseEntity con la categoría creada o actualizada.
	 */
	@PostMapping
	public ResponseEntity<Categoria> crearOActualizarCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaService.crearOActualizarCategoria(categoria));
	}

	/**
	 * Obtiene todas las categorías.
	 * 
	 * @return ResponseEntity con la lista de todas las categorías.
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
		return ResponseEntity.ok(categoriaService.obtenerTodasLasCategorias());
	}

	/**
	 * Obtiene una categoría por su ID.
	 * 
	 * @param idCategoria ID de la categoría a buscar.
	 * @return ResponseEntity con la categoría encontrada o 404 si no existe.
	 */
	@GetMapping("/{idCategoria}")
	public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable String idCategoria) {
		Optional<Categoria> categoriaProvisional = categoriaService.obtenerCategoriaPorId(idCategoria);

		if (categoriaProvisional.isPresent()) {
			return ResponseEntity.ok(categoriaProvisional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Elimina una categoría por su ID.
	 * 
	 * @param idCategoria ID de la categoría a eliminar.
	 * @return ResponseEntity sin contenido si se elimina exitosamente.
	 */
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<Void> eliminarCategoria(@PathVariable String idCategoria) {
		categoriaService.eliminarCategoria(idCategoria);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Actualiza una categoría por su ID.
	 * 
	 * @param idCategoria ID de la categoría a actualizar.
	 * @param categoria   Objeto categoría con los datos actualizados.
	 * @return ResponseEntity con la categoría actualizada.
	 */
	@PutMapping("/{idCategoria}")
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable String idCategoria,
			@RequestBody Categoria categoria) {
		categoria.setIdCategoria(idCategoria); // Asegura que el ID sea correcto
		Categoria categoriaActualizada = categoriaService.crearOActualizarCategoria(categoria);
		return ResponseEntity.ok(categoriaActualizada);
	}
}