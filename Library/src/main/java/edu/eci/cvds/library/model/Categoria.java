package edu.eci.cvds.library.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Clase que representa una categoría en el sistema de gestión de biblioteca.
 * Cada categoría tiene un identificador único, un nombre, y una lista de
 * subcategorías asociadas.
 * Se utiliza la integración con MongoDB para el almacenamiento de los datos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "categorias")
public class Categoria {

    @Id
    private String idCategoria; // Identificador único de la categoría en la base de datos.
    private String nombre; // Nombre de la categoría.

    @DBRef(lazy = true)
    private List<Subcategoria> subcategorias; // Lista de subcategorías asociadas a esta categoría.

    /**
     * Constructor que inicializa una nueva categoría con un nombre específico y
     * crea una lista vacía de subcategorías.
     * 
     * @param nombre Nombre de la categoría.
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.subcategorias = new ArrayList<>();
    }

    public Categoria() {
        this.subcategorias = new ArrayList<>();
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

    /**
     * Agrega una subcategoría a la lista de subcategorías asociadas a esta
     * categoría.
     * 
     * @param subcategoria Subcategoría a agregar.
     */
    public void addSubcategoria(Subcategoria subcategoria) {
        subcategorias.add(subcategoria);
    }

    /**
     * Busca dentro de la lista de subcategorías si existe una con el nombre
     * especificado.
     * 
     * @param nombreSubcategoria Nombre de la subcategoría a buscar.
     * @return {@code true} si la subcategoría existe, {@code false} en caso
     *         contrario.
     */
    public boolean findSubcategoria(String nombreSubcategoria) {
        for (Subcategoria c : subcategorias) {
            if (c.getNombre().equals(nombreSubcategoria)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega una subcategoría a la lista de subcategorías asociadas a esta
     * categoría.
     * 
     * @param idSubcategoria Subcategoría a agregar.
     */
    public void setSubcategorias(Subcategoria idSubcategoria) {
        subcategorias.add(idSubcategoria);
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }
}
