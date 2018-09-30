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

            contentStream.drawString("Resultados");
            contentStream.newLine();
            contentStream.drawString(dateFormat.format(new Date()));
            contentStream.newLine();
            contentStream.drawString("Frase Origen");
            contentStream.newLine();
            contentStream.drawString(dto.getFraseOrigen());
            ArrayList<String> algoritmosSelec = dto.getAlgoritmosSelec();

            for(int i = 0; i < algoritmosSelec.size(); i++) {
                contentStream.drawString("Algoritmo: " + algoritmosSelec.get(i));
                contentStream.drawString("Modo de Algoritmo: " + modoCifrado);
                contentStream.drawString("Resultado: ");
                contentStream.drawString(dto.getResultados().get(i));
            }

            contentStream.endText();

// Make sure that the content stream is closed:
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Save the results and ensure that the document is properly closed:
        try {
            document.save( "Resultado"+DaoPdf.contPDF+".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();


        return true;
    }

}
