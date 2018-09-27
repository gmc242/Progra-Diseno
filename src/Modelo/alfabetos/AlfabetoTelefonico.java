package Modelo.alfabetos;

import java.util.ArrayList;
import java.util.HashMap;

public class AlfabetoTelefonico extends Alfabeto {

    private ArrayList<HashMap<Integer,Character>> buckets;

    public AlfabetoTelefonico(int id, String nombre, String simbolos){
        super(id,nombre,simbolos);
        calcularBuckets();
    }

    @Override
    public void setSimbolos(HashMap<Integer, Character>  simbolos) {
        this.simbolos = simbolos;
        calcularBuckets();
    }

    private void calcularBuckets(){
        buckets = new ArrayList<>();
    }
}
