package Modelo;

import Logica.DTOAlgoritmos;

import java.io.IOException;

public interface DAOEscritura {

    String path = ".\\src\\Archivos\\Resultados\\"; // Generalización de la ruta

    boolean escribir(DTOAlgoritmos datos) throws IOException;

}
