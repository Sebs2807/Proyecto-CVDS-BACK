// package edu.eci.cvds.Library.azureBlobStorage;


// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import com.azure.storage.blob.BlobClient;
// import com.azure.storage.blob.BlobClientBuilder;
// import edu.eci.cvds.library.configuration.AzureBlobStorageConfig;
// import edu.eci.cvds.library.model.CodeGenerator;
// import edu.eci.cvds.library.service.AzureBlobStorageService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.io.ByteArrayInputStream;
// import java.io.InputStream;

// class AzureBlobStorageServiceTest {

//     private AzureBlobStorageService azureBlobStorageService;

//     @Mock
//     private AzureBlobStorageConfig azureBlobStorageConfig;

//     @Mock
//     private BlobClient blobClient;

//     @Mock
//     private BlobClientBuilder blobClientBuilder;

//     @Mock
//     private CodeGenerator codeGenerator;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         azureBlobStorageService = new AzureBlobStorageService(azureBlobStorageConfig);
//     }

//     @Test
//     void testGuardarArchivoEnBlob() throws Exception {
//         String inputData = "testData";
//         String fileName = "testFile.png";
//         InputStream mockStream = new ByteArrayInputStream(new byte[]{1, 2, 3});

//         when(azureBlobStorageConfig.getConnectionString()).thenReturn("mockConnectionString");
//         when(azureBlobStorageConfig.getContainerName()).thenReturn("mockContainer");
//         when(codeGenerator.generarCodigoBarrasPngStream(inputData)).thenReturn((ByteArrayInputStream) mockStream);
//         when(blobClientBuilder.connectionString("mockConnectionString")).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.containerName("mockContainer")).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.blobName(fileName)).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.buildClient()).thenReturn(blobClient);

//         doNothing().when(blobClient).upload(mockStream, mockStream.available(), true);

//         assertDoesNotThrow(() -> azureBlobStorageService.guardarArchivoEnBlob(inputData, fileName));
//         verify(blobClient).upload(mockStream, mockStream.available(), true);
//     }

//     @Test
//     void testObtenerUrlArchivo() {
//         String fileName = "testFile.png";
//         String expectedUrl = "https://mockstorage.blob.core.windows.net/mockContainer/testFile.png";

//         when(azureBlobStorageConfig.getConnectionString()).thenReturn("mockConnectionString");
//         when(azureBlobStorageConfig.getContainerName()).thenReturn("mockContainer");
//         when(blobClientBuilder.connectionString("mockConnectionString")).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.containerName("mockContainer")).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.blobName(fileName)).thenReturn(blobClientBuilder);
//         when(blobClientBuilder.buildClient()).thenReturn(blobClient);
//         when(blobClient.getBlobUrl()).thenReturn(expectedUrl);

//         String actualUrl = azureBlobStorageService.obtenerUrlArchivo(fileName);
//         assertEquals(expectedUrl, actualUrl);
//     }
// }
