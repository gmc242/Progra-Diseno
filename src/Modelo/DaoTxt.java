package Modelo;

import Logica.DTOAlgoritmos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaoTxt implements DAOEscritura {
    public boolean escribir (DTOAlgoritmos datos){
        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String modoCifrado = "";

            if (datos.isModoCodificacion()) {
                modoCifrado = "Codificado";
            } else {
                modoCifrado = "Decodificado";
            }

            FileWriter fichero = new FileWriter("resultado.txt");
            PrintWriter pw = new PrintWriter(fichero);
            pw.println("Fecha y hora: " + dateFormat.format(new Date()));
            pw.println("Frase origen: " + datos.getFraseOrigen());
            ArrayList<String> algoritmosSelec = datos.getAlgoritmosSelec();
            pw.println("\n");

            for(int i = 0; i < algoritmosSelec.size(); i++) {
                pw.println("Algoritmo: " + algoritmosSelec.get(i));
                pw.println("Modo de Algoritmo: " + modoCifrado);
                pw.println("Resultado: ");
                pw.println(datos.getResultados().get(i));
                pw.println("\n");
            }

            fichero.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
