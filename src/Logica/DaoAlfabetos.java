package Logica;

import Modelo.alfabetos.Alfabeto;

import java.io.File;
import java.util.*;

public class DaoAlfabetos implements IValidable<Alfabeto> {

    public static boolean agregarAlfabeto(DTOAlfabeto dto) {
        // Lo escribe en el archivo
        return true;
    }

    public static boolean borrarAlfabeto(DTOAlfabeto dto) {
        // Lo borra del archivo
        return true;
    }

    public static boolean actualizarAlfabeto(DTOAlfabeto dto){
        // Lo actualiza en el archivo
        return true;
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
        String fileNameDefined = "../Archivos/Alfabetos.csv";
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
