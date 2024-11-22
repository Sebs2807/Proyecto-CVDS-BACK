package edu.eci.cvds.library.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.oned.Code128Writer;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QrGenerator {
    public void generadorQrBar(String inputData) {
        // Cambiar por el identificador del ejemplar
        String data = "https://example.com"; // Nueva variable, no modifica el parámetro

        // Cambiar por la ruta donde se vayan a guardar los QR
        String qrFilePath = "qrcode.svg";
        // Cambiar por la ruta donde se vayan a guardar los códigos de barras
        String barcodeFilePath = "barcode.svg";

        Logger logger = Logger.getLogger(getClass().getName());
        int qrSize = 300;
        int barcodeWidth = 400;
        int barcodeHeight = 100;

        try {
            // Generar QR
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix qrMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, qrSize, qrSize);
            generateSVG(qrMatrix, qrFilePath, qrSize, qrSize);
            logger.log(Level.INFO, () -> "QR Code SVG generated at: " + qrFilePath);

            // Generar código de barras
            Code128Writer barcodeWriter = new Code128Writer();
            BitMatrix barcodeMatrix = barcodeWriter.encode(data, BarcodeFormat.CODE_128, barcodeWidth, barcodeHeight);
            generateSVG(barcodeMatrix, barcodeFilePath, barcodeWidth, barcodeHeight);
            logger.log(Level.INFO, () -> "Barcode SVG generated at: " + barcodeFilePath);
        } catch (WriterException | IOException e) {
            logger.log(Level.SEVERE, "Error generating QR or Barcode", e);
        }
    }

    private static void generateSVG(BitMatrix bitMatrix, String filePath, int width, int height) throws IOException {
        // Configuración de Batik para SVG
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(null, "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        // Escalar y fondo blanco
        svgGenerator.setPaint(java.awt.Color.WHITE);
        svgGenerator.fill(new Rectangle2D.Double(0, 0, width, height));
        double scaleX = width / (double) bitMatrix.getWidth();
        double scaleY = height / (double) bitMatrix.getHeight();
        // Dibujar el código en negro
        svgGenerator.setPaint(java.awt.Color.BLACK);
        for (int y = 0; y < bitMatrix.getHeight(); y++) {
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                if (bitMatrix.get(x, y)) {
                    svgGenerator.fill(new Rectangle2D.Double(x * scaleX, y * scaleY, scaleX, scaleY));
                }
            }
        }
        // Guardar SVG en archivo
        try (Writer writer = new FileWriter(filePath)) {
            svgGenerator.stream(writer, true); // true = incluye DOCTYPE
        }
    }
}
