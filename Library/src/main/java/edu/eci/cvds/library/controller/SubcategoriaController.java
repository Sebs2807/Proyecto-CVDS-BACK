package edu.eci.cvds.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import edu.eci.cvds.Library.model.Subcategoria;
import edu.eci.cvds.Library.service.SubcategoriaService;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController {

	@Autowired
	private SubcategoriaService subcategoriaService;

	/**
	 * Crea o actualiza una subcategoría.
	 * 
	 * @param subcategoria Objeto subcategoría a crear o actualizar.
	 * @return ResponseEntity con la subcategoría creada o actualizada.
	 */
	@PostMapping
	public ResponseEntity<Subcategoria> crearOActualizarSubcategoria(@RequestBody Subcategoria subcategoria) {
		return ResponseEntity.ok(subcategoriaService.crearOActualizarSubcategoria(subcategoria));
	}

	/**
	 * Obtiene todas las subcategorías.
	 * 
	 * @return ResponseEntity con la lista de todas las subcategorías.
	 */
	@GetMapping
	public ResponseEntity<List<Subcategoria>> obtenerTodasLasSubcategorias() {
		return ResponseEntity.ok(subcategoriaService.obtenerTodasLasSubcategorias());
	}

	/**
	 * Obtiene una subcategoría por su ID.
	 * 
	 * @param idSubcategoria ID de la subcategoría a buscar.
	 * @return ResponseEntity con la subcategoría encontrada o 404 si no existe.
	 */
	@GetMapping("/{idSubcategoria}")
	public ResponseEntity<Subcategoria> obtenerSubcategoriaPorId(@PathVariable String idSubcategoria) {
		Optional<Subcategoria> subcategoriaProvisional = subcategoriaService.obtenerSubcategoriaPorId(idSubcategoria);

		if (subcategoriaProvisional.isPresent()) {
			return ResponseEntity.ok(subcategoriaProvisional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Elimina una subcategoría por su ID.
	 * 
	 * @param idSubcategoria ID de la subcategoría a eliminar.
	 * @return ResponseEntity sin contenido si se elimina exitosamente.
	 */
	@DeleteMapping("/{idSubcategoria}")
	public ResponseEntity<Void> eliminarSubcategoria(@PathVariable String idSubcategoria) {
		subcategoriaService.eliminarSubcategoria(idSubcategoria);
		return ResponseEntity.noContent().build();
	}
}