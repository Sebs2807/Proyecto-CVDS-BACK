package edu.eci.cvds.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.repository.LibroRepository;

@Service
public class BusquedaService {

    private LibroService libroService;
    private EjemplarService ejemplarService;

    public BusquedaService(LibroService libroService){
        this.libroService = libroService;
    }
    

    public Page<Libro> buscarLibrosPorAtributo(String regex, String parametro, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return libroService.findByFieldWithRegexExcluding(parametro, regex, pageable);
    }

    public Page<Libro> buscarEjemplar(String idEjemplar, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ejemplarService.findById(idEjemplar, pageable);
    }
}
