package edu.eci.cvds.Library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Document(collection = "ejemplares")
public class Ejemplar {
    @Id
    private String id;
    
    @DBRef
    private Libro libro;
    
    private String codigoEjemplar;
    private String estado;
    private boolean disponible;
    private String codigoQR;

    /**
     * Obtiene el ID del ejemplar.
     * @return ID del ejemplar.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un ID al ejemplar.
     * @param id ID del ejemplar.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el libro al que pertenece el ejemplar.
     * @return libro al que pertenece el ejemplar.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Asigna un libro al ejemplar.
     * @param libro Libro al que pertenece el ejemplar.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene el código del ejemplar.
     * @return código del ejemplar.
     */
    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    /**
     * Asigna un código al ejemplar.
     * @param codigoEjemplar Código del ejemplar.
     */
    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    /**
     * Obtiene el estado del ejemplar.
     * @return estado del ejemplar.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Asigna un estado al ejemplar.
     * @param estado Estado del ejemplar.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Verifica si el ejemplar está disponible.
     * @return true si el ejemplar está disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Asigna la disponibilidad del ejemplar.
     * @param disponible true si el ejemplar está disponible, false en caso contrario.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el código QR del ejemplar.
     * @return código QR del ejemplar.
     */
    public String getCodigoQR() {
        return codigoQR;
    }

    /**
     * Asigna un código QR al ejemplar.
     * @param codigoQR Código QR del ejemplar.
     */
    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }
}
