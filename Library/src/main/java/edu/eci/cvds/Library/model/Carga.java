package edu.eci.cvds.Library.model;

public class Carga {
    private char nombreLibro;
    private char autor;
    private char editorial;
    private char edicion;
    private char isbn;
    private char estadoFisico;
    private char sinopsis;
    private char subcategoria;
    private char categoria;
    private char disponibilidad;
    private char anioPublicacion;

    public char getNombreLibro() {
        return nombreLibro;
    }

    public char getAutor() {
        return autor;
    }

    public char getEditorial() {
        return editorial;
    }

    public char getEdicion() {
        return edicion;
    }

    public char getIsbn() {
        return isbn;
    }

    public char getDisponibilidad() {
        return disponibilidad;
    }

    public char getCategoria() {
        return categoria;
    }

    public char getEstadoFisico() {
        return estadoFisico;
    }

    public char getSubcategoria() {
        return subcategoria;
    }

    public char getSinopsis() {
        return sinopsis;
    }

    public char getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setNombreLibro(char nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public void setAutor(char autor) {
        this.autor = autor;
    }

    public void setEditorial(char editorial) {
        this.editorial = editorial;
    }

    public void setEdicion(char edicion) {
        this.edicion = edicion;
    }

    public void setIsbn(char isbn) {
        this.isbn = isbn;
    }

    public void setDisponibilidad(char disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public void setEstadoFisico(char estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public void setSubcategoria(char subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setSinopsis(char sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setAnioPublicacion(char anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
