package pooco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pooco.modelo.Articulo;
import pooco.modelo.Cliente;
import pooco.modelo.Datos;
import pooco.vista.OnlineStore;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorPedidos  {
    private Datos datos;
    private Cliente cliente;
    private Articulo articulo;

    @FXML
    private Button añadirPedido;

    @FXML
    private TextField txtNumeroPedido;
    @FXML
    private TextField txtClientePedido;
    @FXML
    private TextField txteMailPedido;
    @FXML
    private TextField txtCodigoArtPedido;
    @FXML
    private TextField txtArticuloPedido;
    @FXML
    private TextField txtCantidadPedido;

    @FXML
    private TextArea txtResult;
    private ActionEvent event;


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
        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddPedidoVistaFX.fxml"));
            Parent root=loader.load();
            ControladorPedidos controlador =loader.getController();

            controlador.comenzarPedido();

            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);

            stage.show();



            stage.setOnCloseRequest(e -> controlador.closeWindowMenuPedidos("AÑADIR"));
            Stage myStage=(Stage) añadirPedido.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void comenzarPedido()
    {
        int numPedido = datos.getNumeroPedido();
        numPedido++;
        txtNumeroPedido.setText(Integer.toString(numPedido));
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

    @FXML
    private void onEnter(ActionEvent event) {
        // Voy a capturar el cliente
        cliente=datos.clienteByEmail(txteMailPedido.getText());
        if (cliente!=null)
        {
            txtResult.setVisible(false);
            txtResult.setText("");
            txtClientePedido.setText(cliente.toString());

        } else
        {
            txtResult.setVisible(true);
            txtResult.setText("");
            txtResult.setText("Cliente no encontrado.");
        }


    }
   @FXML
   private void onEnter2(ActionEvent event)
   {
       articulo=datos.getArticuloByCodigo(txtCodigoArtPedido.getText());
       if (articulo!=null){
           txtResult.setVisible(false);
           txtResult.setText("");
           txtArticuloPedido.setText(articulo.toString());
       }
       else
       {
           txtResult.setVisible(true);
           txtResult.setText("");
           txtResult.setText("Artículo no existe.");
       }










   }


    public void addPedido(ActionEvent event) {
    }

    public void addMostrar(ActionEvent event) {
        String textoPedido="";
        float descuento;
        float gastosEnvio;
        int cantidad;
        if (cliente!=null && articulo!=null)
        {

            gastosEnvio=articulo.getGastosEnvio();
            if (datos.clienteTipoSTD(cliente.getIdeMail())!=null)
            {
                descuento=datos.clienteTipoSTD(cliente.getIdeMail()).getDescuento();
            }
            else if (datos.clienteTipoPRM(cliente.getIdeMail())!=null) {
                descuento=datos.clienteTipoPRM(cliente.getIdeMail()).getDescuento();
            } else descuento=0;
            cantidad=Integer.parseInt(txtCantidadPedido.getText());
            gastosEnvio=articulo.getGastosEnvio();

            textoPedido="Número de Pedido: " + txtNumeroPedido.getText()+ "\n" +
                "Articulo :" + articulo.getDescripcion() + "\n" +
                "PVP: " + articulo.getPvpVenta() + "\n" +
                "Cliente: " + cliente.getIdeMail() + " " + cliente.getNombre() + "\n" +
                "Descuento: " + Float.toString(descuento) + "\n" +
                "Cantidad: " + Integer.toString(cantidad) + "\n" +
                "Gastos Envio: " + Float.toString(gastosEnvio);

            txtResult.setText("");
            txtResult.setVisible(true);
            txtResult.setText(textoPedido);
        }


//
//
//        success = datos.setPedido(numPedido,datos.getArticuloByCodigo(codigo), cantidad, datos.clienteByEmail(eMail));
//        pedidoVista.introducido(success);
    }
}
