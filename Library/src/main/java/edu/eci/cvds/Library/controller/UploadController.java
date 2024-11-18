package edu.eci.cvds.Library.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.cvds.Library.service.LectorService;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.eci.cvds.Library.model.Upload;



@RestController
@RequestMapping("/uploads")
public class UploadController {
    
    @Autowired
    private LectorService lectorService;
    
    @PostMapping("/multiple-books")
    public ResponseEntity<String> uploadExelFile(@RequestParam("file") MultipartFile file, @RequestBody Upload uploadConfig) {
        try (InputStream is = file.getInputStream()) {
            
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            StringBuilder result = new StringBuilder();

            return ResponseEntity.ok("Contenido del archivo: \n" + result.toString());

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al procesar el archivo Excel: " + e.getMessage());
        }

    }
}
