package Modelo.algoritmos;

import Logica.server.GestorAlfabetos;

import java.util.ArrayList;
import java.util.Objects;

public class CodigoTelefonico extends Algoritmo {

    private ArrayList<String> buckets = new ArrayList<>();

    public String codificar(String mensaje) {
        llenarBuckets();
        String resultado = "";
        for(int i = 0; i < mensaje.length(); i++) {
            if (mensaje.charAt(i) == ' ') {
                resultado += "*";
            }
            resultado += ubicarEnBucket(mensaje.charAt(i));
            if (i == mensaje.length()) {break;}
            resultado += " ";
        }
        return resultado;
    }

    public String decodificar(String mensaje) {
        llenarBuckets();
        String resultado = "";
        int num = 0;
        for (String part : mensaje.split(" ")) {
            System.out.println(part);
            if (Objects.equals(part, "*")) {
                resultado += " ";
            }
            else {
                num = Integer.parseInt(part);
                resultado += Character.toString(buckets.get(num/10).charAt(num%10));
            }
        }
        return resultado;
    }

    private void llenarBuckets() {
        if (buckets.isEmpty()) {
            this.calcularBuckets();
        }
    }

    private void calcularBuckets() {
        int tamanoBuckets = (GestorAlfabetos.getActual().getSimbolos().length() / 9);
        if (tamanoBuckets * 9 < GestorAlfabetos.getActual().getSimbolos().length()) {
            tamanoBuckets += 1;
        }
        String bucket = "";
        int cont = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < tamanoBuckets; j++) {
                if (cont >= GestorAlfabetos.getActual().getSimbolos().length()) {
                    break;
                }
                bucket += GestorAlfabetos.getActual().getSimbolos().charAt(cont);
                cont += 1;
            }
            if (!bucket.isEmpty()) {
                this.buckets.add(bucket);
                bucket = "";
            }
        }
    }

    private String ubicarEnBucket(char letra) {
        String indice = "";
        for (int i = 0 ; i < buckets.size(); i++) {
            for (int j = 0; j < buckets.get(i).length(); j++) {
                if (buckets.get(i).charAt(j) == letra) {
                    indice = Integer.toString(i) + Integer.toString(j);
                }
            }
        }
        return indice;
    }
}
