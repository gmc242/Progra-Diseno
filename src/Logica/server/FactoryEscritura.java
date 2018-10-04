package Logica.server;

import Modelo.DAOEscritura;

// Factory para obtener los distintos daos para escribir
// Aunque no se espera que el proyecto crezca en este sentido, se deja la posibilidad abierta
public class FactoryEscritura {

    public DAOEscritura getDAO(String tipo) throws Exception{
        String nombre = DAOEscritura.class.getPackage().getName();
        String path = nombre + ".Dao" + tipo;
        DAOEscritura dao = (DAOEscritura) Class.forName(path).getConstructor().newInstance();
        return dao;
    }
}
