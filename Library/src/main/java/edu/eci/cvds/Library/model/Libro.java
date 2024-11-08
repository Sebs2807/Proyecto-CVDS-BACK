package edu.eci.cvds.Library.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String edicion;
    private Integer anioPublicacion;
    private String isbn;
    private String categoria;
    private String subcategoria;
    @Lob
    private String descripcion;
    private String imagenUrl;
    
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares;
    
    // Getters y setters
}

