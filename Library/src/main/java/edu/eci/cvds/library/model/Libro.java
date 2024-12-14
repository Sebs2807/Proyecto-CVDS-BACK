package edu.eci.cvds.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "libros")
public class Libro {

    @Id
    private String id;
    private String nombreLibro;
    private String autor;
    private String editor;
    private String edicion;
    private String isbn;
    private String sinopsis;
    private String anioPublicacion;

    @DBRef(lazy = false)
    private List<Subcategoria> subcategorias;

    @DBRef(lazy = false)
    private List<Categoria> categorias;

    // Constructor vacío necesario para la deserialización
    public Libro(String nombreLibro, String autor, String editor, String edicion, String isbn, String sinopsis,
            String anioPublicacion) {
        this.nombreLibro = nombreLibro;
        this.autor = autor;
        this.editor = editor;
        this.edicion = edicion;
        this.isbn = isbn;
        this.sinopsis = sinopsis;
        this.anioPublicacion = anioPublicacion;
        subcategorias = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    /**
     * Obtiene las subcategorías del libro.
     * 
     * @return una lista de subcategorías a las que pertenece el libro.
     */

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
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
    public String getEdicion() {
        return edicion;
    }

    /**
     * Establece la edición del libro.
     * 
     * @param edicion la edición del libro.
     */
    public void setEdicion(String edicion) {
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
     * Busca dentro de la lista de subcategorias si existe alguna con ese nombre de
     * la subcategoria.
     * 
     * @param nombreSubcategoria booleano que indica si existe o no.
     */
    public boolean haveSubcategoria(String nombreSubcategoria) {
        for (Subcategoria c : subcategorias) {
            if (c.getNombre().equals(nombreSubcategoria)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Busca dentro de la lista de subcategorias si existe alguna con ese nombre de
     * la subcategoria.
     * 
     * @param nombreCategoria booleano que indica si existe o no.
     */
    public boolean haveCategoria(String nombreCategoria) {
        for (Categoria c : categorias) {
            if (c.getNombre().equals(nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adiciona una nueva subcategoria a la lista de subcategorias.
     * 
     * @param subcategoria suncategoria que va ser adicionada.
     */
    public void addSubcategoria(Subcategoria subcategoria) {
        this.subcategorias.add(subcategoria);
    }

    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
