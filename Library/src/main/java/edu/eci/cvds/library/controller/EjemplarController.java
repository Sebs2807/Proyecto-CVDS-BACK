package edu.eci.cvds.library.controller;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.service.EjemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ejemplares")
@PreAuthorize("hasRole('admin') or hasRole('student')")
public class EjemplarController {

    private final EjemplarService ejemplarService;

    @Autowired
    public EjemplarController(EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    /**
     * @return Un objeto `ResponseEntity` que contiene una lista de ejemplares.
     */
    @GetMapping
    public ResponseEntity<List<Ejemplar>> obtenerTodosLosEjemplares() {
        return ResponseEntity.ok(ejemplarService.obtenerTodosLosEjemplares());
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<Ejemplar>> obtenerEjemplaresPorLibroId(@PathVariable String libroId) {
        return ResponseEntity.ok(ejemplarService.obtenerEjemplaresPorLibroId(libroId));
    }

    /**
     * @param id Identificador único del ejemplar a buscar.
     * @return Un objeto `ResponseEntity` con el ejemplar encontrado o un estado
     *         404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> obtenerEjemplarPorId(@PathVariable String id) {
        Optional<Ejemplar> ejemplar = ejemplarService.obtenerEjemplarPorId(id);
        return ejemplar.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @return ResponseEntity con la lista de ejemplares disponibles.
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<Ejemplar>> ejemplaresDisponibles() {
        return ResponseEntity.ok(ejemplarService.obtenerEjemplaresDisponibles());
    }

    @PostMapping
    public ResponseEntity<Ejemplar> nuevoEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar nuevoEjemplar = ejemplarService.crearEjemplar(ejemplar);
        return ResponseEntity.ok(nuevoEjemplar);
    }

    /**
     * @param id Identificador único del ejemplar a eliminar.
     * @return Un objeto `ResponseEntity` sin contenido si la operación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEjemplar(@PathVariable String id) {
        ejemplarService.eliminarEjemplar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id       Identificador único del ejemplar a actualizar.
     * @param ejemplar Objeto `Ejemplar` con los datos actualizados.
     * @return Un objeto `ResponseEntity` con el ejemplar actualizado o un estado
     *         404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> actualizarEjemplar(@PathVariable String id, @RequestBody Ejemplar ejemplar) {
        ejemplar.setId(id); // Asegura que el ID sea correcto
        Ejemplar ejemplarActualizado = ejemplarService.actualizarEjemplar(ejemplar);
        return ResponseEntity.ok(ejemplarActualizado);
    }

    /**
     * Obtiene el estado de un ejemplar por su ID.
     * 
     * @param id ID del ejemplar a buscar.
     * @return ResponseEntity con el estado del ejemplar o 404 si no existe.
     */
    @GetMapping("/estado/{id}")
    public ResponseEntity<Map<String, String>> obtenerEstadoEjemplarPorId(@PathVariable String id) {
        Optional<Map<String, String>> estadoId = ejemplarService.obtenerEstadoPorId(id);

        if (estadoId.isPresent()) {
            return ResponseEntity.ok(estadoId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable String id, @RequestBody String nuevoEstado) {
        boolean actualizado = ejemplarService.actualizarEstado(id, nuevoEstado);

        if (actualizado) {
            return ResponseEntity.ok("El estado se ha actualizado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Libro no encontrado.");
        }
    }
}