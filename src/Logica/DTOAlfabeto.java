package Logica;

import java.util.ArrayList;
import java.util.HashMap;

public class DTOAlfabeto {

    private String nombre;
    private int id;
    private String simbolos;
    private HashMap<Integer, String> alfabetosExistentes;

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getSimbolos(){ return simbolos; }
    public void setSimbolos(String simbolos){ this.simbolos = simbolos; }

    public HashMap<Integer, String> getAlfabetosExistentes() {
        return alfabetosExistentes;
    }
    public void setAlfabetosExistentes(HashMap<Integer, String> alfabetosExistentes) {
        this.alfabetosExistentes = alfabetosExistentes;
    }
}
