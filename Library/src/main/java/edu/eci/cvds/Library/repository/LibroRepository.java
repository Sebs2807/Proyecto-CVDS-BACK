package edu.eci.cvds.Library.repository;
import edu.eci.cvds.Library.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    
}