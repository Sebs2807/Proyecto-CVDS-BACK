package edu.eci.cvds.Library.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")
public class Categoria {

	@Id
	private String idCategoria;
	private String nombre;
	@DBRef
	private List<Subcategoria> subcategorias;

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	// Getters and Setters
	public String getIdCategoria() {
		return idCategoria;
	}

	public void setSubcategoria(Subcategoria idSubcategoria) {
        subcategorias.add(idSubcategoria);
	}

	public String getNombre() {
		return nombre;
	}

}