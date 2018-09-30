package Modelo;

import Logica.DTOAlgoritmos;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaoPdf implements DAOEscritura {

   public static int contPDF = 1;
    public static final String DEST = "./hello_world.pdf";

    public boolean escribir(DTOAlgoritmos dto) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        String fraseFinal = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        PDFont font = PDType1Font.HELVETICA_BOLD;
        String modoCifrado = "";

        if (dto.isModoCodificacion()) {
            modoCifrado = "Codificado";
        } else {
            modoCifrado = "Decodificado";
        }


        // Start a new content stream which will "hold" the to be created content
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
            contentStream.beginText();

            contentStream.newLineAtOffset(25, 725);
            contentStream.setFont(font, 12);
            contentStream.setLeading(14.5f);

            contentStream.showText("Resultados");
            contentStream.newLine();
            contentStream.showText("Fecha y hora: " + dateFormat.format(new Date()));
            contentStream.newLine();
            contentStream.showText("Frase Origen");
            contentStream.newLine();
            contentStream.showText(dto.getFraseOrigen());
            contentStream.newLine();

            for(int i = 0; i < dto.getAlgoritmosSelec().size(); i++) {
                contentStream.showText("Algoritmo: " + dto.getAlgoritmosSelec().get(i));
                contentStream.newLine();
                contentStream.showText("Modo de Algoritmo: " + modoCifrado);
                contentStream.newLine();
                contentStream.showText("Resultado: ");
                contentStream.showText(dto.getResultados().get(i));
                contentStream.newLine();
            }

            contentStream.endText();

            // Make sure that the content stream is closed:
            contentStream.close();
        } catch (Exception e) {
            throw e;
        }

        // Save the results and ensure that the document is properly closed:
        try {
            document.save( "Resultado"+DaoPdf.contPDF+".pdf");
        } catch (IOException e) {
            throw e;
        }
        document.close();

        return true;
    }

}
