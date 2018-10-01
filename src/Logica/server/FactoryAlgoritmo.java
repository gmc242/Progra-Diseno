package Logica.server;

import Modelo.algoritmos.Algoritmo;

import java.util.HashMap;

public class FactoryAlgoritmo {

    public static Algoritmo getAlgoritmo(String algo) throws Exception{
        String path = Algoritmo.class.getPackage().getName();
        String pathCompleto = path + "." + algo;
        Class[] parametros = {int.class, String.class, HashMap.class};
        Algoritmo instancia = (Algoritmo) Class.forName(pathCompleto).getDeclaredConstructor().newInstance();
        return instancia;
    }

}
