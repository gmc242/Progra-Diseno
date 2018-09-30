package Logica;

import Modelo.alfabetos.Alfabeto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DaoAlfabetos implements IValidable<Alfabeto> {

    public static boolean agregarAlfabeto(DTOAlfabeto dto) {
        try{
            // Obtiene los alfabetos actuales
            ArrayList<Alfabeto> alfabetos = getAlfabetos();

            // Abre el archivo para sobre-escritura
            PrintWriter pw = new PrintWriter(new File("../Archivos/Alfabetos.csv"));
            StringBuffer sb = new StringBuffer();

            // Adjunta el header del csv al buffer
            sb.append("ID,Nombre,Simbolos\n");

            // Adjunta cada alfabeto al buffer
            // Sin una librería externa no hay de otra
            for(Alfabeto alfabeto : alfabetos){
                sb.append(alfabeto.getId());
                sb.append(",");
                sb.append(alfabeto.getNombre());
                sb.append(",");
                sb.append(alfabeto.getSimbolosString());
                sb.append("/n");
            }

            // Adjunta los datos del alfabeto nuevo al buffer
            sb.append(dto.getId());
            sb.append(",");
            sb.append(dto.getNombre());
            sb.append(",");
            sb.append(dto.getSimbolos());
            sb.append("/n");

            // Escribe el buffer al archivo
            pw.write(sb.toString());
            pw.close();

            return true;

        }catch (IOException e){
            return false;
        }catch (Exception e){

            // Este caso se da si falla la operación de obtener alfabetos, probablemente porque el arhivo no existe
            // Se crea el archivo y se escriben solo los datos del alfabeto nuevo.
            try{
                // Abre el archivo para sobre-escritura
                PrintWriter pw = new PrintWriter(new File("../Archivos/Alfabetos.csv"));
                StringBuffer sb = new StringBuffer();

                // Adjunta el header del csv al buffer
                sb.append("ID,Nombre,Simbolos\n");

                // Adjunta los datos del alfabeto nuevo al buffer
                sb.append(dto.getId());
                sb.append(",");
                sb.append(dto.getNombre());
                sb.append(",");
                sb.append(dto.getSimbolos());
                sb.append("/n");

                // Escribe el buffer al archivo
                pw.write(sb.toString());
                pw.close();

                return true;

            }catch (Exception e2){
                return false;
            }
        }
    }

    public static boolean borrarAlfabeto(DTOAlfabeto dto) {
        try{
            // Obtiene los alfabetos actuales
            ArrayList<Alfabeto> alfabetos = getAlfabetos();

            // Abre el archivo para sobre-escritura
            PrintWriter pw = new PrintWriter(new File("../Archivos/Alfabetos.csv"));
            StringBuffer sb = new StringBuffer();

            // Adjunta el header del csv al buffer
            sb.append("ID,Nombre,Simbolos\n");

            // Adjunta cada alfabeto al buffer
            // Sin una librería externa no hay de otra
            for(Alfabeto alfabeto : alfabetos){
                if(alfabeto.getId() != dto.getId()){
                    sb.append(alfabeto.getId());
                    sb.append(",");
                    sb.append(alfabeto.getNombre());
                    sb.append(",");
                    sb.append(alfabeto.getSimbolosString());
                    sb.append("/n");
                }
            }

            // Escribe el buffer al archivo
            pw.write(sb.toString());
            pw.close();

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public static boolean actualizarAlfabeto(DTOAlfabeto dto){
        try{
            // Obtiene los alfabetos actuales
            ArrayList<Alfabeto> alfabetos = getAlfabetos();

            // Abre el archivo para sobre-escritura
            PrintWriter pw = new PrintWriter(new File("../Archivos/Alfabetos.csv"));
            StringBuffer sb = new StringBuffer();

            // Adjunta el header del csv al buffer
            sb.append("ID,Nombre,Simbolos\n");

            // Adjunta cada alfabeto al buffer
            // Sin una librería externa no hay de otra
            for(Alfabeto alfabeto : alfabetos){
                if(alfabeto.getId() != dto.getId()){
                    sb.append(alfabeto.getId());
                    sb.append(",");
                    sb.append(alfabeto.getNombre());
                    sb.append(",");
                    sb.append(alfabeto.getSimbolosString());
                    sb.append("/n");
                }else{
                    // Adjunta los datos del alfabeto por actualizar al buffer
                    sb.append(dto.getId());
                    sb.append(",");
                    sb.append(dto.getNombre());
                    sb.append(",");
                    sb.append(dto.getSimbolos());
                    sb.append("/n");
                }
            }

            // Escribe el buffer al archivo
            pw.write(sb.toString());
            pw.close();

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public static Alfabeto consultarAlfabeto(DTOAlfabeto dto) throws Exception{
        // Lo consulta del archivo
        ArrayList<Alfabeto> alfabetos = getAlfabetos();
        Alfabeto resultado = null;
        for (Alfabeto alpha : alfabetos) {
            if (dto.getId() == alpha.getId()) {
                resultado = alpha;
                break;
            }
        }
        return resultado;
    }

    public static ArrayList<Alfabeto> getAlfabetos() throws Exception{
        String path = new File("").getAbsolutePath();

        // Muestra los disponibles del archivo
        ArrayList<Alfabeto> alfabetos = new ArrayList<Alfabeto>();
        String fileNameDefined = path + "\\src\\Archivos\\Alfabetos.csv";
        File file = new File(fileNameDefined);

        Scanner inputStream = new Scanner(file);
        String[] fields;

        while(inputStream.hasNext()){
            String line = inputStream.nextLine();
            fields = line.split(",");

            alfabetos.add(new Alfabeto( Integer.parseInt(fields[0]),fields[1],fields[2]));
        }
        // after loop, close scanner
        inputStream.close();

        return alfabetos;
    }

    public boolean validar(Alfabeto obj){
        boolean valido = true;
        return valido;
    }


}
