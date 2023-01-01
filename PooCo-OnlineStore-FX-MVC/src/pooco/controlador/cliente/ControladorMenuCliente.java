package pooco.controlador.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pooco.controlador.Controlador;
import pooco.vista.OnlineStore;

import java.io.IOException;

public class ControladorMenuCliente {

    @FXML
    private Button añadirCliente;

    public void btnAddCliente(ActionEvent event) {
    }

    public void btnMostrarTodosClientes(ActionEvent event) {
    }
    @FXML
    public void btnSalir(ActionEvent event) {
        Stage stage = (Stage) this.añadirCliente.getScene().getWindow();
        stage.close();
        closeWindows();

    }


    public void btnMostrarClientesEstandard(ActionEvent event) {
    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/GestionOS.fxml"));
            Parent root=loader.load();
            Controlador controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage=(Stage) this.añadirCliente.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void btnMostrarClientesPremium(ActionEvent event) {
    }
}
