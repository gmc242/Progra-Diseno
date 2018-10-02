package Modelo.algoritmos;

import Logica.server.GestorAlfabetos;

public class PalabraClave extends Algoritmo {

    private String clave = "secreto";

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String codificar(String mensaje) {
        String resultado = "";
        int cont = 0;
        int nuevoIndice;
        int indiceTxt;
        int indiceCode;
        for(int i = 0; i < mensaje.length(); i++) {
            if (cont > getClave().length()) {  //Si el contador es mayor que el largo del  codigo, se reinicia, para iterar sobre el codigo
                cont = 0;
            }
            if (mensaje.charAt(i) == ' ') {
                cont = 0;
                resultado += " ";
            } else {
                indiceTxt = GestorAlfabetos.getActual().getIndiceSimbolo(mensaje.charAt(i)); //#Busca el caracter en el abecedario
                indiceCode = getCodePos(getClave().charAt(cont));    //#Buscar el caracter del codigo en el abecedario

                nuevoIndice = indiceTxt + indiceCode; //#Suma los equivalentes para obtener el nuevo indice del caracter

                if (nuevoIndice >= GestorAlfabetos.getActual().getSimbolos().length()) { //#Si es mayor que 26, resta 26 para obtener un caracter valido
                    nuevoIndice -= GestorAlfabetos.getActual().getSimbolos().length()-1;
                }
                resultado += GestorAlfabetos.getActual().getSimbolos().charAt(nuevoIndice);
                cont += 1;
            }
        }
        return resultado;
    }

    public String decodificar(String mensaje) {
        String resultado = "";
        int cont = 0;
        int nuevoIndice;
        int indiceTxt;
        int indiceCode;
        for(int i = 0; i < mensaje.length(); i++) {
            if (cont > getClave().length()) {  //Si el contador es mayor que el largo del  codigo, se reinicia, para iterar sobre el codigo
                cont = 0;
            }
            if (mensaje.charAt(i) == ' ') {
                cont = 0;
                resultado += " ";
            } else {
                indiceTxt = GestorAlfabetos.getActual().getIndiceSimbolo(mensaje.charAt(i)); //#Busca el caracter en el abecedario
                indiceCode = getCodePos(getClave().charAt(cont));    //#Buscar el caracter del codigo en el abecedario

                nuevoIndice = indiceTxt - indiceCode; //#Suma los equivalentes para obtener el nuevo indice del caracter

                if (nuevoIndice < 0) { //#Si es mayor que 26, resta 26 para obtener un caracter valido
                    nuevoIndice += GestorAlfabetos.getActual().getSimbolos().length()-1;
                }
                resultado += GestorAlfabetos.getActual().getSimbolos().charAt(nuevoIndice);
                cont += 1;
            }
        }
        return resultado;
    }

    public int getCodePos(char letra) {
        for (int i = 0; i < this.getClave().length(); i++) {
            if (letra == this.getClave().charAt(i)) {
                return i;
            }
        }
        return -1;
    }

}
