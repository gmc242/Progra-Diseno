package Logica;

import Modelo.alfabetos.Alfabeto;

import java.util.ArrayList;

public class GestorAlfabetos {
    private static Alfabeto actual;

    public static Alfabeto getActual(){
        // A como está alfabetoPorDefecto siempre devuelve algo, sin embargo si eso cambio esto tira excepcion
        if(actual == null)
            actual = getAlfabetoPorDefecto();
        return actual;
    }

    public static boolean setActual(DTOAlfabeto dto){
        try {
            actual = DaoAlfabetos.consultarAlfabeto(dto);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static ArrayList<Alfabeto> getAlfabetos() throws Exception{
        try {
            return DaoAlfabetos.getAlfabetos();
        }catch (Exception e){
            throw new Exception("No se ha podido cargar el archivo de alfabetos");
        }
    }

    public static Alfabeto getAlfabeto(DTOAlfabeto dto) throws Exception{
        try {
            return DaoAlfabetos.consultarAlfabeto(dto);
        }catch (Exception e){
            throw new Exception("No se ha encontrado el alfabeto seleccionado");
        }
    }

    public static boolean agregarAlfabeto(DTOAlfabeto dto){
        return DaoAlfabetos.agregarAlfabeto(dto);
    }

    public static boolean borrarAlfabeto(DTOAlfabeto dto){ return DaoAlfabetos.borrarAlfabeto(dto); }

    public static boolean actualizarAlfabeto(DTOAlfabeto dto){ return DaoAlfabetos.actualizarAlfabeto(dto); }

    private static Alfabeto getAlfabetoPorDefecto(){
        try{
            return getAlfabetos().get(0);
        }catch (Exception e){
            // Se asegura que siempre hay algo que devolver, podría mejor mandar excepcion que no se pudo cargar
            Alfabeto porDefecto = new Alfabeto(0, "Por defecto", "abcdefghijklmnopqrstuvwxyz ");
            return porDefecto;
        }
    }
}
