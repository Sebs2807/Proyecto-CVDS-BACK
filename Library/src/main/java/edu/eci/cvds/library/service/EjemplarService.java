package edu.eci.cvds.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.Libro;
import edu.eci.cvds.library.repository.EjemplarRepository;
import edu.eci.cvds.library.repository.LibroRepository;

/**
 * Servicio para manejar la creación, actualización y generación de códigos QR y
 * códigos de barras
 * para los ejemplares de la biblioteca.
 */
@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final AzureBlobStorageService azureBlobStorageService;
    private final LibroRepository libroRepository;

    @Autowired
    public EjemplarService(EjemplarRepository ejemplarRepository, AzureBlobStorageService azureBlobStorageService,
            LibroRepository libroRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.azureBlobStorageService = azureBlobStorageService;
        this.libroRepository = libroRepository;

    }

    /**
     * Crea o actualiza un ejemplar en la base de datos, genera un código QR y
     * código de barras, y los guarda en Azure Blob Storage.
     *
     * @param ejemplar El objeto ejemplar a crear o actualizar.
     * @return El ejemplar creado o actualizado con los códigos QR y de barras, o
     *         null si ocurre un error.
     */
    public Ejemplar crearOActualizarEjemplar(Ejemplar ejemplar) {
        if (ejemplar.getLibro() != null) {
            Optional<Libro> libro = libroRepository.findById(ejemplar.getLibro().getId());
            libro.ifPresent(ejemplar::setLibro);
        }
        return ejemplarRepository.save(ejemplar);
    }

    /**
     * Obtiene todos los ejemplares.
     *
     * @return Lista de ejemplares.
     */
    public List<Ejemplar> obtenerTodosLosEjemplares() {
        return ejemplarRepository.findAll();
    }

    /**
     * Obtiene un ejemplar por su ID.
     *
     * @param id ID del ejemplar.
     * @return Optional con el ejemplar encontrado o vacío si no existe.
     */
    public Optional<Ejemplar> obtenerEjemplarPorId(String id) {
        return ejemplarRepository.findById(id);
    }

    /**
     * Elimina un ejemplar por su ID.
     *
     * @param id ID del ejemplar a eliminar.
     */
    public void eliminarEjemplar(String id) {
        ejemplarRepository.deleteById(id);
    }

    /**
     * Actualiza un ejemplar existente.
     *
     * @param ejemplar Ejemplar con datos actualizados.
     * @return Ejemplar actualizado.
     */
    public Ejemplar actualizarEjemplar(Ejemplar ejemplar) {
        if (ejemplar.getLibro() != null) {
            Optional<Libro> libro = libroRepository.findById(ejemplar.getLibro().getId());
            libro.ifPresent(ejemplar::setLibro);
        }
        return ejemplarRepository.save(ejemplar);
    }

    /**
     * Obtiene todos los ejemplares disponibles.
     *
     * @return Lista de ejemplares disponibles.
     */
    public List<Ejemplar> obtenerEjemplaresDisponibles() {
        return ejemplarRepository.findByDisponibleTrue();
    }

    /**
     * Obtiene el estado de un ejemplar dado su ID.
     * 
     * @param id ID del ejemplar a buscar.
     * @return Un Optional con el estado del ejemplar, o vacío si no existe.
     */
    public Optional<Map<String, String>> obtenerEstadoPorId(String id) {
        Optional<Ejemplar> ejemplarProvisional = ejemplarRepository.findById(id);

        if (ejemplarProvisional.isEmpty()) {
            return Optional.empty();
        }

        Ejemplar ejemplar = ejemplarProvisional.get();
        Map<String, String> estadoId = new HashMap<>();
        estadoId.put("estado", ejemplar.getEstado());
        estadoId.put("idLibro", ejemplar.getId());

        return Optional.of(estadoId);
    }

    public boolean actualizarEstado(String id, String nuevoEstado) {
        Optional<Ejemplar> ejemplarProvisional = ejemplarRepository.findById(id);

        if (ejemplarProvisional.isEmpty()) {
            return false;
        }

        Ejemplar ejemplar = ejemplarProvisional.get();
        ejemplar.setEstado(nuevoEstado.strip());
        ejemplarRepository.save(ejemplar);

        return true;
    }

    public Page<Libro> findById(String idEjemplar, Pageable pageable) {
        return (ejemplarRepository.findByIdEjemplar(idEjemplar, pageable));
    }

    public List<Ejemplar> obtenerEjemplaresPorLibroId(String libroId) {
        return ejemplarRepository.findByLibro(libroId);
    }
}
