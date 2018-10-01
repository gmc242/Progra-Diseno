package Modelo.algoritmos;

public class Transposicion extends Algoritmo {

    @Override
    public String codificar(String mensaje){
        String resultado = "";
        for (String part : mensaje.split(" ")) {
            resultado += (new StringBuilder(part).reverse().toString());
            resultado += " ";
        }
        return resultado;
    }

    @Override
    public String decodificar(String mensaje){

        return codificar(mensaje);
    }
}
