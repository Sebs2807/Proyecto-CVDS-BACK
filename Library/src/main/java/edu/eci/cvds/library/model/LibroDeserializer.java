package edu.eci.cvds.library.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.eci.cvds.library.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LibroDeserializer extends JsonDeserializer<Libro> {

  @Autowired
  private LibroRepository libroRepository;

  @Override
  public Libro deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String id = p.getText();
    return libroRepository.findById(id).orElseThrow(() -> new IOException("Libro not found"));
  }
}