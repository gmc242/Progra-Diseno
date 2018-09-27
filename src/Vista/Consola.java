package Vista;

import Logica.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Consola {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el string que desea operar");
        String input = scan.next();

        ArrayList<String> metodos = new ArrayList<>();
        while(true){
            System.out.println("Ingrese el nombre del método que desea utilizar");
            metodos.add(scan.next());

            System.out.println("¿Desea agregar otro método? Y/N");
            if(!scan.next().equals("Y"))
                break;
        }

        System.out.println("¿Desea codificar o decodificar? C/D");
        boolean operacion = scan.next().equals("C") ? true : false;

        System.out.println("Ingrese el id del alfabeto por utilizar (0 es por defecto)");
        int idAlfa = scan.nextInt();

        ArrayList<String> escritura = new ArrayList<>();
        while(true){
            System.out.println("Ingrese el nombre de la salida que desea utilizar");
            escritura.add(scan.next());

            System.out.println("¿Desea agregar tipo de salida? Y/N");
            if(!scan.next().equals("Y"))
                break;
        }

        DTOAlfabeto dtoAlfa = new DTOAlfabeto();
        DTOAlgoritmos dtoAlgo = new DTOAlgoritmos();

        dtoAlfa.setId(idAlfa);
        dtoAlgo.setAlgoritmosSelec(metodos);
        dtoAlgo.setFraseOrigen(input);
        dtoAlgo.setModoCodificacion(operacion);
        dtoAlgo.setSalidasSelec(escritura);

        Controlador c = new Controlador();

        try {
            c.procesarPeticion(dtoAlgo, dtoAlfa);

            for(String s : dtoAlgo.getResultados()){
                System.out.println("Un resultado de la operacion es: " + s);
            }

        }catch (Exception e){
            System.out.println("No se pudo ejecutar el algoritmo, el mensaje de error es: ");
            System.out.println(e.getMessage());
        }


    }
}
