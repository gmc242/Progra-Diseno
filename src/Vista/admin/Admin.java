package Vista.admin;

import Logica.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Modelo.alfabetos.Alfabeto;
import Vista.utilidades.AlfabetoConverter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class Admin {

    @FXML private ComboBox<Alfabeto> alfabetosCombo;
    @FXML private TextField alfabetoNombre;
    @FXML private TextField alfabetoId;
    @FXML private TextArea alfabetoSimbolos;
    @FXML private ListView<String> algoritmosLista;

    private Controlador controlador;
    private DTOAlfabeto dto;
    private DTOAlgoritmos dtoAlgo;

    @FXML public void initialize(){
        this.controlador = new Controlador();
        this.dto = controlador.getDTOAlfabeto();
        this.dtoAlgo = controlador.getDTOAlgoritmos();

        StringConverter<Alfabeto> conv = new AlfabetoConverter();
        alfabetosCombo.setConverter(conv);

        alfabetosCombo.getItems().addAll(dto.getAlfabetosExistentes());
        alfabetosCombo.getSelectionModel().select(0);

        algoritmosLista.getItems().addAll(dtoAlgo.getAlgoritmosDisponibles());

    }

    @FXML public void cargarInfo(){
        Alfabeto alfa = alfabetosCombo.getValue();
        alfabetoNombre.setText(alfa.getNombre());
        alfabetoSimbolos.setText(alfa.getSimbolosString());
        alfabetoId.setText(String.valueOf(alfa.getId()));
    }

    @FXML public void cargarSimbolos(){}

    @FXML public void agregarAlfabeto(){
        popularDTO();
        if(alfabetoId.getText().equals("") || alfabetoId.getText() == null)
            controlador.agregarAlfabeto(dto);
    }

    @FXML public void eliminarAlfabeto(){
        popularDTO();
        if(!alfabetoId.getText().equals("") && alfabetoId.getText() != null)
            controlador.borrarAlfabeto(dto);
    }

    @FXML public void modificarAlfabeto(){
        popularDTO();
        if(!alfabetoId.getText().equals("") && alfabetoId.getText() != null)
            controlador.actualizarAlfabeto(dto);
    }

    @FXML public void refrescarAlgoritmos(){
        this.dtoAlgo = controlador.getDTOAlgoritmos();
        algoritmosLista.getItems().addAll(dtoAlgo.getAlgoritmosDisponibles());
    }

    @FXML public void cargarAlgoritmo(){
        // Abrir un file chooser
    }

    @FXML public void eliminarAlgoritmo(){
        // Borrar el archivo
    }

    private void popularDTO(){
        dto.setNombre(alfabetoNombre.getText());
        dto.setId(Integer.valueOf(alfabetoId.getText()));
        dto.setSimbolos(alfabetoSimbolos.getText());
    }
}
