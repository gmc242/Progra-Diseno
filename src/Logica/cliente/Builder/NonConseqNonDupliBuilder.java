package Logica.cliente.Builder;

import java.util.Random;

public class NonConseqNonDupliBuilder extends BuilderString {

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
                if (cadenaNueva.contains(""+alfabeto.charAt(indice))) {
                  continue;
                }
                else {
                    cadenaNueva += Character.toString(alfabeto.charAt(indice));
                }
            }
        }
        setCadena(cadenaNueva);
    }

}
