package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTOAlgoritmos implements Serializable {

    private String fraseOrigen;
    private ArrayList<String> algoritmosSelec;
    private ArrayList<String> salidasSelec;
    private ArrayList<String> resultados;
    private ArrayList<String> algoritmosDisponibles;
    private boolean modoCodificacion;

    public String getFraseOrigen() {
        return fraseOrigen;
    }

    public void setFraseOrigen(String fraseOrigen) {
        this.fraseOrigen = fraseOrigen;
    }

    public ArrayList<String> getAlgoritmosSelec() {
        return algoritmosSelec;
    }

    public void setAlgoritmosSelec(ArrayList<String> algoritmosSelec) {
        this.algoritmosSelec = algoritmosSelec;
    }

    public ArrayList<String> getSalidasSelec() {
        return salidasSelec;
    }

    public void setSalidasSelec(ArrayList<String> salidasSelec) {
        this.salidasSelec = salidasSelec;
    }

    public ArrayList<String> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<String> resultados) {
        this.resultados = resultados;
    }

    public ArrayList<String> getAlgoritmosDisponibles(){
        return algoritmosDisponibles;
    }

    public void setAlgoritmosDisponibles(ArrayList<String> algoritmosDisponibles){
        this.algoritmosDisponibles = algoritmosDisponibles;
    }

    public boolean isModoCodificacion() {
        return modoCodificacion;
    }

    public void setModoCodificacion(boolean modoCodificacion) {
        this.modoCodificacion = modoCodificacion;
    }

}
