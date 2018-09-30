package Modelo;

import Logica.DTOAlgoritmos;

import java.io.IOException;

public interface DAOEscritura {

    boolean escribir(DTOAlgoritmos datos) throws IOException;

}
