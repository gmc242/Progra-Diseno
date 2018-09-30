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

    @FXML public void cargarInfo(){}

    @FXML public void cargarSimbolos(){}

    @FXML public void agregarAlfabeto(){}

    @FXML public void eliminarAlfabeto(){}

    @FXML public void modificarAlfabeto(){}

    @FXML public void refrescarAlgoritmos(){}

    @FXML public void cargarAlgoritmo(){}

    @FXML public void eliminarAlgoritmo(){}
}
