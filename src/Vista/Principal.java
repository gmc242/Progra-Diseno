package Vista;

import Logica.Controlador;
import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Principal {

    @FXML private RadioButton radioCodfificar;
    @FXML private RadioButton radioDecodificar;
    @FXML private CheckBox checkVigenere;
    @FXML private CheckBox checkCodigoTel;
    @FXML private CheckBox checkTransposicion;
    @FXML private CheckBox checkPDF;
    @FXML private CheckBox checkXML;
    @FXML private CheckBox checkTXT;
    @FXML private TextArea areaInput;
    @FXML private TextArea areaOutput;

    @FXML
    public void iniciarOnAction(){
        DTOAlfabeto dtoAlfa = new DTOAlfabeto();
        DTOAlgoritmos dtoAlgo = new DTOAlgoritmos();

        ArrayList<String> algos = new ArrayList<>();
        ArrayList<String> escritura = new ArrayList<>();

        if(checkPDF.isSelected())
            escritura.add("Pdf");
        if(checkXML.isSelected())
            escritura.add("Xml");
        if(checkTXT.isSelected())
            escritura.add("Txt");

        if(checkVigenere.isSelected())
            algos.add("Vigenere");

        if(checkTransposicion.isSelected())
            algos.add("CodigoTelefonico");

        if(checkCodigoTel.isSelected())
            algos.add("Transposicion");

        boolean modo = radioCodfificar.isSelected();

        dtoAlgo.setSalidasSelec(escritura);
        dtoAlgo.setAlgoritmosSelec(algos);
        dtoAlgo.setModoCodificacion(modo);
        dtoAlgo.setFraseOrigen(areaInput.getText());

        Controlador c = new Controlador();

        try {
            c.procesarPeticion(dtoAlgo, dtoAlfa);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        procesarOutput(dtoAlgo);
    }

    private void procesarOutput(DTOAlgoritmos dto){
        for(String s : dto.getResultados()){
            areaOutput.setText(areaOutput.getText() + "\n" + s);
        }
    }

}
