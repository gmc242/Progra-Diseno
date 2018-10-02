package Logica.cliente.Builder;

import java.util.Random;

public class ConseqDupliBuilder extends BuilderString{

    public void buildString(int largo, String alfabeto) {
        String cadenaNueva = "";
        Random aleatorio = new Random(System.currentTimeMillis());
        int indice = 0;
        int largoAlf = alfabeto.length();
        for (int i = 0; i < largo; i++) {
            indice = aleatorio.nextInt(largoAlf);
            cadenaNueva += Character.toString(alfabeto.charAt(indice));
        }
        setCadena(cadenaNueva);
    }
}
