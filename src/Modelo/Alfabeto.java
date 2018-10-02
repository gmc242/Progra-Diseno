package Modelo;

import Logica.server.IValidable;

import java.io.Serializable;

public class Alfabeto implements IValidable<String>, Serializable {

    protected int id;
    protected String nombre;
    protected String simbolos;
    private static int creados = 0;

    public Alfabeto(String nombre, String alfabeto){
        setId(++creados);
        setNombre(nombre);
        setSimbolos(alfabeto);
    }

    public Alfabeto(int id, String nombre, String simbolos){
        setId(id);
        setNombre(nombre);
        setSimbolos(simbolos);
        creados = (creados < id) ? id : creados;
    }

    public boolean validar(String s){
        // Valida entradas
        for (char c: s.toCharArray()){
            if(!simbolos.contains(c+""))
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

    public String getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(String simbolos) {
        this.simbolos = "";

        // Evita duplicados
        for(char c : simbolos.toCharArray()){
            if(!this.simbolos.contains(c+""))
                this.simbolos += c;
        }

    }

    public int getIndiceSimbolo(char c){
        return simbolos.indexOf(c);
    }
}
