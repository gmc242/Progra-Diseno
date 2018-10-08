package Vista.admin;

import Logica.server.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Logica.server.Listener;
import Modelo.Alfabeto;
import Vista.MessageBox;
import Vista.utilidades.AlfabetoConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.awt.*;
import java.beans.EventHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class Admin {

    @FXML private ComboBox<Alfabeto> alfabetosCombo;
    @FXML private TextField alfabetoNombre;
    @FXML private TextField alfabetoId;
    @FXML private TextArea alfabetoSimbolos;
    @FXML private ListView<String> algoritmosLista;
    @FXML private VBox contenedorPDF;
    @FXML private VBox contenedorTXT;
    @FXML private VBox contenedorXML;

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

        ArrayList<File> bitacoras = controlador.getBitacoras();

        for(File bitacora : bitacoras){

            Button btn = new Button(bitacora.getName());
            btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Desktop.getDesktop().open(bitacora);
                    }catch (Exception e){
                        MessageBox.crearAlerta("No se pudo abrir el archivo de bitácora.\n" + e.getMessage());
                    }
                }
            });

            if(bitacora.getName().toLowerCase().contains("pdf"))
                contenedorPDF.getChildren().add(btn);

            else if(bitacora.getName().toLowerCase().contains("xml"))
                contenedorXML.getChildren().add(btn);
            else
                contenedorTXT.getChildren().add(btn);
        }
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
                MessageBox.crearAlerta(e.getMessage());
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
            MessageBox.crearAlerta("No se pudo agregar el alfabeto");
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
                MessageBox.crearAlerta("No se pudo eliminar el alfabeto");
                // Mensaje: No se pudo eliminar exitosamente el alfabeto
            }
        }else{
            MessageBox.crearAlerta("No se ha seleccionado un alfabeto para eliminar");
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
                MessageBox.crearAlerta("La modificación no ha sido exitosa");
                // Mensaje: La modificación no fue exitosa
            }
        }else{
            MessageBox.crearAlerta("No se ha podido modificar ya que no ha sido seleccionado");
            // Muestra mensaje de que no puede modificar porque no ha seleccionado
        }
    }

    @FXML public void refrescarAlgoritmos(){
        this.dtoAlgo = controlador.getDTOAlgoritmos();
        algoritmosLista.getItems().setAll(dtoAlgo.getAlgoritmosDisponibles());
    }

    @FXML public void cargarAlgoritmo(){

        File fileIn = new FileChooser().showOpenDialog(null);

        if(fileIn != null){
            // Este path podría estar en algoritmo
            String pathOut = System.getProperty("user.dir") +
                    "\\out\\production\\Progra-Diseno\\Modelo\\algoritmos\\" + fileIn.getName();
            File fileOut = new File(pathOut);

            InputStream is = null;
            OutputStream os = null;

            try {
                is = new FileInputStream(fileIn);
                os = new FileOutputStream(fileOut);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                MessageBox.crearConfirmacion("Se ha copiado el archivo con éxito");
            }catch (Exception e){
                MessageBox.crearAlerta(e.getMessage());
            }finally {
                try{
                    is.close();
                    os.close();
                }catch (Exception e){
                    MessageBox.crearAlerta(e.getMessage());
                }
            }
        }else{
            MessageBox.crearAlerta("No se ha seleccionado un archivo");
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
                    MessageBox.crearConfirmacion("Se ha eliminado con éxito");
                    // Mensaje de éxito
                    refrescarAlgoritmos();
                } else {
                    MessageBox.crearAlerta("No se ha podido eliminar el archivo");
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
            MessageBox.crearAlerta("El proceso de escucha de clientes");// Mensaje de que ya está escuchando
        }
    }

    @FXML public void detenerProceso(){
        if(listener != null)
            listener.setActivo(false);
        else
            MessageBox.crearAlerta("No hay un proceso activo");
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
