package Logica.server;

import Logica.DTOAlfabeto;
import Modelo.alfabetos.Alfabeto;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DaoAlfabetos implements IValidable<Alfabeto> {

    private static String path = System.getProperty("user.dir") + "\\src\\Archivos\\Alfabetos.csv";

    public static boolean agregarAlfabeto(DTOAlfabeto dto) {
        try{

            // Obtiene los alfabetos actuales
            ArrayList<Alfabeto> alfabetos = getAlfabetos();

            // Abre el archivo para sobre-escritura
            PrintWriter pw = new PrintWriter(new File(path));
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
                sb.append(alfabeto.getSimbolos());
                sb.append("\n");
            }

            // Crea una instancia
            Alfabeto nuevo = new Alfabeto(dto.getNombre(), dto.getSimbolos());

            // Adjunta los datos del alfabeto nuevo al buffer
            sb.append(nuevo.getId());
            sb.append(",");
            sb.append(nuevo.getNombre());
            sb.append(",");
            sb.append(nuevo.getSimbolos());

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

                // Crea una instancia
                Alfabeto nuevo = new Alfabeto(dto.getNombre(), dto.getSimbolos());

                // Adjunta los datos del alfabeto nuevo al buffer
                sb.append(nuevo.getId());
                sb.append(",");
                sb.append(nuevo.getNombre());
                sb.append(",");
                sb.append(nuevo.getSimbolos());

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
            PrintWriter pw = new PrintWriter(new File(path));
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
                    sb.append(alfabeto.getSimbolos());
                    sb.append("\n");
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
            PrintWriter pw = new PrintWriter(new File(path));
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
                    sb.append(alfabeto.getSimbolos());
                    sb.append("\n");
                }else{
                    // Adjunta los datos del alfabeto por actualizar al buffer
                    sb.append(dto.getId());
                    sb.append(",");
                    sb.append(dto.getNombre());
                    sb.append(",");
                    sb.append(dto.getSimbolos());
                    sb.append("\n");
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

        // Muestra los disponibles del archivo
        ArrayList<Alfabeto> alfabetos = new ArrayList<Alfabeto>();
        File file = new File(path);

        Scanner inputStream = new Scanner(file);
        String[] fields;
        inputStream.nextLine(); // Consume el header

        while(inputStream.hasNext()){
            String line = inputStream.nextLine();
            fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
            alfabetos.add(new Alfabeto(id,fields[1],fields[2]));
        }
        // after loop, close scanner
        inputStream.close();

        return alfabetos;
    }

    public static String getPath(){
        return path;
    }

    public boolean validar(Alfabeto obj){
        boolean valido = true;
        return valido;
    }

}
