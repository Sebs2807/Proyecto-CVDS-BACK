package edu.eci.cvds.library.model;

public class Carga {
    private String nombreLibro;
    private String autor;
    private String editorial;
    private String edicion;
    private String isbn;
    private String estadoFisico;
    private String sinopsis;
    private String subcategoria;
    private String categoria;
    private String disponibilidad;
    private String anioPublicacion;

    public String getNombreLibro() {
        return nombreLibro;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public static int letraAIndice(String letra) {
        if (letra.length() != 1 || letra.charAt(0) < 'A' || letra.charAt(0) > 'Z') {
            throw new IllegalArgumentException("La letra debe ser una letra de la A a la Z.");
        }
        return letra.charAt(0) - 'A';
    }
}
