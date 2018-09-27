package Modelo.alfabetos;

import Logica.IValidable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Alfabeto implements IValidable<String> {

    protected int id;
    protected String nombre;
    protected HashMap<Integer, Character> simbolos;

    public Alfabeto(int id, String nombre, String simbolos){
        setId(id);
        setNombre(nombre);
        setSimbolos(stringToMap(simbolos));
    }

    public boolean validar(String s){
        for(char c: s.toCharArray())
            if(!simbolos.containsValue(c))
                return false;
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

    public HashMap<Integer, Character>  getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(HashMap<Integer, Character>  simbolos) {
        this.simbolos = simbolos;
    }

    private static HashMap<Integer, Character> stringToMap(String simbolos){
        HashMap<Integer, Character> map = new HashMap<>();
        int id = 1;
        for(char c : simbolos.toCharArray()){
            if(!map.containsValue(c)){
                map.put(id, c);
            }
        }
        return map;
    }
}
