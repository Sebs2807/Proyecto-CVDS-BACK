package edu.eci.cvds.Library.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import edu.eci.cvds.Library.model.Carga;
import edu.eci.cvds.Library.model.Categoria;
import edu.eci.cvds.Library.model.Libro;
import edu.eci.cvds.Library.repository.LibroRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class CargaService {

    public void cargarExcel(Sheet sheet, Carga carga) {
        // Iterar sobre las filas del archivo
        Iterator<Row> rowIterator = sheet.iterator();
        
        // Salta la primera fila si es el encabezado (opcional)
        if (rowIterator.hasNext()) {
            rowIterator.next();  // Salta la fila de encabezados si es necesario
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Cell nameBookCell = row.getCell(Carga.letraAIndice(carga.getNombreLibro()));
            Cell authorCell = row.getCell(Carga.letraAIndice(carga.getAutor()));
            Cell editorialCell = row.getCell(Carga.letraAIndice(carga.getEditorial()));
            Cell edicionCell = row.getCell(Carga.letraAIndice(carga.getEdicion()));

            String nombreLibro = nameBookCell != null ? nameBookCell.getStringCellValue() : null;
            String autor = authorCell != null ? authorCell.getStringCellValue() : null;
            String editorial = editorialCell != null ? editorialCell.getStringCellValue() : null;
            String edicion = edicionCell != null ? edicionCell.getStringCellValue() : null;

            Libro queryLibro = LibroRepository.buscarPorCualquierCampo(nombreLibro, autor, editorial, edicion);

            if (queryLibro != null){
                Cell categoriaCell = row.getCell(Carga.letraAIndice(carga.getCategoria()));
                String categoriaABuscar = categoriaCell != null ? categoriaCell.getStringCellValue() : null;

                if(!queryLibro.findCategoria(categoriaABuscar)){
                    Categoria categoria = new Categoria(categoriaABuscar);
                    queryLibro.addCategoria(categoria);
                }
            }

            // Agregar más celdas según lo necesites...

            // Validar y procesar las celdas, dependiendo de los tipos de datos esperados
            if (nameBookCell != null) {
                String nameBook = nameBookCell.toString();
                System.out.println("Nombre del libro: " + nameBook);
            }

            if (authorCell != null) {
                String author = authorCell.toString();
                System.out.println("Autor: " + author);
            }


            // Procesa las celdas adicionales de acuerdo con la configuración
            // Por ejemplo, podrías almacenar los valores en una base de datos
        }
    }
}
