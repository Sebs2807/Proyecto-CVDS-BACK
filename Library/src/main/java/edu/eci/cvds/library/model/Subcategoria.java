package edu.eci.cvds.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa una subcategoría dentro del sistema de gestión de biblioteca.
 * Esta clase es mapeada a un documento en la colección "subcategorias" de MongoDB.
 * Cada subcategoría está asociada con un nombre y un identificador único.
 * 
 * @author [Tu Nombre]
 */
@Document(collection = "subcategorias")
public class Subcategoria {

    /**
     * El identificador único de la subcategoría en la base de datos.
     */
    @Id
    private String idSubcategoria;

    /**
     * El nombre de la subcategoría, que es una descripción del tipo de libros que contiene.
     */
    private String nombre;

    /**
     * Constructor que inicializa el nombre de la subcategoría.
     * Este constructor es utilizado principalmente para la deserialización.
     * 
     * @param nombre El nombre de la subcategoría.
     */
    public Subcategoria(String nombre) {
        this.nombre = nombre;
    }

    // Getters and Setters

    /**
     * Obtiene el identificador único de la subcategoría.
     * 
     * @return El identificador único de la subcategoría.
     */
    public String getIdSubcategoria() {
        return idSubcategoria;
    }

    /**
     * Asigna el identificador único de la subcategoría.
     * 
     * @param idSubcategoria El identificador único de la subcategoría.
     */
    public void setSubcategoria(String idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    /**
     * Obtiene el nombre de la subcategoría.
     * 
     * @return El nombre de la subcategoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre a la subcategoría.
     * 
     * @param nombre El nombre de la subcategoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
