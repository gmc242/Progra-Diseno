package Logica;

import Modelo.alfabetos.Alfabeto;

import java.io.Serializable;
import java.util.ArrayList;

public class DTOAlfabeto implements Serializable {

    private String nombre;
    private int id;
    private String simbolos;
    private ArrayList<Alfabeto> alfabetosExistentes;

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getSimbolos(){ return simbolos; }
    public void setSimbolos(String simbolos){ this.simbolos = simbolos; }

    public ArrayList<Alfabeto> getAlfabetosExistentes() {
        return alfabetosExistentes;
    }
    public void setAlfabetosExistentes(ArrayList<Alfabeto> alfabetosExistentes) {
        this.alfabetosExistentes = alfabetosExistentes;
    }
}
