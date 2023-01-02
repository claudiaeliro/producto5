/*
 * Aplicación de escritorio basada en Java que se ejecutará en el backend 
 * como alternativa a la aplicación web de un comercio electronico.
 * Persistencia en BBDD con JPA - EclipseLink
 */
package pooco.vista;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Application;

public class OnlineStore extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/GestionOS.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
//    public static void main(String[] args) {
//
//        GestionOS gestion = new GestionOS();
//        gestion.inicio();
//    }
}

