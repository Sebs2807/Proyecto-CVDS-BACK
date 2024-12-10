package edu.eci.cvds.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;

/**
 * Servicio que maneja la lógica de búsqueda de libros y ejemplares.
 * Utiliza los servicios LibroService y EjemplarService para realizar
 * las búsquedas en los repositorios.
 */
@Service
public class BusquedaService {

    private LibroService libroService;
    private EjemplarService ejemplarService;

    /**
     * Constructor de la clase BusquedaService.
     * 
     * @param libroService Servicio encargado de la gestión de libros.
     * @param ejemplarService Servicio encargado de la gestión de ejemplares.
     */
    @Autowired
    public BusquedaService(LibroService libroService, EjemplarService ejemplarService){
        this.libroService = libroService;
        this.ejemplarService = ejemplarService;
    }

    /**
     * Busca libros por un atributo específico utilizando una expresión regular.
     * 
     * @param regex Expresión regular para la búsqueda.
     * @param parametro Nombre del atributo del libro por el cual se realizará la búsqueda (por ejemplo, "nombre", "autor").
     * @param page Número de página de los resultados.
     * @param size Número de elementos por página.
     * @return Un objeto Page<Libro> con los resultados de la búsqueda de libros.
     */
    public Page<Libro> buscarLibrosPorAtributo(String regex, String parametro, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return libroService.findByFieldWithRegexExcluding(parametro, regex, pageable);
    }

    /**
     * Busca ejemplares de libros específicos por su ID.
     * 
     * @param idEjemplar ID del ejemplar a buscar.
     * @param page Número de página de los resultados.
     * @param size Número de elementos por página.
     * @return Un objeto Page<Ejemplar> con los resultados de la búsqueda de ejemplares.
     */
    public Page<Ejemplar> buscarEjemplar(String idEjemplar, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ejemplarService.findById(idEjemplar, pageable);
    }
}
