package edu.eci.cvds.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.repository.EjemplarRepository;

/**
 * Servicio para manejar la creación, actualización y generación de códigos QR y códigos de barras
 * para los ejemplares de la biblioteca.
 */
@Service
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final AzureBlobStorageService azureBlobStorageService;

    @Autowired
    public EjemplarService(EjemplarRepository ejemplarRepository, AzureBlobStorageService azureBlobStorageService) {
        this.ejemplarRepository = ejemplarRepository;
        this.azureBlobStorageService = azureBlobStorageService;
    }

    /**
     * Crea o actualiza un ejemplar en la base de datos, genera un código QR y código de barras, y los guarda en Azure Blob Storage.
     *
     * @param ejemplar El objeto ejemplar a crear o actualizar.
     * @return El ejemplar creado o actualizado con los códigos QR y de barras, o null si ocurre un error.
     */
    public Ejemplar crearOActualizarEjemplar(Ejemplar ejemplar) {
        try {
            // Guardar el ejemplar en la base de datos
            Ejemplar ejemplarCarga = ejemplarRepository.save(ejemplar);

            // Generar el QR en formato InputStream
            String cbFileName = "cb-" + ejemplarCarga.getId() + ".png";

            // Subir el QR a Azure Blob Storage
            azureBlobStorageService.guardarArchivoEnBlob(ejemplarCarga.getId(), cbFileName);

            // Actualizar el ejemplar con el nombre del archivo QR subido
            ejemplarCarga.setCodigoBarras(cbFileName);
            return ejemplarRepository.save(ejemplarCarga);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
}
