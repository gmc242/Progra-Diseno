package Logica.server;

import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Modelo.Alfabeto;
import Modelo.algoritmos.Algoritmo;
import Modelo.DAOEscritura;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public class Controlador {

    // Uso de patrón strategy, esta clase sería el context
    private Algoritmo strategy;

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

            GestorAlfabetos.setActual(alfa);

            ArrayList<String> resultados = new ArrayList<>();

            // Uso del patrón strategy
            for (String algo : datos.getAlgoritmosSelec()) {

                // Setea la estrategia
                setStrategy(algo);

                // Uso de la estrategia para codificar
                if(datos.isModoCodificacion())
                    resultados.add(strategy.codificar(datos.getFraseOrigen()));

                // Uso de la estrategia para decodificar
                else
                    resultados.add(strategy.decodificar(datos.getFraseOrigen()));
            }

            datos.setResultados(resultados);

            escribir(datos);
        }
    }

    public void escribir(DTOAlgoritmos datos) throws Exception{

        if(datos.getSalidasSelec() == null || datos.getAlgoritmosSelec().isEmpty()){
            throw new Exception("No se ha escogido un metodo de escritura");
        }else {

            // Declaración de la fabrica para obtener los métodos de escritura
            FactoryEscritura factory = new FactoryEscritura();

            for (String dao : datos.getSalidasSelec()){
                try {
                    // Uso de la fábrica para obtener métodos de escritura
                    DAOEscritura daoObjeto = factory.getDAO(dao);
                    // Uso del objeto obtenido de la fábrica para escribir
                    daoObjeto.escribir(datos);
                }catch (Exception e){
                    throw new Exception("No se podido guardar el archivo en el método de escritura " + dao);
                }
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

        File parent = new File(Algoritmo.getPath());

        File[] algos = parent.listFiles();

        for(File file : algos){
            String algoritmo = file.getName();
            algoritmo = algoritmo.substring(0, algoritmo.indexOf(".class"));
            if(!algoritmo.equals("Algoritmo"))
                res.add(algoritmo);
        }

        return res;
    }

    public ArrayList<File> getBitacoras(){
        File root = new File(DAOEscritura.path);
        ArrayList<File> res = new ArrayList<>(Arrays.asList(root.listFiles()));
        return res;
    }

    // setStrategy con uso de instrospección
    public void setStrategy(String algoritmo) throws Exception{
        //String path = Algoritmo.getPath() + algoritmo + ".class";

        String path = Algoritmo.class.getPackage().getName() + "." + algoritmo;
        strategy = (Algoritmo) Class.forName(path).getDeclaredConstructor().newInstance();
    }

}
