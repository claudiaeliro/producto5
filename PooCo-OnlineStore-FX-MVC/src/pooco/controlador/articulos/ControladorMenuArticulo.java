package pooco.controlador.articulos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pooco.controlador.Controlador;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;

public class ControladorMenuArticulo {
    private Datos datos;

    @FXML
    private  Button añadirArticulo;

    public ControladorMenuArticulo() {datos = new Datos();}

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    @FXML
    private void btnAddArticulo(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddArticuloVistaFX.fxml"));
            Parent root=loader.load();
            ControladorAddArticulo controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage=(Stage) this.añadirArticulo.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void btnMostrarArticulo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/ShowArticuloVistaFX.fxml"));
            Parent root=loader.load();
            ControladorShowArticulo controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage=(Stage) this.añadirArticulo.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void btnSalir(ActionEvent event) {
        Stage stage = (Stage) this.añadirArticulo.getScene().getWindow();
        stage.close();
        closeWindows();


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

           // stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage=(Stage) this.añadirArticulo.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
