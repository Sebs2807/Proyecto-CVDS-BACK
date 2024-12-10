package edu.eci.cvds.library.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "subcategorias")
public class Subcategoria {

    @Id
    private String id;
    private String nombre;

    @DBRef(lazy = false)
    private List<Categoria> categorias;

    public Subcategoria(String nombre){
        this.nombre = nombre;
    }

    // No-argument constructor
    public Subcategoria() {
        this.categorias = new ArrayList<>();
    }

    // Constructor with properties (Optional)
    @JsonCreator
    public Subcategoria(@JsonProperty("nombre") String nombre, @JsonProperty("categorias") List<Categoria> categorias) {
        this.nombre = nombre;
        this.categorias = categorias != null ? categorias : new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public boolean findCategoria(String nombreCategoria) {
        for (Categoria c : categorias) {
            if (c.getNombre().equals(nombreCategoria)) {
                return true;
            }
        }
        return false;
    }
}