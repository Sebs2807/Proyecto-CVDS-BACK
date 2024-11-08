package edu.eci.cvds.Library.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.service.*;;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;
    
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }
    
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLosLibros());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
