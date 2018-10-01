package Vista.cliente;

import Logica.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Modelo.alfabetos.Alfabeto;
import Vista.utilidades.AlfabetoConverter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class Principal {

    @FXML private RadioButton radioCodfificar;
    @FXML private RadioButton radioDecodificar;
    @FXML private HBox contenedorAlgoritmos;
    @FXML private CheckBox checkPDF;
    @FXML private CheckBox checkXML;
    @FXML private CheckBox checkTXT;
    @FXML private TextArea areaInput;
    @FXML private TextArea areaOutput;
    @FXML private ComboBox<Alfabeto> comboAlfabetos;

    private Controlador controlador;
    private DTOAlfabeto dtoAlfa;
    private DTOAlgoritmos dtoAlgo;

    public void Principal(){}

    @FXML
    public void initialize(){
        this.controlador = new Controlador();

        this.dtoAlfa = controlador.getDTOAlfabeto();
        this.dtoAlgo = controlador.getDTOAlgoritmos();

        StringConverter<Alfabeto> conv = new AlfabetoConverter();
        comboAlfabetos.setConverter(conv);

        comboAlfabetos.getItems().addAll(dtoAlfa.getAlfabetosExistentes());
        comboAlfabetos.getSelectionModel().select(0);

        for (String s : dtoAlgo.getAlgoritmosDisponibles()){
            contenedorAlgoritmos.getChildren().add(new CheckBox(s));
        }

    }

    @FXML
    public void iniciarOnAction(){

        ArrayList<String> algos = new ArrayList<>();
        ArrayList<String> escritura = new ArrayList<>();

        if(checkPDF.isSelected())
            escritura.add("Pdf");
        if(checkXML.isSelected())
            escritura.add("Xml");
        if(checkTXT.isSelected())
            escritura.add("Txt");

        for(Node algo : contenedorAlgoritmos.getChildren()){
            CheckBox algoCheck = (CheckBox)algo;
            if(algoCheck.isSelected()){
                algos.add(algoCheck.getText());
            }
        }

        boolean modo = radioCodfificar.isSelected();

        dtoAlgo.setSalidasSelec(escritura);
        dtoAlgo.setAlgoritmosSelec(algos);
        dtoAlgo.setModoCodificacion(modo);
        dtoAlgo.setFraseOrigen(areaInput.getText());

        try {
            controlador.procesarPeticion(dtoAlgo, dtoAlfa);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        procesarOutput(dtoAlgo);
    }

    private void procesarOutput(DTOAlgoritmos dto){
        areaOutput.setText(""); // Limpia la ventana de output

        for(String s : dto.getResultados()){
            areaOutput.setText(areaOutput.getText() + "\n" + s);
        }
    }

    @FXML public void codOnClick(){
        radioDecodificar.setSelected(!radioCodfificar.isSelected());
    }

    @FXML public void decodOnClick(){
        radioCodfificar.setSelected(!radioDecodificar.isSelected());
    }
}
