package edu.eci.cvds.library.controller;

import edu.eci.cvds.library.service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/blobs")
@PreAuthorize("hasRole('admin') or hasRole('student')")
public class BlobController {

    private final AzureBlobStorageService azureBlobStorageService;

    @Autowired
    public BlobController(AzureBlobStorageService azureBlobStorageService) {
        this.azureBlobStorageService = azureBlobStorageService;
    }

    /**
     * Endpoint para obtener la URL SAS de un blob.
     *
     * @param blobName Nombre del blob en Azure Storage.
     * @return URL con acceso temporal al blob.
     */
    @GetMapping("/blob-url/{blobName}")
    public String obtenerBlobUrl(@PathVariable String blobName) {
        return azureBlobStorageService.obtenerUrlArchivo(blobName);
    }
}
