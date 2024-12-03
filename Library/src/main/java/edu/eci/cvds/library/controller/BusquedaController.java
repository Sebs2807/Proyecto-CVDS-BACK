package edu.eci.cvds.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.core.http.rest.Page;

import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.service.BusquedaService;

@RestController
@RequestMapping("/busquedas")
@PreAuthorize("hasRole('ADMIN', 'ESTUDIANTE')")
public class BusquedaController {

    private BusquedaService busquedaService;
    
    public BusquedaController(BusquedaService busquedaService){
        this.busquedaService = busquedaService;
    }

    @GetMapping("/{regex}/parametro/{parametroBusqueda}/pagina/{noPagina}/tamano/{size}")
    public ResponseEntity<org.springframework.data.domain.Page<Libro>> busqueda(@PathVariable String regex, @PathVariable String parametroBusqueda, @PathVariable int noPagina, @PathVariable int size ) {
        if (parametroBusqueda == "ejemplar"){
            return ResponseEntity.ok(busquedaService.buscarEjemplar(regex, noPagina, size));
        }
        return ResponseEntity.ok(busquedaService.buscarLibrosPorAtributo(regex, parametroBusqueda, noPagina, size));
    }
}
