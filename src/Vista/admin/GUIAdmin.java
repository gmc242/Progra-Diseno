package Vista.admin;

import Logica.cliente.ControladorCliente;
import Vista.cliente.Principal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIAdmin extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Vista/admin/Admin.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Proyecto Programado Diseño de Software");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
