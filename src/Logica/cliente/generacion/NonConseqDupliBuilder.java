package Logica.cliente.generacion;

import java.util.Random;

// Rol de ConcreteBuilder
public class NonConseqDupliBuilder extends BuilderString {

    public void buildString(int largo, String alfabeto) {
        String cadenaNueva = "";
        Random aleatorio = new Random(System.currentTimeMillis());
        int indice = 0;
        int indiceAnterior = -10;
        int largoAlf = alfabeto.length();
        for (int i = 0; i < largo; i++) {
            if (indice == indiceAnterior+1 || indice == indiceAnterior-1) {
                continue;
            }
            else {
                indice = aleatorio.nextInt(largoAlf);
                cadenaNueva += Character.toString(alfabeto.charAt(indice));
            }
        }
        setCadena(cadenaNueva);
    }
}

