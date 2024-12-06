package edu.eci.cvds.library.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa una subcategoría dentro del sistema de gestión de biblioteca.
 * Esta clase es mapeada a un documento en la colección "subcategorias" de
 * MongoDB.
 * Cada subcategoría está asociada con un nombre y un identificador único.
 * 
 * @author [Tu Nombre]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "subcategorias")
public class Subcategoria {

    @Id
    private String idSubcategoria;
    private String nombre;

    @DBRef(lazy = false)
    private List<Categoria> categorias;

    /**
     * Constructor que inicializa el nombre de la subcategoría.
     * Este constructor es utilizado principalmente para la deserialización.
     * 
     * @param nombre El nombre de la subcategoría.
     */
    public Subcategoria(String nombre) {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
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

    /**
     * Agrega una subcategoría a la lista de subcategorías asociadas a esta
     * categoría.
     * 
     * @param subcategoria Subcategoría a agregar.
     */
    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    /**
     * Busca dentro de la lista de subcategorías si existe una con el nombre
     * especificado.
     * 
     * @param nombreSubcategoria Nombre de la subcategoría a buscar.
     * @return {@code true} si la subcategoría existe, {@code false} en caso
     *         contrario.
     */
    public boolean findCategoria(String nombreCategoria) {
        for (Categoria c : categorias) {
            if (c.getNombre().equals(nombreCategoria)) {
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
    public void setCategorias(Categoria idCategoria) {
        categorias.add(idCategoria);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}
