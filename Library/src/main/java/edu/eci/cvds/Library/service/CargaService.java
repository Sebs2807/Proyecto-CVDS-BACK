package edu.eci.cvds.Library.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import edu.eci.cvds.Library.model.Carga;

import java.util.Iterator;

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
            
            // Obtener los valores de las celdas según la configuración
            // Por ejemplo, puedes usar el objeto `carga` para saber qué columnas procesar
            Cell nameBookCell = row.getCell(carga.getNombreLibro());
            Cell authorCell = row.getCell(carga.getAutor());
            Cell editorialCell = row.getCell(carga.getEditorial());
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

            if (editorialCell != null) {
                String editorial = editorialCell.toString();
                System.out.println("Editorial: " + editorial);
            }

            // Procesa las celdas adicionales de acuerdo con la configuración
            // Por ejemplo, podrías almacenar los valores en una base de datos
        }
    }
}
