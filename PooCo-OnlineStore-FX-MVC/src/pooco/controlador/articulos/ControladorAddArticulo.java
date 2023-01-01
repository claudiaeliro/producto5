package pooco.controlador.articulos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;

public class ControladorAddArticulo {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtEnvio;
    @FXML
    private TextField txtPreparacion;
    @FXML
    private TextField txtResult;

    private Datos datos;

    public ControladorAddArticulo() {
        datos = new Datos();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }


    @FXML
    public void addArticulos(ActionEvent event) {
        boolean success=false;
        String codigo = txtId.getText();
        if (codigo!="") {
            String descripcionArticulo = txtDescripcion.getText();
            Float pvpVentaArticulo = Float.parseFloat(txtPrecio.getText());
            Float gastosEnvioArticulo = Float.parseFloat(txtEnvio.getText());
            Integer tiempoPreparacionArticulo = Integer.parseInt(txtPreparacion.getText());
            if (datos.getArticuloByCodigo(codigo)==null) {
                success = datos.setArticulo(codigo, descripcionArticulo, pvpVentaArticulo
                        ,gastosEnvioArticulo,tiempoPreparacionArticulo);
            } else {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + " ya existe.");
            }
            if(success) {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + " se ha introducido correctamente.");
            }
        }
        else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un código.");
        }
        txtId.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtEnvio.setText("");
        txtPreparacion.setText("");
    }

    @FXML
    public void btnSalir(ActionEvent event) {
        Stage stage = (Stage) this.txtPrecio.getScene().getWindow();
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
            Stage myStage = (Stage) this.txtId.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

