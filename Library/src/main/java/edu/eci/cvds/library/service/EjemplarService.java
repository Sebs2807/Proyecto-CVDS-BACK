package edu.eci.cvds.library.service;

import edu.eci.cvds.library.model.Ejemplar;
import edu.eci.cvds.library.model.QrGenerator;
import edu.eci.cvds.library.repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarService {

    private final EjemplarRepository ejemplaresRepository;
    private final QrGenerator qrGenerator;

    @Autowired
    public EjemplarService(EjemplarRepository ejemplarRepository) {
        this.ejemplaresRepository = ejemplarRepository;
        this.qrGenerator = new QrGenerator(); // Instancia de la clase generadora de QR
    }

    /**
     * Crea un nuevo ejemplar y genera su QR automáticamente.
     *
     * @param ejemplar Ejemplar a crear.
     * @return El ejemplar creado.
     */
    public Ejemplar crearEjemplar(Ejemplar ejemplar) {
        // Generar QR antes de guardar
        String qrFilePath = "qr-" + ejemplar.getCodigoEjemplar() + ".svg"; // Archivo único por ejemplar
        qrGenerator.generadorQrBar(ejemplar.getCodigoEjemplar());
        ejemplar.setCodigoQR(qrFilePath);

        return ejemplaresRepository.save(ejemplar);
    }

    /**
     * Obtiene todos los ejemplares.
     *
     * @return Lista de ejemplares.
     */
    public List<Ejemplar> obtenerTodosLosEjemplares() {
        return ejemplaresRepository.findAll();
    }

    /**
     * Obtiene un ejemplar por su ID.
     *
     * @param id ID del ejemplar.
     * @return Optional con el ejemplar encontrado o vacío si no existe.
     */
    public Optional<Ejemplar> obtenerEjemplarPorId(String id) {
        return ejemplaresRepository.findById(id);
    }

    /**
     * Elimina un ejemplar por su ID.
     *
     * @param id ID del ejemplar a eliminar.
     */
    public void eliminarEjemplar(String id) {
        ejemplaresRepository.deleteById(id);
    }

    /**
     * Actualiza un ejemplar existente.
     *
     * @param ejemplar Ejemplar con datos actualizados.
     * @return Ejemplar actualizado.
     */
    public Ejemplar actualizarEjemplar(Ejemplar ejemplar) {
        return ejemplaresRepository.save(ejemplar);
    }

    /**
     * Obtiene todos los ejemplares disponibles.
     *
     * @return Lista de ejemplares disponibles.
     */
    public List<Ejemplar> obtenerEjemplaresDisponibles() {
        return ejemplaresRepository.findByDisponibleTrue();
    }
}
