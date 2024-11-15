package edu.eci.cvds.Library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.util.List;

@Document(collection = "libros")
public class Libro {

    @Id
    private String id;
    private String nombreLibro;
    private String autor;
    private String distribuidor;
    private String coleccion;
    private String lugarPublicacion;
    private String editorial; // Ajustado para coincidir con JSON (minusculas)
    private String editor;
    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    private Integer edicion; // Cambiado a Integer para coincidir con JSON
    private String numeroClasificacion; // Cambiado a Integer para coincidir con JSON
    private Integer anioPublicacion;
    private Double paginas; // Cambiado para coincidir con JSON
    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    private Integer tiempoprestamo;
    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    private Integer idsubcategoria;
    private String isbn;
    private String nombreCategoria; // Ajustado para coincidir con JSON (sin acento)
    private String nombreSubcategoria; // Ajustado para coincidir con JSON (sin acento)
    private String gradosRecomendados;
    private String tema;
    private String idioma;
    private String sinopsis;
    private String estadoFisico;
    private String ubicacionBiblioteca;
    private Date fechaIngreso;

    @DBRef
    private List<Ejemplar> ejemplares;

    // Constructor vacío (necesario para deserialización)
    public Libro() {}

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
     * Obtiene el distribuidor del libro.
     * 
     * @return el distribuidor del libro.
     */
    public String getDistribuidor() {
        return distribuidor;
    }

    /**
     * Establece el distribuidor del libro.
     * 
     * @param distribuidor el distribuidor del libro.
     */
    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    /**
     * Obtiene la colección del libro.
     * 
     * @return la colección del libro.
     */
    public String getColeccion() {
        return coleccion;
    }

    /**
     * Establece la colección del libro.
     * 
     * @param coleccion la colección del libro.
     */
    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    /**
     * Obtiene el lugar de publicación del libro.
     * 
     * @return el lugar de publicación del libro.
     */
    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    /**
     * Establece el lugar de publicación del libro.
     * 
     * @param lugarPublicacion el lugar de publicación del libro.
     */
    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    /**
     * Obtiene la editorial del libro.
     * 
     * @return la editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establece la editorial del libro.
     * 
     * @param editorial la editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
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
     * Obtiene el número de clasificación del libro.
     * 
     * @return el número de clasificación del libro.
     */
    public String getNumeroClasificacion() {
        return numeroClasificacion;
    }

    /**
     * Establece el número de clasificación del libro.
     * 
     * @param numeroClasificacion el número de clasificación del libro.
     */
    public void setNumeroClasificacion(String numeroClasificacion) {
        this.numeroClasificacion = numeroClasificacion;
    }

    /**
     * Obtiene el año de publicación del libro.
     * 
     * @return el año de publicación del libro.
     */
    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Establece el año de publicación del libro.
     * 
     * @param anioPublicacion el año de publicación del libro.
     */
    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Obtiene el número de páginas del libro.
     * 
     * @return el número de páginas del libro.
     */
    public Double getPaginas() {
        return paginas;
    }

    /**
     * Establece el número de páginas del libro.
     * 
     * @param paginas el número de páginas del libro.
     */
    public void setPaginas(Double paginas) {
        this.paginas = paginas;
    }

    /**
     * Obtiene el tiempo de préstamo del libro en días.
     * 
     * @return el tiempo de préstamo del libro en días.
     */
    public Integer getTiempoprestamo() {
        return tiempoprestamo;
    }

    /**
     * Establece el tiempo de préstamo del libro en días.
     * 
     * @param tiempoprestamo el tiempo de préstamo del libro en días.
     */
    public void setTiempoprestamo(Integer tiempoprestamo) {
        this.tiempoprestamo = tiempoprestamo;
    }

    /**
     * Obtiene el ID de la subcategoría del libro.
     * 
     * @return el ID de la subcategoría del libro.
     */
    public Integer getIdsubcategoria() {
        return idsubcategoria;
    }

    /**
     * Establece el ID de la subcategoría del libro.
     * 
     * @param idsubcategoria el ID de la subcategoría del libro.
     */
    public void setIdsubcategoria(Integer idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
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
     * Obtiene el nombre de la categoría del libro.
     * 
     * @return el nombre de la categoría del libro.
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Establece el nombre de la categoría del libro.
     * 
     * @param nombreCategoria el nombre de la categoría del libro.
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene el nombre de la subcategoría del libro.
     * 
     * @return el nombre de la subcategoría del libro.
     */
    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    /**
     * Establece el nombre de la subcategoría del libro.
     * 
     * @param nombreSubcategoria el nombre de la subcategoría del libro.
     */
    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    /**
     * Obtiene los grados recomendados para el libro.
     * 
     * @return los grados recomendados para el libro.
     */
    public String getGradosRecomendados() {
        return gradosRecomendados;
    }

    /**
     * Establece los grados recomendados para el libro.
     * 
     * @param gradosRecomendados los grados recomendados para el libro.
     */
    public void setGradosRecomendados(String gradosRecomendados) {
        this.gradosRecomendados = gradosRecomendados;
    }

    /**
     * Obtiene el tema del libro.
     * 
     * @return el tema del libro.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Establece el tema del libro.
     * 
     * @param tema el tema del libro.
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Obtiene el idioma del libro.
     * 
     * @return el idioma del libro.
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma del libro.
     * 
     * @param idioma el idioma del libro.
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
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
     * Obtiene el estado físico del libro.
     * 
     * @return el estado físico del libro.
     */
    public String getEstadoFisico() {
        return estadoFisico;
    }

    /**
     * Establece el estado físico del libro.
     * 
     * @param estadoFisico el estado físico del libro.
     */
    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    /**
     * Obtiene la ubicación del libro en la biblioteca.
     * 
     * @return la ubicación del libro en la biblioteca.
     */
    public String getUbicacionBiblioteca() {
        return ubicacionBiblioteca;
    }

    /**
     * Establece la ubicación del libro en la biblioteca.
     * 
     * @param ubicacionBiblioteca la ubicación del libro en la biblioteca.
     */
    public void setUbicacionBiblioteca(String ubicacionBiblioteca) {
        this.ubicacionBiblioteca = ubicacionBiblioteca;
    }

    /**
     * Obtiene la fecha de ingreso del libro a la biblioteca.
     * 
     * @return la fecha de ingreso del libro.
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Establece la fecha de ingreso del libro a la biblioteca.
     * 
     * @param fechaIngreso la fecha de ingreso del libro.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Obtiene la lista de ejemplares del libro.
     * 
     * @return la lista de ejemplares del libro.
     */
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    /**
     * Establece la lista de ejemplares del libro.
     * 
     * @param ejemplares la lista de ejemplares del libro.
     */
    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
}
