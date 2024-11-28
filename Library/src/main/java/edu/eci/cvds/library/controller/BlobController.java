// package edu.eci.cvds.library.controller;

// import edu.eci.cvds.library.service.AzureBlobStorageService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;


// @RestController
// @RequestMapping("/blobs")
// public class BlobController {

//     private final AzureBlobStorageService azureBlobStorageService;

//     @Autowired
//     public BlobController(AzureBlobStorageService azureBlobStorageService) {
//         this.azureBlobStorageService = azureBlobStorageService;
//     }

//     /**
//      * Endpoint para obtener la URL SAS de un blob.
//      *
//      * @param blobName Nombre del blob en Azure Storage.
//      * @return URL con acceso temporal al blob.
//      */
//     @GetMapping("/blob-url")
//     public String obtenerBlobUrl(@RequestParam String blobName) {
//         return azureBlobStorageService.obtenerUrlArchivo(blobName);
//     }
// }
