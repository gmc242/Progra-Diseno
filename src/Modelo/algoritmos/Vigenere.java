package Modelo.algoritmos;

public class Vigenere extends Algoritmo {

    private int codigo;

    public String codificar(String mensaje){
        return "Metodo codificar vigenere";
    }

    public String decodificar(String mesnsaje){
        return "Metodo decodificar vigenere";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /*
    @Override
    public String codificar(String mensaje, String alfabeto) {
        int contador = 0;
        String msgCodificado = "";
        int indice = 0;
        if (mensaje.isEmpty()) {
            return "";
        }
        else {
            for(int i = 0; i < mensaje.length(); i++ ) {
                if (contador > 1) { contador = 0;}
                if (mensaje.charAt(indice) == ' ') {
                    msgCodificado += " ";
                }
                else {
                    indice = ubicarEnAlfabeto(alfabeto, mensaje.charAt(i));
                    if (contador == 0) {
                        indice += (this.getCodigo() / 10);
                    } else {
                        indice += (this.getCodigo() % 10);
                    }
                    msgCodificado += alfabeto.charAt(indice);
                }
                contador += 1;
             }
            return msgCodificado;

        }


    @Override
    public String decodificar(String mensaje){

        return super.decodificar(mensaje);
    }
    */
}
