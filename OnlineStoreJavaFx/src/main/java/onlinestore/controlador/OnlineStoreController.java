package onlinestore.controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OnlineStoreController {
    @FXML
    protected void botonGestionArticulos (ActionEvent event)
    {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/onlinestore/vista/ArticuloVista.fxml"));
            Parent root =loader.load();

            ArticuloController controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage().toString());
        }
    }


    @FXML
    protected void botonGestionClientes(ActionEvent event) {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/onlinestore/vista/ClientesVista.fxml"));
            Parent root =loader.load();

            ClientesController controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage().toString());
        }



    }

    @FXML
    protected void botonGestionPedidos(ActionEvent event) {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/onlinestore/vista/PedidosVista.fxml"));
            Parent root =loader.load();

            PedidosController controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage().toString());
        }
    }
}
