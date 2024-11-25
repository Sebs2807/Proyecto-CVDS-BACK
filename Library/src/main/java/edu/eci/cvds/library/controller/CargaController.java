package edu.eci.cvds.library.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.cvds.library.model.Carga;
import edu.eci.cvds.library.service.CargaService;

import java.io.IOException;

@RestController
@RequestMapping("/cargas")
public class CargaController {
    
    private CargaService cargaService;

    @Autowired
    public CargaController(CargaService cargaService){
        this.cargaService = cargaService;
    }

    @PostMapping("/multiple")
    public ResponseEntity<String> cargarArchivo(@RequestParam("file") MultipartFile file, @RequestBody Carga configCarga) {
        // Verificar que el archivo no esté vacío
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío.");
        }

        try {
            // Leer el archivo Excel
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // Cargar el archivo Excel con la configuración proporcionada
            cargaService.cargarExcel(sheet, configCarga);
            
            workbook.close();
            return ResponseEntity.ok("Archivo cargado y procesado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
