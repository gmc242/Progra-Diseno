package Logica.server;

import Modelo.DAOEscritura;

public class FactoryEscritura {

    public static DAOEscritura getDAO(String tipo) throws Exception{
        String nombre = DAOEscritura.class.getPackage().getName();
        String path = nombre + ".Dao" + tipo;
        DAOEscritura dao = (DAOEscritura) Class.forName(path).getConstructor().newInstance();
        return dao;
    }
}
