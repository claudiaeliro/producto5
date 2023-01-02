package pooco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;
import java.util.Locale;

public class ControladorPedidos {
    private Datos datos;

    @FXML
    private Button añadirPedido;

    public ControladorPedidos() {
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
            Stage myStage=(Stage) this.añadirPedido.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeWindowMenuPedidos(String ventanaCerrar) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuPedidoVistaFX.fxml"));
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
      /*  Stage myStage = null;
        if (ventana.trim().toUpperCase()=="AÑADIR")
        {
            myStage=(Stage) this.btnAñadir.getScene().getWindow();
        }
        else if (ventana.trim().toUpperCase(Locale.ROOT)=="MOSTRAR")
        {
            myStage=(Stage) this.todos.getScene().getWindow();
        }
        myStage.close();*/
    }

    public void btnAddPedido(ActionEvent event) {
    }

    public void btnEliminarPedido(ActionEvent event) {
    }

    public void btnMostrarPedidoPE(ActionEvent event) {
    }

    public void btnMostrarPedidoEnv(ActionEvent event) {
    }

    public void btnSalir(ActionEvent event) {
       String id = ((Node) event.getSource()).getId();
        switch (id){
            case "volverEliminar":
                closeWindowMenuPedidos("ELIMINAR");
                break;
            case "volverAñadir":
                closeWindowMenuPedidos("AÑADIR");
                break;
            case "volverMostrarPendientesEnvio":
                closeWindowMenuPedidos("PENDIENTESENVIO");
                break;
            case "volverMostrarEnviados":
                closeWindowMenuPedidos("ENVIADOS");
                break;
            default:
                closeWindowMenuGestionOS();
        }
    }
}
