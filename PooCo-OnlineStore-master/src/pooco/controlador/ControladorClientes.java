package pooco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ControladorClientes {
    private Datos datos;
    
    @FXML
    private Button añadirCliente;
    @FXML
    private Button mostrarCliente;

    @FXML
    private Button btnAñadir;
    @FXML
    private Button todos;

    @FXML
    private TextField txteMail;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtNif;
    @FXML
    private RadioButton rdEstandar;
    @FXML
    private RadioButton rdPremium;
    @FXML
    private ListView listViewClientes;
    @FXML
    private TextArea txtResult;


    public ControladorClientes() {
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
            Stage myStage=(Stage) this.añadirCliente.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeWindowMenuClientes(String ventanaCerrar) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuClienteVistaFX.fxml"));
            Parent root=loader.load();
            ControladorClientes controlador =loader.getController();
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
            myStage=(Stage) this.todos.getScene().getWindow();
        }
        myStage.close();
    }

    @FXML
    private void btnAddCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddClienteVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorClientes controlador=loader.getController();
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuClientes("AÑADIR"));
            //Lo utilizo para volver cuando cierre el formulario
            Stage myStage=(Stage) añadirCliente.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnShowCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MostrarClientesVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorClientes controlador=loader.getController();
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuClientes("MOSTRAR"));
            //Lo utilizo para volver cuando cierre el formulario
            Stage myStage=(Stage) mostrarCliente.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void btnSalir(ActionEvent event) {
        String id = ((Node) event.getSource()).getId();
        switch (id){
            case "volverMostrar":
                closeWindowMenuClientes("MOSTRAR");
                break;
            case "volverAñadir":
                closeWindowMenuClientes("AÑADIR");
                break;
            default:
                closeWindowMenuGestionOS();
        }

    }

    @FXML
    private  void btnShowTodos(ActionEvent event) {
        listViewClientes.getItems().clear();
        List lista = datos.getListaClientes();
        if (lista!=null){
            for(int item=0; item<(lista.size()); item++) {
                listViewClientes.getItems().add(lista.get(item).toString());
            }
        }


    }

    @FXML
    private void btnShowEstandar(ActionEvent event) {
        listViewClientes.getItems().clear();
        List lista = datos.getListaClientesSTD();
        if (lista!=null){
            for(int item=0; item<(lista.size()); item++) {
                listViewClientes.getItems().add(lista.get(item).toString());
            }
        }
    }

    @FXML
    private void btnShowPremium(ActionEvent event) {
        listViewClientes.getItems().clear();
        List lista = datos.getListaClientesPRM();
        if (lista!=null){
            for(int item=0; item<(lista.size()); item++) {
                listViewClientes.getItems().add(lista.get(item).toString());
            }
        }
    }

    @FXML
    public void addCliente(ActionEvent event) {
        boolean success=false;
        String codigo = txteMail.getText();
        String tipoCliente;
        if (codigo!="") {
            String nombreCliente = txtNombre.getText();
            String domicilioCliente = txtDomicilio.getText();
            String nifCliente = txtNif.getText();
            if (rdEstandar.isSelected()) {
                tipoCliente = "1";
            } else tipoCliente = "2";

            if (datos.clienteByEmail(codigo)==null) {
                success = datos.setCliente(codigo, nombreCliente, domicilioCliente, nifCliente, tipoCliente);
            } else {
                txtResult.setVisible(true);
                txtResult.setText("El Email " + codigo + "\nya existe.");
            }
            if(success) {
                txtResult.setVisible(true);
                txtResult.setText("El Email " + codigo + "\nse ha introducido \ncorrectamente.");
            }
        }
        else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un email.");
        }
        txteMail.setText("");
        txtNombre.setText("");
        txtDomicilio.setText("");
        txtNif.setText("");
    }

    @FXML
    public void cambiaTipo(ActionEvent event) {
        if (rdEstandar.isSelected()) {
            rdEstandar.setSelected(false);
            rdPremium.setSelected(true);
        } else {
            rdEstandar.setSelected(true);
            rdPremium.setSelected(false);
        }
    }



}
