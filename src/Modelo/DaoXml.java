package Modelo;

import Logica.DTOAlgoritmos;

public class DaoXml implements DAOEscritura {

    public boolean escribir(DTOAlgoritmos dto){
        System.out.println("Estoy escribiendo en XML");
        return true;
    }
}
