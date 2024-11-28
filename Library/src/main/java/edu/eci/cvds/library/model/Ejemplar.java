package edu.eci.cvds.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa un ejemplar físico de un libro dentro del sistema de gestión de biblioteca.
 * Cada ejemplar tiene un estado físico, un identificador único, y detalles adicionales como disponibilidad y códigos de identificación.
 */
@Document(collection = "ejemplares")
public class Ejemplar {
    @Id
    private String id; // Identificador único del ejemplar.
    private String estado; // Estado físico del ejemplar (e.g., "Nuevo", "Usado", "Dañado").
    private boolean disponible; // Indica si el ejemplar está disponible para préstamo.
    private String codigoBarras; // Código de barras asociado al ejemplar.

    /**
     * Constructor que inicializa un ejemplar con su estado físico y disponibilidad.
     * 
     * @param estado Estado físico del ejemplar.
     * @param disponible Disponibilidad del ejemplar (true si está disponible, false en caso contrario).
     */
    public Ejemplar(String estado, boolean disponible) {
        this.estado = estado;
        this.disponible = disponible;
    }

    // Getters y Setters

    /**
     * Obtiene el ID del ejemplar.
     * 
     * @return ID del ejemplar.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un ID al ejemplar.
     * 
     * @param id ID del ejemplar.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el estado del ejemplar.
     * 
     * @return Estado físico del ejemplar.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Asigna un estado al ejemplar.
     * 
     * @param estado Estado físico del ejemplar.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Verifica si el ejemplar está disponible.
     * 
     * @return true si el ejemplar está disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Asigna la disponibilidad del ejemplar.
     * 
     * @param disponible true si el ejemplar está disponible, false en caso contrario.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el código de barras del ejemplar.
     * 
     * @return Código de barras del ejemplar.
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String direccionURL){
        this.codigoBarras = direccionURL;
    }

}
