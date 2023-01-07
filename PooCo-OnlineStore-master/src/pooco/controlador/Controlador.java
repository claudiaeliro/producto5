package pooco.controlador;

import java.io.IOException;
import javafx.scene.control.*;
import pooco.modelo.Datos;
import pooco.vista.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controlador {
    private Datos datos;

    @FXML
    private Button añadirArticulo;
    @FXML
    private Button gestionArticulos;
    @FXML
    private Button gestionClientes;
    @FXML
    private Button gestionPedidos;


    public Controlador() {       
        datos = new Datos ();       
    }
    
    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {        
        this.datos = datos;
    }
    
    @FXML
    private void btnGestionArticulos(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorArticulos controlador=loader.getController();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

             stage.setOnCloseRequest(e -> controlador.closeWindowMenuGestionOS());
             //Lo utilizo para volver cuando cierre el formulario

             Stage myStage=(Stage) gestionArticulos.getScene().getWindow();
             myStage.close();

 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML        
    private void btnGestionClientes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuClienteVistaFX.fxml"));
              Pane ventana = (Pane) loader.load();
              ControladorClientes controlador=loader.getController();

              Scene scene = new Scene(ventana);
              Stage stage=new Stage();
              stage.setScene(scene);
              stage.show();

              stage.setOnCloseRequest(e -> controlador.closeWindowMenuGestionOS());
              //Lo utilizo para volver cuando cierre el formulario

              Stage myStage=(Stage) gestionClientes.getScene().getWindow();
              myStage.close();
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML    
    private void btnGestionPedidos(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuPedidoVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
            ControladorPedidos controlador=loader.getController();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuGestionOS());
              //Lo utilizo para volver cuando cierre el formulario
            Stage myStage=(Stage) gestionPedidos.getScene().getWindow();
            myStage.close();
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML    
    private void btnSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }



}
