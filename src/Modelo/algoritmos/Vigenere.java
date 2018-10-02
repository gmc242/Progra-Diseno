package Modelo.algoritmos;

import Logica.server.GestorAlfabetos;

public class Vigenere extends Algoritmo {

    private int codigo = 23;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String codificar(String mensaje) {
        int contador = 0;
        String msgCodificado = "";
        int indice;
        if (mensaje.isEmpty()) {
            return "";
        }
        else {
            for (int i = 0; i < mensaje.length(); i++) {
                if (contador > 1) {
                    contador = 0;
                }
                if (mensaje.charAt(i) == ' ') {
                    msgCodificado += " ";
                } else {
                    indice = GestorAlfabetos.getActual().getIndiceSimbolo(mensaje.charAt(i));
                    if (contador == 0) {
                        indice += (this.getCodigo() / 10);
                    } else {
                        indice += (this.getCodigo() % 10);
                    }
                    if (indice >= GestorAlfabetos.getActual().getSimbolos().length()) {
                        indice -= (GestorAlfabetos.getActual().getSimbolos().length())-1;
                    }
                    msgCodificado += GestorAlfabetos.getActual().getSimbolos().charAt(indice);
                }
                contador += 1;
            }
        }
        return msgCodificado;
    }


    @Override
    public String decodificar(String mensaje) {
        int contador = 0;
        String msgCodificado = "";
        int indice = 0;
        if (mensaje.isEmpty()) {
            return "";
        } else {
            for (int i = 0; i < mensaje.length(); i++) {
                if (contador > 1) {
                    contador = 0;
                }
                if (mensaje.charAt(i) == ' ') {
                    msgCodificado += " ";
                } else {
                    indice = GestorAlfabetos.getActual().getIndiceSimbolo(mensaje.charAt(i));
                    if (contador == 0) {
                        indice -= (this.getCodigo() / 10);
                    } else {
                        indice -= (this.getCodigo() % 10);
                    }
                    if (indice < 0) {
                        indice += GestorAlfabetos.getActual().getSimbolos().length();
                    }
                    msgCodificado += GestorAlfabetos.getActual().getSimbolos().charAt(indice);
                }
                contador += 1;
            }


            }
            return msgCodificado;
    }


}
