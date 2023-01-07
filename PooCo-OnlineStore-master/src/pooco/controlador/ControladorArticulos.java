package pooco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ControladorArticulos {
    private Datos datos;

    @FXML
    private Button añadirArticulo;
    @FXML
    private Button mostrarArticulo;


    @FXML
    private Button btnMostrarArticulo;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnMostrar;

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
    private TextArea txtResult;

    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblPvpVenta;
    @FXML
    private Label lblGastoEnvio;
    @FXML
    private Label lblTiempoPrep;

    public ControladorArticulos() {
        datos = new Datos ();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }


    public void closeWindowMenuGestionOS() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/GestionOS.fxml"));
            Parent root=loader.load();
            Controlador controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            //Lo utilizo para volver cuando cierre el formulario
            Stage myStage=(Stage) this.añadirArticulo.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void closeWindowMenuArticulos(String ventanaCerrar) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuArticuloVistaFX.fxml"));
            Parent root=loader.load();
            ControladorArticulos controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuGestionOS());

            cerrarVentana(ventanaCerrar);



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    private void cerrarVentana(String ventana)
    {
        Stage myStage = null;
        if (ventana.trim().toUpperCase()=="AÑADIR")
        {
          myStage=(Stage) this.btnAñadir.getScene().getWindow();
        }
        else if (ventana.trim().toUpperCase(Locale.ROOT)=="MOSTRAR")
        {
            myStage=(Stage) this.btnMostrar.getScene().getWindow();
        }
        myStage.close();
    }

    @FXML
    private void btnAddArticulo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorArticulos controlador=loader.getController();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuArticulos("AÑADIR"));
            //Lo utilizo para volver cuando cierre el formulario

            Stage myStage=(Stage) añadirArticulo.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }

    @FXML
    private void btnMostrarArticulo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MostrarArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorArticulos controlador=loader.getController();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuArticulos("MOSTRAR"));
            //Lo utilizo para volver cuando cierre el formulario

            Stage myStage=(Stage) mostrarArticulo.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void btnSalir(ActionEvent event) {
        String id = ((Node) event.getSource()).getId();
        switch (id){
            case "volverMostrar":
                closeWindowMenuArticulos("MOSTRAR");
                break;
            case "volverAñadir":
                closeWindowMenuArticulos("AÑADIR");
                break;
            default:
                closeWindowMenuGestionOS();
        }
    }

    @FXML
    private void addArticulo(ActionEvent event) {
        boolean success=false;
        String codigo;
        String descripcionArticulo;
        Float pvpVentaArticulo;
        Float gastosEnvioArticulo;
        Integer tiempoPreparacionArticulo;

        codigo = txtId.getText();
        if (codigo!="") {
            descripcionArticulo = txtDescripcion.getText();
            if (txtPrecio.getText()=="") pvpVentaArticulo=0f;
            else pvpVentaArticulo = Float.parseFloat(txtPrecio.getText());
            if (txtEnvio.getText()=="") gastosEnvioArticulo=0f;
            else gastosEnvioArticulo = Float.parseFloat(txtEnvio.getText());
            if (txtPreparacion.getText()=="") tiempoPreparacionArticulo=0;
            else tiempoPreparacionArticulo = Integer.parseInt(txtPreparacion.getText());
            if (datos.getArticuloByCodigo(codigo)==null) {
                success = datos.setArticulo(codigo, descripcionArticulo, pvpVentaArticulo
                        ,gastosEnvioArticulo,tiempoPreparacionArticulo);
            } else {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + "\n" + "ya existe.");
            }
            if(success) {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + "\n" + "se ha introducido \ncorrectamente.");
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
    private  void mostrarArticulo(ActionEvent event) {
        String codigo = txtId.getText();
        if (codigo!="") {
            if (datos.getArticuloByCodigo(codigo) != null) {
                txtResult.setVisible(false);
                txtDescripcion.setText(datos.getArticuloByCodigo(codigo).getDescripcion());
                txtPrecio.setText(datos.getArticuloByCodigo(codigo).getPvpVenta().toString());
                txtEnvio.setText(datos.getArticuloByCodigo(codigo).getGastosEnvio().toString());
                txtPreparacion.setText(datos.getArticuloByCodigo(codigo).getTiempoPreparacion().toString());
                lblDescripcion.setVisible(true);
                txtDescripcion.setVisible(true);
                lblPvpVenta.setVisible(true);
                txtPrecio.setVisible(true);
                lblGastoEnvio.setVisible(true);
                txtEnvio.setVisible(true);
                lblTiempoPrep.setVisible(true);
                txtPreparacion.setVisible(true);

            } else {
                lblDescripcion.setVisible(false);
                txtDescripcion.setVisible(false);
                lblPvpVenta.setVisible(false);
                txtPrecio.setVisible(false);
                lblGastoEnvio.setVisible(false);
                txtEnvio.setVisible(false);
                lblTiempoPrep.setVisible(false);
                txtPreparacion.setVisible(false);
                txtResult.setVisible(true);
                txtResult.setText("No existe un articulo con este código");
            }
        } else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un código.");
        }


    }

}
