package Logica;

import Modelo.algoritmos.Algoritmo;
import Modelo.DAOEscritura;

import java.util.ArrayList;

public class Controlador {

    public void procesarPeticion(DTOAlgoritmos datos, DTOAlfabeto alfa) throws Exception {
        if (datos.getAlgoritmosSelec() == null || datos.getAlgoritmosSelec().isEmpty()) {
            throw new Exception("No hay algoritmo por aplicar");
        } else {

            GestorAlfabetos.setActual(alfa);

            try {
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

            } catch (Exception e) {
                throw new Exception("Alguno de los lenguajes escogidos no existe en el ambiente");
            }
        }
    }

    public void escribir(DTOAlgoritmos datos) throws Exception{
        if(datos.getSalidasSelec() == null || datos.getAlgoritmosSelec().isEmpty()){
            throw new Exception("No se ha escogido un metodo de escritura");
        }else {
            try {
                for (String dao : datos.getSalidasSelec()){
                    DAOEscritura daoObjeto = FactoryEscritura.getDAO(dao);
                    daoObjeto.escribir(datos);
                }
            }catch (Exception e){
                throw new Exception("El modo de escritura pedido no está implementado");
            }
        }
    }

    // Solo esto debería cambiar para agregar un algoritmo
    public String[] getAlgoritmos(){
        String[] res =  {"Transposicion", "Vigenere", "AlfabetoTelefonico"};
        return res;
    }
}
