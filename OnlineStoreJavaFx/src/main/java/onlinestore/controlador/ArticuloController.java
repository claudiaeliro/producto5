package onlinestore.controlador;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ArticuloController {
    @FXML
    private Button volverArticulo;

    @FXML
    protected void botonMostrarArticulo (ActionEvent event) throws IOException
    {
        System.out.println("aaaA");
    }

    @FXML
    protected void botonVolverArticulo (ActionEvent event) throws IOException
    {
        Stage stage=(Stage) this.volverArticulo.getScene().getWindow();
        stage.close();;

    }

    @FXML
    protected void botonAñadirArticulo (ActionEvent event) throws IOException
    {

    }






}
