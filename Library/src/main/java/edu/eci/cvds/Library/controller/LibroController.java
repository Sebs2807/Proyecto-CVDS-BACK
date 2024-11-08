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
     * @param libro Objeto libro a crear.
     * @return ResponseEntity con el libro creado.
     */
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    /**
     * Obtiene todos los libros.
     * @return ResponseEntity con la lista de todos los libros.
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLosLibros());
    }

    /**
     * Obtiene un libro por su ID.
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
     * @param id ID del libro a eliminar.
     * @return ResponseEntity sin contenido si se elimina exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca libros por título.
     * @param titulo El título del libro a buscar.
     * @return ResponseEntity con los libros encontrados.
     */
    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Libro>> buscarLibrosPorTitulo(@RequestParam String titulo) {
        List<Libro> libros = libroService.buscarLibrosPorTitulo(titulo);
        return libros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(libros);
    }

    /**
     * Busca libros por autor.
     * @param autor El autor del libro a buscar.
     * @return ResponseEntity con los libros encontrados.
     */
    @GetMapping("/buscar/autor")
    public ResponseEntity<List<Libro>> buscarLibrosPorAutor(@RequestParam String autor) {
        List<Libro> libros = libroService.buscarLibrosPorAutor(autor);
        return libros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(libros);
    }

    /**
     * Busca libros por categoría.
     * @param categoria La categoría del libro a buscar.
     * @return ResponseEntity con los libros encontrados.
     */
    @GetMapping("/buscar/categoria")
    public ResponseEntity<List<Libro>> buscarLibrosPorCategoria(@RequestParam String categoria) {
        List<Libro> libros = libroService.buscarLibrosPorCategoria(categoria);
        return libros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(libros);
    }
}
