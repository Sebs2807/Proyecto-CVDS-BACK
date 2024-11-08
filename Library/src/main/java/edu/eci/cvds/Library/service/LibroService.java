package edu.eci.cvds.Library.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.repository.*;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }
    
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}

