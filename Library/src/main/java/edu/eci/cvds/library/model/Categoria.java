package edu.eci.cvds.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa una categoría en el sistema de gestión de biblioteca.
 * Cada categoría tiene un identificador único, un nombre, y una lista de
 * subcategorías asociadas.
 * Se utiliza la integración con MongoDB para el almacenamiento de los datos.
 */
@Document(collection = "categorias")
public class Categoria {

    @Id
    private String idCategoria; // Identificador único de la categoría en la base de datos.
    private String nombre; // Nombre de la categoría.

    /**
     * Constructor que inicializa una nueva categoría con un nombre específico y
     * crea una lista vacía de subcategorías.
     * 
     * @param nombre Nombre de la categoría.
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único de la categoría.
     * 
     * @return El identificador de la categoría.
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     * 
     * @return El nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
