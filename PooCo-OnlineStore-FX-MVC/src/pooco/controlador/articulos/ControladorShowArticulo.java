package pooco.controlador.articulos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pooco.modelo.Articulo;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;

public class ControladorShowArticulo {

    @FXML
    private TextField txtIDMostrarArticulo;
    @FXML
    private TextField txtDescripcionMostrarArticulo;
    @FXML
    private TextField txtPrecioMostrarArticulo;
    @FXML
    private TextField txtEnvioMostrarArticulo;
    @FXML
    private TextField txtPreparacionMostrarArticulo;
    @FXML
    private TextField txtResultMostrar;


    private Datos datos;

    public ControladorShowArticulo() {
        datos = new Datos();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public void ShowArticulo(ActionEvent event) {
        String codigo = txtIDMostrarArticulo.getText();
        Articulo articulo;
        if (codigo != "") {
            articulo=datos.getArticuloByCodigo(codigo);
            if (articulo!=null)
            {
                txtDescripcionMostrarArticulo.setText(articulo.getDescripcion());
                txtPrecioMostrarArticulo.setText(Float.toString(articulo.getPvpVenta()));
                txtEnvioMostrarArticulo.setText(Float.toString(articulo.getGastosEnvio()));
                txtPreparacionMostrarArticulo.setText(Integer.toString(articulo.getTiempoPreparacion()));
            }
            else {
                txtResultMostrar.setVisible(true);
                txtResultMostrar.setText("Debe insertar un código.");
            }
        }
    }
    @FXML
    public void btnSalir(ActionEvent event) {
        Stage stage = (Stage) this.txtPrecioMostrarArticulo.getScene().getWindow();
        stage.close();
        closeWindows();


    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuArticuloVistaFX.fxml"));
            Parent root = loader.load();
            ControladorMenuArticulo controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage = (Stage) this.txtIDMostrarArticulo.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
