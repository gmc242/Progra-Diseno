package Modelo.algoritmos;

import Logica.server.GestorAlfabetos;

public class Binario extends Algoritmo {

    public String codificar(String mensaje){
        String resultado = "";
        int indice = 0;
        for( int i = 0; i < mensaje.length(); i++) {
            if (mensaje.charAt(i) == ' ') {
                resultado += "* ";
            }
            else {
                indice = GestorAlfabetos.getActual().getIndiceSimbolo(mensaje.charAt(i));
                resultado += Integer.toBinaryString(indice);
                if (i == mensaje.length()) {
                    break;
                }
                else {
                    resultado += " ";
                }
            }
        }
        return resultado;
    }

    public String decodificar(String mensaje) {
        String resultado = "";
        for (String part : mensaje.split(" ")) {
            if (part == "*") {
                resultado += " ";
            }
            else {
                resultado += GestorAlfabetos.getActual().getSimbolos().charAt(Integer.parseInt(part, 2));
            }
        }
        return resultado;
    }
}
