package edu.eci.cvds.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    /**
     * Crea un nuevo libro.
     * 
     * @param libro Objeto libro a crear.
     * @return ResponseEntity con el libro creado.
     */
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    /**
     * Obtiene todos los libros.
     * 
     * @return ResponseEntity con la lista de todos los libros.
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLosLibros());
    }

    /**
     * Obtiene un libro por su ID.
     * 
     * @param id ID del libro a buscar.
     * @return ResponseEntity con el libro encontrado o 404 si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable String id) {
        Optional<Libro> libroProvisional = libroService.obtenerLibroPorId(id);

        if (libroProvisional.isPresent()) {
            return ResponseEntity.ok(libroProvisional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un libro por su ID.
     * 
     * @param id ID del libro a eliminar.
     * @return ResponseEntity sin contenido si se elimina exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un libro existente por su ID.
     * 
     * @param id    ID del libro a actualizar.
     * @param libro Objeto libro con los datos actualizados.
     * @return ResponseEntity con el libro actualizado o 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable String id, @RequestBody Libro libro) {
        Optional<Libro> libroExistente = libroService.obtenerLibroPorId(id);
        if (libroExistente.isPresent()) {
            libro.setId(id);
            Libro libroActualizado = libroService.actualizarLibro(libro);
            return ResponseEntity.ok(libroActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "El endpoint está funcionando.";
    }

    // @PostMapping("/cargar-desde-json")
    // public ResponseEntity<Void> cargarLibrosDesdeJson(@RequestParam String rutaArchivo) {
    //     libroService.cargarLibrosDesdeJson(rutaArchivo);
    //     return ResponseEntity.ok().build();
    // }
}
