package edu.eci.cvds.Library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Clase que representa un libro en la biblioteca.
 */
@Document(collection = "libros")
public class Libro {
    @Id
    private String id;
    private String titulo;
    private String autor;
    private String editorial;
    private String edicion;
    private Integer anioPublicacion;
    private String isbn;
    private String categoria;
    private String subcategoria;
    private String descripcion;
    private String imagenUrl;
    
    @DBRef
    private List<Ejemplar> ejemplares;

    /**
     * Obtiene el ID del libro.
     * @return id del libro.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un ID al libro.
     * @param id El ID del libro.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el título del libro.
     * @return título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Asigna un título al libro.
     * @param titulo El título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     * @return autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Asigna un autor al libro.
     * @param autor El autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene la editorial del libro.
     * @return editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Asigna una editorial al libro.
     * @param editorial La editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene la edición del libro.
     * @return edición del libro.
     */
    public String getEdicion() {
        return edicion;
    }

    /**
     * Asigna una edición al libro.
     * @param edicion La edición del libro.
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * Obtiene el año de publicación del libro.
     * @return año de publicación del libro.
     */
    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Asigna el año de publicación al libro.
     * @param anioPublicacion Año de publicación del libro.
     */
    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Obtiene el ISBN del libro.
     * @return ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Asigna el ISBN al libro.
     * @param isbn El ISBN del libro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la categoría del libro.
     * @return categoría del libro.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Asigna la categoría al libro.
     * @param categoria La categoría del libro.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la subcategoría del libro.
     * @return subcategoría del libro.
     */
    public String getSubcategoria() {
        return subcategoria;
    }

    /**
     * Asigna una subcategoría al libro.
     * @param subcategoria La subcategoría del libro.
     */
    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    /**
     * Obtiene la descripción del libro.
     * @return descripción del libro.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna una descripción al libro.
     * @param descripcion La descripción del libro.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la URL de la imagen del libro.
     * @return URL de la imagen del libro.
     */
    public String getImagenUrl() {
        return imagenUrl;
    }

    /**
     * Asigna una URL de imagen al libro.
     * @param imagenUrl La URL de la imagen del libro.
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    /**
     * Obtiene la lista de ejemplares del libro.
     * @return lista de ejemplares del libro.
     */
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    /**
     * Asigna una lista de ejemplares al libro.
     * @param ejemplares La lista de ejemplares del libro.
     */
    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
}
