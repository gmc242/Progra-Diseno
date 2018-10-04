package Modelo.algoritmos;

public abstract class Algoritmo {

    public static String getPath(){ return System.getProperty("user.dir") +
            "\\out\\production\\Progra-Diseno\\Modelo\\algoritmos\\"; }

    public abstract String codificar(String mensaje);
    public abstract String decodificar(String mensaje);


}
