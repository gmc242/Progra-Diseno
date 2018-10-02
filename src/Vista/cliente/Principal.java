package Vista.cliente;

import Logica.DTOAlfabeto;
import Logica.DTOAlgoritmos;
import Logica.cliente.ControladorCliente;
import Logica.cliente.DTOString;
import Logica.server.Controlador;
import Modelo.alfabetos.Alfabeto;
import Vista.utilidades.AlfabetoConverter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
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
    @FXML private RadioButton radioManual;
    @FXML private RadioButton radioConse;
    @FXML private RadioButton radioDupli;
    @FXML private RadioButton radioAmbos;
    @FXML private TextField largoField;
    private final ToggleGroup groupGeneracion = new ToggleGroup();

    private ControladorCliente controlador;

    public void Principal(){}

    @FXML
    public void initialize(){

        radioManual.setToggleGroup(groupGeneracion);
        radioAmbos.setToggleGroup(groupGeneracion);
        radioDupli.setToggleGroup(groupGeneracion);
        radioConse.setToggleGroup(groupGeneracion);

        controlador = new ControladorCliente();

        controlador.initialize();

        DTOAlgoritmos dtoAlgo = controlador.getDtoAlgoritmos();
        DTOAlfabeto dtoAlfa = controlador.getDtoAlfabeto();

        if(dtoAlfa != null && dtoAlgo != null){
            // Construye la interfaz
            StringConverter<Alfabeto> conv = new AlfabetoConverter();
            comboAlfabetos.setConverter(conv);

            comboAlfabetos.getItems().addAll(dtoAlfa.getAlfabetosExistentes());
            comboAlfabetos.getSelectionModel().select(0);

            for (String s : dtoAlgo.getAlgoritmosDisponibles()){
                contenedorAlgoritmos.getChildren().add(new CheckBox(s));
            }
        }else{
            System.out.println("No se pudo obtener los objetos DTO.");
        }

    }

    @FXML
    public void iniciarOnAction(){

        DTOAlfabeto dtoAlfa = controlador.getDtoAlfabeto();
        DTOAlgoritmos dtoAlgo = controlador.getDtoAlgoritmos();

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
        dtoAlfa.setId(comboAlfabetos.getValue().getId());

        controlador.setDtoAlfabeto(dtoAlfa);
        controlador.setDtoAlgoritmos(dtoAlgo);

        try {
            // Envía los DTO's con la info para procesar la peticion
            controlador.enviarDTOs();
            // Recibe los DTO's con los resultados
            controlador.recibirDTOs();
            // Actualiza la ventana
            procesarOutput(controlador.getDtoAlgoritmos());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML public void codOnClick(){
        radioDecodificar.setSelected(!radioCodfificar.isSelected());
    }

    @FXML public void decodOnClick(){
        radioCodfificar.setSelected(!radioDecodificar.isSelected());
    }

    @FXML public void generarOnClick(){
        RadioButton seleccionado = (RadioButton) groupGeneracion.getSelectedToggle();
        if(seleccionado != null){
            if(seleccionado.getText().equals("Manual")) {
                areaInput.setEditable(true);
            }
            else{
                areaInput.setEditable(false);
                DTOString dto = controlador.getDtoString();
                dto.setTipo(seleccionado.getText());
                dto.setAlfabeto(comboAlfabetos.getValue().getSimbolos());
                dto.setLargo(Integer.valueOf(largoField.getText()));
                controlador.generarString(dto);
                areaInput.setText(dto.getResultado());
            }
        }else{
            // Muestra mensaje: No ha seleccionado ningún método de codificación
        }

    }

    private void procesarOutput(DTOAlgoritmos dto){
        areaOutput.setText(""); // Limpia la ventana de output

        for(String s : dto.getResultados()){
            areaOutput.setText(areaOutput.getText() + "\n" + s);
        }
    }

}
