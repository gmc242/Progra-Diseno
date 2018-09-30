package Logica;

import Modelo.algoritmos.Algoritmo;
import Modelo.DAOEscritura;

import java.util.ArrayList;


public class Controlador {

    public Boolean anadirAlfabeto(DTOAlfabeto dto){
        return GestorAlfabetos.anadirAlfabeto(dto);
    }

    public void procesarPeticion(DTOAlgoritmos datos, DTOAlfabeto alfa) throws Exception {
        if (datos.getAlgoritmosSelec() == null || datos.getAlgoritmosSelec().isEmpty()) {
            throw new Exception("No hay algoritmo por aplicar");
        } else {

            GestorAlfabetos.setActual(alfa);

            ArrayList<String> resultados = new ArrayList<>();

            for (String algo : datos.getAlgoritmosSelec()) {
                Algoritmo algoObjeto = FactoryAlgoritmo.getAlgoritmo(algo);

                if(datos.isModoCodificacion())
                    resultados.add(algoObjeto.codificar(datos.getFraseOrigen()));
                else
                    resultados.add(algoObjeto.codificar(datos.getFraseOrigen()));
            }

            datos.setResultados(resultados);

            escribir(datos);
        }
    }

    public void escribir(DTOAlgoritmos datos) throws Exception{

        if(datos.getSalidasSelec() == null || datos.getAlgoritmosSelec().isEmpty()){
            throw new Exception("No se ha escogido un metodo de escritura");
        }else {
            for (String dao : datos.getSalidasSelec()){
                try {
                    DAOEscritura daoObjeto = FactoryEscritura.getDAO(dao);
                    daoObjeto.escribir(datos);
                }catch (Exception e){
                    throw new Exception("No se podido guardar el archivo en el método de escritura " + dao);
                }
            }
        }

    }

    // Solo esto debería cambiar para agregar un algoritmo
    public String[] getAlgoritmos(){
        String[] res =  {"Transposicion", "Vigenere", "AlfabetoTelefonico"};
        return res;
    }
}
