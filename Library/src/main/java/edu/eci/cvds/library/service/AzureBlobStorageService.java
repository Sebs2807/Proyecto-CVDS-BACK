package edu.eci.cvds.library.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import edu.eci.cvds.library.configuration.AzureBlobStorageConfig;
import edu.eci.cvds.library.model.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AzureBlobStorageService {

    private final AzureBlobStorageConfig azureBlobStorageConfig;

    @Autowired
    public AzureBlobStorageService(AzureBlobStorageConfig azureBlobStorageConfig) {
        this.azureBlobStorageConfig = azureBlobStorageConfig;
    }

    /**
     * Guarda un archivo generado a partir de un código de barras o QR en Azure Blob Storage.
     *
     * @param inputData El contenido del archivo que se va a guardar.
     * @param fileName  El nombre del archivo que se almacenará.
     */
    public void guardarArchivoEnBlob(String inputData, String fileName) {
        try {
            CodeGenerator codigoBarrasGenerator = new CodeGenerator();
            InputStream qrStream = codigoBarrasGenerator.generarCodigoBarrasPngStream(inputData);

            BlobClient blobClient = new BlobClientBuilder()
                    .connectionString(azureBlobStorageConfig.getConnectionString())
                    .containerName(azureBlobStorageConfig.getContainerName())
                    .blobName(fileName)
                    .buildClient();

            blobClient.upload(qrStream, qrStream.available(), true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el archivo en Azure Blob Storage", e);
        }
    }

    /**
     * Obtiene la URL pública de un archivo almacenado en Azure Blob Storage.
     *
     * @param fileName El nombre del archivo cuyo enlace se desea obtener.
     * @return La URL pública del archivo.
     */
    public String obtenerUrlArchivo(String fileName) {
        try {
            BlobClient blobClient = new BlobClientBuilder()
                    .connectionString(azureBlobStorageConfig.getConnectionString())
                    .containerName(azureBlobStorageConfig.getContainerName())
                    .blobName(fileName)
                    .buildClient();

            return blobClient.getBlobUrl(); // Obtiene la URL completa del blob
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la URL del archivo en Azure Blob Storage", e);
        }
    }
}
