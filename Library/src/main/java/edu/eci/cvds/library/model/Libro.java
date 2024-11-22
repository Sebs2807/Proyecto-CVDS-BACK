package edu.eci.cvds.Library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

@Document(collection = "libros")
public class Libro {

    @Id
    private String id;
    private String nombreLibro;
    private String autor;
    private String editor;
    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    private Integer edicion;
    private String isbn;
    private String sinopsis;
    private Date fechaIngreso;

    @DBRef
    private List<Category> Categorias;
    @DBRef
    private List<Subcategory> Subcategorias;
    @DBRef
    private List<Ejemplar>ejemplares;

    // Constructor vacío necesario para la deserialización
    public Libro() {
    }

    /**
     * Obtiene el ID del libro.
     * 
     * @return el ID del libro.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del libro.
     * 
     * @param id el ID del libro.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del libro.
     * 
     * @return el nombre del libro.
     */
    public String getNombreLibro() {
        return nombreLibro;
    }

    /**
     * Establece el nombre del libro.
     * 
     * @param nombreLibro el nombre del libro.
     */
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    /**
     * Obtiene el autor del libro.
     * 
     * @return el autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     * 
     * @param autor el autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el editor del libro.
     * 
     * @return el editor del libro.
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Establece el editor del libro.
     * 
     * @param editor el editor del libro.
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * Obtiene la edición del libro.
     * 
     * @return la edición del libro.
     */
    public Integer getEdicion() {
        return edicion;
    }

    /**
     * Establece la edición del libro.
     * 
     * @param edicion la edición del libro.
     */
    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    /**
     * Obtiene el ISBN del libro.
     * 
     * @return el ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     * 
     * @param isbn el ISBN del libro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la sinopsis del libro.
     * 
     * @return la sinopsis del libro.
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Establece la sinopsis del libro.
     * 
     * @param sinopsis la sinopsis del libro.
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * Obtiene la fecha de ingreso del libro.
     * 
     * @return la fecha de ingreso.
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Establece la fecha de ingreso del libro.
     * 
     * @param fechaIngreso la fecha de ingreso.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Obtiene la lista de ejemplares asociados al libro.
     * 
     * @return la lista de ejemplares.
     */
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    /**
     * Establece la lista de ejemplares asociados al libro.
     * 
     * @param ejemplares la lista de ejemplares.
     */
    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
}
