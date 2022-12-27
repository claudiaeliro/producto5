package onlinestore.controlador;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientesController {
    @FXML
    private Button volverClientes;

    public void botonClientesPremium(ActionEvent event) {
    }

    public void botonAñadirCliente(ActionEvent event) {
    }

    public void botonMostrarClientes(ActionEvent event) {
    }

    public void botonClientesEstandard(ActionEvent event) {
    }

    @FXML
    protected  void botonVolverClientes(ActionEvent event) {
        Stage stage=(Stage) this.volverClientes.getScene().getWindow();
        stage.close();;
    }
}
