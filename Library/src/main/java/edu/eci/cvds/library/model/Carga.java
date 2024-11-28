package edu.eci.cvds.library.model;

/**
 * Clase que representa la configuración para la carga de datos desde un archivo Excel.
 * Cada atributo de esta clase define la columna específica donde se encuentra la información correspondiente en el Excel.
 */
public class Carga {
    private String nombreLibro;      // Columna del nombre del libro
    private String autor;            // Columna del autor
    private String editorial;        // Columna de la editorial
    private String edicion;          // Columna de la edición
    private String isbn;             // Columna del ISBN
    private String estadoFisico;     // Columna del estado físico del ejemplar
    private String sinopsis;         // Columna de la sinopsis del libro
    private String subcategoria;     // Columna de la subcategoría
    private String categoria;        // Columna de la categoría
    private String disponibilidad;   // Columna de la disponibilidad
    private String anioPublicacion;  // Columna del año de publicación

    /**
     * Obtiene la columna donde se encuentra el nombre del libro.
     * 
     * @return Nombre de la columna para el nombre del libro.
     */
    public String getNombreLibro() {
        return nombreLibro;
    }

    /**
     * Establece la columna donde se encuentra el nombre del libro.
     * 
     * @param nombreLibro Nombre de la columna para el nombre del libro.
     */
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    /**
     * Obtiene la columna donde se encuentra el autor.
     * 
     * @return Nombre de la columna para el autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece la columna donde se encuentra el autor.
     * 
     * @param autor Nombre de la columna para el autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene la columna donde se encuentra la editorial.
     * 
     * @return Nombre de la columna para la editorial.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establece la columna donde se encuentra la editorial.
     * 
     * @param editorial Nombre de la columna para la editorial.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene la columna donde se encuentra la edición.
     * 
     * @return Nombre de la columna para la edición.
     */
    public String getEdicion() {
        return edicion;
    }

    /**
     * Establece la columna donde se encuentra la edición.
     * 
     * @param edicion Nombre de la columna para la edición.
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * Obtiene la columna donde se encuentra el ISBN.
     * 
     * @return Nombre de la columna para el ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece la columna donde se encuentra el ISBN.
     * 
     * @param isbn Nombre de la columna para el ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la columna donde se encuentra el estado físico del ejemplar.
     * 
     * @return Nombre de la columna para el estado físico.
     */
    public String getEstadoFisico() {
        return estadoFisico;
    }

    /**
     * Establece la columna donde se encuentra el estado físico del ejemplar.
     * 
     * @param estadoFisico Nombre de la columna para el estado físico.
     */
    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    /**
     * Obtiene la columna donde se encuentra la sinopsis del libro.
     * 
     * @return Nombre de la columna para la sinopsis.
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Establece la columna donde se encuentra la sinopsis del libro.
     * 
     * @param sinopsis Nombre de la columna para la sinopsis.
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    /**
     * Obtiene la columna donde se encuentra la subcategoría.
     * 
     * @return Nombre de la columna para la subcategoría.
     */
    public String getSubcategoria() {
        return subcategoria;
    }

    /**
     * Establece la columna donde se encuentra la subcategoría.
     * 
     * @param subcategoria Nombre de la columna para la subcategoría.
     */
    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    /**
     * Obtiene la columna donde se encuentra la categoría.
     * 
     * @return Nombre de la columna para la categoría.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la columna donde se encuentra la categoría.
     * 
     * @param categoria Nombre de la columna para la categoría.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la columna donde se encuentra la disponibilidad.
     * 
     * @return Nombre de la columna para la disponibilidad.
     */
    public String getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Establece la columna donde se encuentra la disponibilidad.
     * 
     * @param disponibilidad Nombre de la columna para la disponibilidad.
     */
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Obtiene la columna donde se encuentra el año de publicación.
     * 
     * @return Nombre de la columna para el año de publicación.
     */
    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Establece la columna donde se encuentra el año de publicación.
     * 
     * @param anioPublicacion Nombre de la columna para el año de publicación.
     */
    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    /**
     * Convierte una letra de columna de Excel (A-Z) a su índice correspondiente (0-25).
     * 
     * @param letra Letra de la columna.
     * @return Índice de la columna.
     * @throws IllegalArgumentException Si la letra no está entre A y Z.
     */
    public static int letraAIndice(String letra) {
        if (letra.length() != 1 || letra.charAt(0) < 'A' || letra.charAt(0) > 'Z') {
            throw new IllegalArgumentException("La letra debe ser una letra de la A a la Z.");
        }
        return letra.charAt(0) - 'A';
    }
}