package Vista.admin;

import Logica.server.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Logica.server.Listener;
import Modelo.Alfabeto;
import Vista.utilidades.AlfabetoConverter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class Admin {

    @FXML private ComboBox<Alfabeto> alfabetosCombo;
    @FXML private TextField alfabetoNombre;
    @FXML private TextField alfabetoId;
    @FXML private TextArea alfabetoSimbolos;
    @FXML private ListView<String> algoritmosLista;

    private Controlador controlador;
    private DTOAlfabeto dto;
    private DTOAlgoritmos dtoAlgo;
    private Listener listener;

    @FXML public void initialize(){
        this.controlador = new Controlador();
        this.dto = controlador.getDTOAlfabeto();
        this.dtoAlgo = controlador.getDTOAlgoritmos();

        StringConverter<Alfabeto> conv = new AlfabetoConverter();
        alfabetosCombo.setConverter(conv);

        alfabetosCombo.getItems().setAll(dto.getAlfabetosExistentes());
        alfabetosCombo.getSelectionModel().select(0);

        algoritmosLista.getItems().addAll(dtoAlgo.getAlgoritmosDisponibles());

    }

    @FXML public void cargarInfo(){
        Alfabeto alfa = alfabetosCombo.getValue();
        alfabetoNombre.setText(alfa.getNombre());
        alfabetoSimbolos.setText(alfa.getSimbolos());
        alfabetoId.setText(String.valueOf(alfa.getId()));
    }

    @FXML public void cargarSimbolos(){
        File file = new FileChooser().showOpenDialog(null);
        if(file != null){
            try {
                Scanner scanner = new Scanner(new FileInputStream(file));
                String simbolos = "";
                while(scanner.hasNext())
                    simbolos += scanner.next();
                alfabetoSimbolos.setText(simbolos);
            }catch (Exception e){
                // Muestra mensaje de error
            }
        }else{
            // Muestra mensaje porque no se escogió archivo
        }
    }

    @FXML public void agregarAlfabeto(){
        popularDTO();
        if(controlador.agregarAlfabeto(dto)){
            this.dto = controlador.getDTOAlfabeto();
            this.alfabetosCombo.getItems().setAll(dto.getAlfabetosExistentes());
            this.alfabetosCombo.getSelectionModel().select(alfabetosCombo.getItems().size() - 1);
        }else{
            // Muestra mensaje de que no pudo agregar
        }
    }

    @FXML public void eliminarAlfabeto(){
        popularDTO();
        if(!alfabetoId.getText().equals("") && alfabetoId.getText() != null) {
            if(controlador.borrarAlfabeto(dto)){
                this.dto = controlador.getDTOAlfabeto();
                this.alfabetosCombo.getItems().setAll(dto.getAlfabetosExistentes());
                resetearGUI();
            }else{
                // Mensaje: No se pudo eliminar exitosamente el alfabeto
            }
        }else{
            // Muestra mensaje de que no puede modificar porque no se ha seleccionado
        }

    }

    @FXML public void modificarAlfabeto(){
        popularDTO();
        if(!alfabetoId.getText().equals("") && alfabetoId.getText() != null) {
            if(controlador.actualizarAlfabeto(dto)) {
                this.dto = controlador.getDTOAlfabeto();
                this.alfabetosCombo.getItems().setAll(dto.getAlfabetosExistentes());
            }else{
                // Mensaje: La modificación no fue exitosa
            }
        }else{
            // Muestra mensaje de que no puede modificar porque no ha seleccionado
        }
    }

    @FXML public void refrescarAlgoritmos(){
        this.dtoAlgo = controlador.getDTOAlgoritmos();
        algoritmosLista.getItems().setAll(dtoAlgo.getAlgoritmosDisponibles());
    }

    @FXML public void cargarAlgoritmo() throws IOException{

        File fileIn = new FileChooser().showOpenDialog(null);

        if(fileIn != null){
            // Este path podría estar en algoritmo
            String pathOut = System.getProperty("user.dir") +
                    "\\out\\production\\Progra-Diseno\\Modelo\\algoritmos\\" + fileIn.getName();
            File fileOut = new File(pathOut);

            InputStream is = null;
            OutputStream os = null;

            try{
                is = new FileInputStream(fileIn);
                os = new FileOutputStream(fileOut);

                byte[] buffer = new byte[1024];
                int length;

                while((length = is.read(buffer)) > 0){
                    os.write(buffer, 0, length);
                }
            }finally {
                is.close();
                os.close();
            }
        }else{
            // No se escogio archivo
        }
    }

    @FXML public void eliminarAlgoritmo(){
        // Borrar el archivo
        ObservableList<String> archivos = algoritmosLista.getItems();

        for(String archivo : archivos){
            String path = System.getProperty("user.dir") + "\\out\\production\\Progra-Diseno\\Modelo\\algoritmos\\" +
                    archivo + ".class";
            File file = new File(path);
            try {
                if (file.delete()) {
                    // Mensaje de éxito
                    refrescarAlgoritmos();
                } else {
                    // Mensaje de error
                }
            }catch (ConcurrentModificationException e){
                // ¿Consume el error?
            }
        }
    }

    @FXML public void iniciarProceso(){
        if(listener == null){
            listener = new Listener(controlador);
            Thread hilo = new Thread(listener);
            hilo.start();
        }else{
            // Mensaje de que ya está escuchando
        }
    }

    @FXML public void detenerProceso(){
        if(listener != null)
            listener.setActivo(false);
        else
            System.out.println("No hay un proceso activo");
            //Mensaje de que no hay listener escuchand
    }

    private void popularDTO(){
        this.dto.setNombre(alfabetoNombre.getText());
        if(!alfabetoId.getText().equals("") && alfabetoId != null)
            this.dto.setId(Integer.valueOf(alfabetoId.getText()));
        this.dto.setSimbolos(alfabetoSimbolos.getText());
    }

    private void resetearGUI(){
        this.alfabetoId.setText("");
        this.alfabetosCombo.getSelectionModel().select(0);
        this.alfabetoSimbolos.setText("");
    }
}
