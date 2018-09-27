package Modelo;

import Logica.DTOAlgoritmos;

public class DaoPdf implements DAOEscritura {

    public boolean escribir(DTOAlgoritmos dto){
        System.out.println("Estoy escribiendo en PDF");
        return true;
    }
}
