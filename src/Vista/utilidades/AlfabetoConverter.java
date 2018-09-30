package Vista.utilidades;

import Logica.GestorAlfabetos;
import Modelo.alfabetos.Alfabeto;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class AlfabetoConverter extends StringConverter<Alfabeto> {

    public Alfabeto fromString(String string){
        try{
            ArrayList<Alfabeto> alfabetos = GestorAlfabetos.getAlfabetos();
            for(Alfabeto alfa : alfabetos)
                if(alfa.getNombre().equals(string))
                    return alfa;
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public String toString(Alfabeto alfabeto){
        return alfabeto.getNombre();
    }
}
