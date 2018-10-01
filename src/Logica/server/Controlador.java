package Logica.server;

import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Modelo.alfabetos.Alfabeto;
import Modelo.algoritmos.Algoritmo;
import Modelo.DAOEscritura;

import java.io.File;
import java.util.ArrayList;


public class Controlador {

    public Boolean actualizarAlfabeto(DTOAlfabeto dto){
        return GestorAlfabetos.actualizarAlfabeto(dto);
    }

    public Boolean agregarAlfabeto(DTOAlfabeto dto){
        return GestorAlfabetos.agregarAlfabeto(dto);
    }

    public Boolean borrarAlfabeto(DTOAlfabeto dto){
        return GestorAlfabetos.borrarAlfabeto(dto);
    }

    public void procesarPeticion(DTOAlgoritmos datos, DTOAlfabeto alfa) throws Exception {
        if (datos.getAlgoritmosSelec() == null || datos.getAlgoritmosSelec().isEmpty()) {
            throw new Exception("No hay algoritmo por aplicar");
        } else {
            ArrayList<String> resultados = new ArrayList<>();
            GestorAlfabetos.setActual(alfa);
            System.out.println(datos.getFraseOrigen());
            System.out.println(GestorAlfabetos.getActual().getSimbolos());
            /*
            if (!GestorAlfabetos.getActual().validar(datos.getFraseOrigen())){
                resultados.add("Un simbolo de la oracion no pertenece al alfabeto");
                datos.setResultados(resultados);
            }
            else{
*/
                for (String algo : datos.getAlgoritmosSelec()) {
                    Algoritmo algoObjeto = FactoryAlgoritmo.getAlgoritmo(algo);

                    if (datos.isModoCodificacion())
                        resultados.add(algoObjeto.codificar(datos.getFraseOrigen()));
                    else
                        resultados.add(algoObjeto.decodificar(datos.getFraseOrigen()));
                }

                datos.setResultados(resultados);

                escribir(datos);
            }
        //}
    }

    public void escribir(DTOAlgoritmos datos) throws Exception{

        if(datos.getSalidasSelec() == null || datos.getAlgoritmosSelec().isEmpty()){
            throw new Exception("No se ha escogido un metodo de escritura");
        }else {
            for (String dao : datos.getSalidasSelec()){
                //try {
                    DAOEscritura daoObjeto = FactoryEscritura.getDAO(dao);
                    daoObjeto.escribir(datos);
                //}catch (Exception e){
                //    throw new Exception("No se podido guardar el archivo en el método de escritura " + dao);
                //}
            }
        }

    }

    public DTOAlfabeto getDTOAlfabeto(){
        DTOAlfabeto dto = new DTOAlfabeto();

        //Obtiene los alfabetos disponibles
        try {
            ArrayList<Alfabeto> alfabetos = GestorAlfabetos.getAlfabetos();
            dto.setAlfabetosExistentes(alfabetos);
        }catch (Exception e){
            dto.setAlfabetosExistentes(new ArrayList<>());
        }

        return dto;
    }

    public DTOAlgoritmos getDTOAlgoritmos(){
        DTOAlgoritmos dto = new DTOAlgoritmos();
        ArrayList<String> algoritmos = getAlgoritmos();
        dto.setAlgoritmosDisponibles(algoritmos);
        return dto;
    }

    // Solo esto debería cambiar para agregar un algoritmo
    public ArrayList<String> getAlgoritmos(){
        ArrayList<String> res = new ArrayList<>();

        /*String path = Algoritmo.class.getResource("Algoritmo.class").getPath();
        File file = new File(path);
        File parent = file.getParentFile();*/

        String path = System.getProperty("user.dir");
        path += "\\out\\production\\Progra-Diseno\\Modelo\\algoritmos\\";
        File parent = new File(path);

        File[] algos = parent.listFiles();

        for(File file : algos){
            String algoritmo = file.getName();
            algoritmo = algoritmo.substring(0, algoritmo.indexOf(".class"));
            if(!algoritmo.equals("Algoritmo"))
                res.add(algoritmo);
        }

        return res;
    }
}
