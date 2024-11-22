package edu.eci.cvds.Library.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.cvds.Library.service.LectorService;
import edu.eci.cvds.Library.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.eci.cvds.Library.model.Upload;



@RestController
@RequestMapping("/uploads")
public class UploadController {
    
    @Autowired
    private UploadService uploadService;
    
    @PostMapping("/multiple-books")
    public void uploadExelFile(@RequestParam("file") MultipartFile file, @RequestBody Upload uploadConfig) {
        Upload upload = uploadConfig;

        

    }
}
