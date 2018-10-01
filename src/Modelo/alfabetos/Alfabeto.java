package Modelo.alfabetos;

import Logica.IValidable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Alfabeto implements IValidable<String> {

    protected int id;
    protected String nombre;
    protected String simbolos;

    public Alfabeto(int id, String nombre, String simbolos){
        setId(id);
        setNombre(nombre);
        setSimbolos(simbolos);
    }

    public boolean validar(String s){
        for(char c: s.toCharArray()) {
            if (!simbolos.contains(""+c))
                return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String  getSimbolos() {
        return simbolos;
    }

    public char getSimbolo(int indice) {
        return 'a';
    }

    public int getIndiceSimbolo(char letra) {
        for (int i = 0; i < this.getSimbolos().length(); i++) {
            if (letra == this.getSimbolos().charAt(i)) {
                return i;
            }
        }
        return -1;
    }

    //public String getSimbolosString() { return mapToString(simbolos); }

    public void setSimbolos(String  simbolos) {
        this.simbolos = simbolos;
    }


    private static HashMap<Integer, Character> stringToMap(String simbolos){
        HashMap<Integer, Character> map = new HashMap<>();
        int id = 0;
        for(char c : simbolos.toCharArray()){
            if(!map.containsValue(c)){
                map.put(++id, c);
            }
        }
        return map;
    }

    /*
    private static String mapToString(HashMap<Integer, Character> simbolos){
        Character[] chars = new Character[simbolos.values().size()];
        simbolos.values().toArray(chars);
        return String.valueOf(chars);
    }
     */
}
