package Modelo;

import Logica.DTOAlgoritmos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaoXml implements DAOEscritura {

    public boolean escribir(DTOAlgoritmos dto){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String modoCifrado;

        if (dto.isModoCodificacion()) {
            modoCifrado = "Codificado";
        } else {
            modoCifrado = "Decodificado";
        }
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("ResultadoCifrado");

            document.appendChild(root);
            root.appendChild(document.createTextNode("\n"));

            Element fecha = document.createElement("FechaHora");
            fecha.appendChild(document.createTextNode(dateFormat.format(new Date())));
            root.appendChild(fecha);
            root.appendChild(document.createTextNode("\n"));

            Element fraseOrigen = document.createElement("FraseOrigen");
            fraseOrigen.appendChild(document.createTextNode(dto.getFraseOrigen().replace(" ","")));
            root.appendChild(fraseOrigen);
            root.appendChild(document.createTextNode("\n"));

            ArrayList<String> algoritmosSelec = dto.getAlgoritmosSelec();

            for(int i = 0; i < algoritmosSelec.size(); i++) {
                Element algoritmo = document.createElement("Algoritmo");
                algoritmo.appendChild(document.createTextNode(algoritmosSelec.get(i).replace(" ","")));
                root.appendChild(algoritmo);
                root.appendChild(document.createTextNode("\n"));


                algoritmo.appendChild(document.createTextNode("\n\t"));
                Element modoCif = document.createElement("ModoCifrado");
                modoCif.appendChild(document.createTextNode(modoCifrado));
                algoritmo.appendChild(modoCif);

                algoritmo.appendChild(document.createTextNode("\n\t"));

                Element resultado = document.createElement("Resultado");
                resultado.appendChild(document.createTextNode(dto.getResultados().get(i).replace(" ","")));
                algoritmo.appendChild(resultado);
                algoritmo.appendChild(document.createTextNode("\n"));
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("resultado.xml"));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {

            pce.printStackTrace();
            return false;

        } catch (TransformerException tfe) {

            tfe.printStackTrace();
            return false;
        }
        return true;
    }
}
