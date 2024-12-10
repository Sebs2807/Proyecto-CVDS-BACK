package edu.eci.cvds.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.service.BusquedaService;

@RestController
@RequestMapping("/busquedas")
public class BusquedaController {

    private BusquedaService busquedaService;

    public BusquedaController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    // Método para búsqueda de libros
    @GetMapping("/libros/{regex}/parametro/{parametroBusqueda}/pagina/{noPagina}/tamano/{size}")
    public ResponseEntity<org.springframework.data.domain.Page<Libro>> buscarLibros(
            @PathVariable String regex,
            @PathVariable String parametroBusqueda,
            @PathVariable int noPagina,
            @PathVariable int size) {
        
        // Lógica para buscar libros por atributo
        return ResponseEntity.ok(busquedaService.buscarLibrosPorAtributo(regex, parametroBusqueda, noPagina, size));
    }

    // Método para búsqueda de ejemplares
    @GetMapping("/ejemplares/{regex}/pagina/{noPagina}/tamano/{size}")
    public ResponseEntity<Page<Ejemplar>> buscarEjemplares(
            @PathVariable String regex,
            @PathVariable int noPagina,
            @PathVariable int size) {
        
        // Lógica para buscar ejemplares
        return ResponseEntity.ok(busquedaService.buscarEjemplar(regex, noPagina, size));
    }
}
