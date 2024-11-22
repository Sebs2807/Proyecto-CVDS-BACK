package edu.eci.cvds.Library.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategorias")
public class Subcategoria {

	@Id
	private String idSubcategoria;
	private String nombre;
	@DBRef
	private List<Categoria> categorias;

	// Constructor vacío (necesario para deserialización)
	public Subcategoria() {
	}

	// Getters and Setters

	public String getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setSubcategoria(String idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Categoria> getIdCategoria() {
		return categorias;
	}

	public void setIdCategoria(Categoria Categoria) {
		categorias.add(Categoria);
	}
}