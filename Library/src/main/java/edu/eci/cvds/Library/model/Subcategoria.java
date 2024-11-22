package edu.eci.cvds.Library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategorias")
public class Subcategoria {

  @Id
  private Integer idSubcategoria;
  private String nombre;
  private String descripcion;
  private Integer idCategoria;

  // Constructor vacío (necesario para deserialización)
  public Subcategoria() {
  }

  // Getters and Setters

  public Integer getIdSubcategoria() {
    return idSubcategoria;
  }

  public void setIdSubcategoria(Integer idSubcategoria) {
    this.idSubcategoria = idSubcategoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Integer getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }
}