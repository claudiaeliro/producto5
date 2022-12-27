package onlinestore.controlador;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PedidosController {
    @FXML
    private Button volverPedidos;

    public void botonEnviados(ActionEvent event) {
    }

    public void botonAñadirPedido(ActionEvent event) {
    }

    public void botonEliminarPedido(ActionEvent event) {
    }

    public void botonPendientesEnvio(ActionEvent event) {
    }

    @FXML
    protected  void botonVolverPedidos(ActionEvent event) {
        Stage stage=(Stage) this.volverPedidos.getScene().getWindow();
        stage.close();;
    }
}
