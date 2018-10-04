package Vista.cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Vista/cliente/principal.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Proyecto Programado Diseño de Software");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        Principal principal = loader.getController();

        // Se busca cerrar el socket del cliente para que el admin sepa que no hay más interacción de este socket
        primaryStage.setOnHiding(event -> principal.finalizar());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
