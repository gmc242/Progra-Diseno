package Modelo.alfabetos;

import java.util.ArrayList;
import java.util.HashMap;

public class AlfabetoTelefonico extends Alfabeto {

    private ArrayList<HashMap<Integer,Character>> buckets;

    public AlfabetoTelefonico(Alfabeto alfabeto){
        super(alfabeto.getId(), alfabeto.getNombre(), alfabeto.getSimbolos());
        calcularBuckets();
    }

    public AlfabetoTelefonico(int id, String nombre, String simbolos){
        super(id,nombre,simbolos);
        calcularBuckets();
    }

    @Override
    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
        calcularBuckets();
    }

    private void calcularBuckets(){
        buckets = new ArrayList<>();
    }
}
