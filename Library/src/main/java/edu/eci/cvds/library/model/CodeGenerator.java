package edu.eci.cvds.library.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


@Component
public class CodeGenerator {
    
    /**
     * Genera un código de barras y lo devuelve como un InputStream para guardar en Azure Blob Storage.
     *
     * @param inputData Datos de entrada para el código de barras.
     * @return InputStream del código de barras en formato SVG.
     * @throws IOException Si ocurre un error al procesar el formato SVG.
     * @throws WriterException Si ocurre un error al generar el código de barras.
     */

    public ByteArrayInputStream generarCodigoBarrasPngStream(String inputData) throws IOException, WriterException {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix barcodeMatrix = barcodeWriter.encode(inputData, BarcodeFormat.CODE_128, 100, 50);

        // Convertir BitMatrix a BufferedImage
        BufferedImage image = new BufferedImage(barcodeMatrix.getWidth(), barcodeMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < barcodeMatrix.getHeight(); y++) {
            for (int x = 0; x < barcodeMatrix.getWidth(); x++) {
                image.setRGB(x, y, barcodeMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);  // Negro para true, blanco para false
            }
        }

        // Escribir la imagen en un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);  // Guardar como PNG
        return new ByteArrayInputStream(outputStream.toByteArray());
    }


}
