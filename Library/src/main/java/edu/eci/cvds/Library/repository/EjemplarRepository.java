package edu.eci.cvds.Library.repository;
import edu.eci.cvds.Library.model.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    
}

