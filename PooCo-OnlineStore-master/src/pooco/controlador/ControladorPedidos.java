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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pooco.modelo.Articulo;
import pooco.modelo.Cliente;
import pooco.modelo.Datos;
import pooco.modelo.Pedido;
import pooco.vista.OnlineStore;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;

public class ControladorPedidos  {
    private Datos datos;
 //   private Cliente cliente;
 //   private Articulo articulo;

    @FXML
    private Button añadirPedido;
    @FXML
    private Button eliminarPedido;
    @FXML
    private Button MostrarPedidosPendientesDeEnvio;
    @FXML
    private Button MostrarPedidosEnviados;

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
    private TextField txtNumeroPedidoBorrar;
    @FXML
    private TextField txteMailPendientesEnvioPedido;
    @FXML
    private TextField txteMailEnviadosPedido;

    @FXML
    private TextArea txtResult;
    @FXML
    private TextArea txtResultBorrar;
    @FXML
    private TextArea txtResultPendientes;
    @FXML
    private TextArea txtResultEnviados;


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
            ControladorPedidos controlador =loader.getController();
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
            myStage=(Stage) this.txtNumeroPedido.getScene().getWindow();
        }
        else if (ventana.trim().toUpperCase(Locale.ROOT)=="BORRAR")
        {
            myStage=(Stage) this.txtNumeroPedidoBorrar.getScene().getWindow();
        }
        else if (ventana.trim().toUpperCase()=="PENDIENTESENVIO")
        {
            myStage=(Stage) this.txteMailPendientesEnvioPedido.getScene().getWindow();
        }
        else if (ventana.trim().toUpperCase()=="ENVIADOS")
        {
            myStage=(Stage) this.txteMailEnviadosPedido.getScene().getWindow();
        }
        myStage.close();
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

    @FXML
    private void btnEliminarPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/BorrarPedidoVistaFX.fxml"));
            Parent root=loader.load();
            ControladorPedidos controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuPedidos("BORRAR"));
            Stage myStage=(Stage) eliminarPedido.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnMostrarPedidoPE(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/PendientesEnvioPedidoVistaFX.fxml"));
            Parent root=loader.load();
            ControladorPedidos controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuPedidos("PENDIENTESENVIO"));
            Stage myStage=(Stage) MostrarPedidosPendientesDeEnvio.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnMostrarPedidoEnv(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/EnviadosPedidoVistaFX.fxml"));
            Parent root=loader.load();
            ControladorPedidos controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindowMenuPedidos("ENVIADOS"));
            Stage myStage=(Stage) MostrarPedidosEnviados.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void btnSalir(ActionEvent event) {
       String id = ((Node) event.getSource()).getId();
        switch (id){
            case "volverAñadir":
                closeWindowMenuPedidos("AÑADIR");
                break;
            case "volverBorrarPedido":
                closeWindowMenuPedidos("BORRAR");
                break;
            case "volverPendientesEnvioPedido":
                closeWindowMenuPedidos("PENDIENTESENVIO");
                break;
            case "volverEnviadosPedido":
                closeWindowMenuPedidos("ENVIADOS");
                break;
            default:
                closeWindowMenuGestionOS();
        }
    }

    @FXML
    private void onEnter(ActionEvent event) {
        // Voy a capturar el cliente        
        if (datos.clienteByEmail(txteMailPedido.getText())!=null)
        {
            txtResult.setVisible(false);
            txtResult.setText("");            
            txtClientePedido.setText(datos.clienteByEmail(txteMailPedido.getText()).toString());

        } else
        {
            txtResult.setVisible(true);
            txtResult.setText("");
            txtResult.setText("Cliente no encontrado.");
     /*        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddClienteVistaFX.fxml"));
                Pane ventana = (Pane) loader.load();
                ControladorClientes controlador=loader.getController();
                Scene scene = new Scene(ventana);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();

                 stage.setOnCloseRequest(e -> controlador.closeWindowMenuClientes("ADDClientePedido"));
                //Lo utilizo para volver cuando cierre el formulario
                Stage myStage=(Stage) añadirCliente.getScene().getWindow();
                myStage.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } */
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
        boolean success=false;
        txtResult.setText("");
        txtResult.setVisible(true);
        if (cliente!=null && articulo!=null)
        {
            success=datos.setPedido(Integer.parseInt(txtNumeroPedido.getText()),articulo,
                    Integer.parseInt(txtNumeroPedido.getText()),cliente);
            if (success==true) {
                txtResult.setText("Pedido realizado!!!!");
                articulo.
                cliente=null;
                articulo=null;
            }
            else {
                txtResult.setText("Problemas al realizar el pedido." + "\n" +
                        "Revise el pedido.");
            }
        } else txtResult.setText("No se puede dar de alta un pedido sin un Cliente y/o Articulo.");
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

    }

    @FXML
    public void borrarPedido(ActionEvent event) {
        try
        {
            boolean eliminado=false;
            int numPedido=Integer.parseInt(txtNumeroPedidoBorrar.getText());
            if (datos.pedidoByNum(numPedido)==-1)
            {
                txtResultBorrar.setText("");
                txtResultBorrar.setVisible(true);
                txtResultBorrar.setText("No existe el pedido que quieres borrar.!!!!!");
                return;
            }
            if (muestroMensajeConfirmacion()==true)
            {
                eliminado=datos.eliminarPedido(numPedido);
            }
            txtResultBorrar.setText("");
            txtResultBorrar.setVisible(true);
            if (eliminado==true) {

                txtResultBorrar.setText("Pedido eliminado!!!!");
            }
            else {
                txtResultBorrar.setText("Problemas al borrar el pedido.");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private boolean muestroMensajeConfirmacion()
    {
       int respuesta= JOptionPane.showConfirmDialog(null, "Estás seguro que quieres borrar el Pedido",
                "BORRAR PEDIDO", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
       if( respuesta==0) return true;
       else return false;
    }
    private void  mensajePedidoNoExiste()
    {
        txtResultBorrar.setText("");
        txtResultBorrar.setVisible(true);
        txtResultBorrar.setText("No existe el pedido que quieres borrar.!!!!!");
    }
    private void mostrarPedido(String pedido)
    {
        txtResultBorrar.setText("");
        txtResultBorrar.setVisible(true);
        txtResultBorrar.setText(pedido);
    }
    @FXML
    private  void mostrarBorrar(ActionEvent event) {
        onComprobarPedido(event);
    }
    @FXML
    public void onComprobarPedido(ActionEvent event) {
        try
        {
            boolean eliminado=false;
            int numPedido=Integer.parseInt(txtNumeroPedidoBorrar.getText());
            if (datos.pedidoByNum(numPedido)==-1)
            {
                mensajePedidoNoExiste();
                return;
            }
            //Ahora lo que tenemos que ver que no esté enviado.
            //Ya que si lo está no lo podemos cancelar
            if(!datos.pedidoEnviado(datos.pedidoByNum(numPedido)))
            {
                String textoPedido=muestroPedidoBorrar(numPedido);
                mostrarPedido(textoPedido);
            } else
            {
                mostrarPedido("Pedido no existe o ya ha sido enviado.");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            mensajePedidoNoExiste();
        }
    }

    private String  muestroPedidoBorrar(Integer numeroPedido){
        List<Pedido> lista;
        Pedido pedido=null;
        lista=datos.getListaPedidos();
        for (Pedido pe:lista) {
            if (pe.getIdPedido()==numeroPedido)
            {
                pedido=pe;
                break;
            }
        }
        if (pedido!=null) return pedido.toString();
        else return "";
    }

    @FXML
    private void mostrarPendientesEnvio(ActionEvent event) {
        String email="";
        email=txteMailPendientesEnvioPedido.getText();
        if (email.trim()=="")
        {
            //Listo todos
            listarTodosPedidosPendientes();
        }
        else
        {
            //Listo por filtro de correo
            listarTodosPedidosPorFiltro(email);
        }
    }
    private void listarTodosPedidosPendientes()
    {
        List lista = datos.getListaPedidos();
        txtResultPendientes.setText("");
        txtResultPendientes.setVisible(true);
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                if(!datos.pedidoEnviado(item)){
                    txtResultPendientes.setText(txtResultPendientes.getText() + lista.get(item).toString()
                            + "---------------------------------------------------" + "\n");
                }
            }
        } else {
            txtResultPendientes.setText("No existe ningún pedido pendiente.");
        }
    }

    private void listarTodosPedidosPorFiltro(String email)
    {
        txtResultPendientes.setText("");
        txtResultPendientes.setVisible(true);
        if (datos.clienteByEmail(email)==null)
        {
            txtResultPendientes.setText("No existe ningún cliente dado de alta con el coreo " + email);
            return;
        }
        List lista = datos.getPendienteByCliente(email);
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                txtResultPendientes.setText(txtResultPendientes.getText() +lista.get(item).toString()
                        + "---------------------------------------------------" + "\n");
            }
        } else {
            txtResultPendientes.setText("No existe ningún pedido pendiente del cliente " + email);
        }
    }


    @FXML
    public void mostrarEnviadosPedido(ActionEvent event) {
        String email="";
        email=txteMailEnviadosPedido.getText();
        if (email.trim()=="")
        {
            //Listo todos
            listarTodosPedidosEnviados();
        }
        else
        {
            //Listo por filtro de correo
            listarTodosPedidosPorFiltroEnviados(email);
        }
    }
    private void listarTodosPedidosEnviados()
    {
        txtResultEnviados.setText("");
        txtResultEnviados.setVisible(true);
        List lista = datos.getListaPedidos();
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                if(datos.pedidoEnviado(item)){
                    txtResultEnviados.setText(txtResultEnviados.getText() + lista.get(item).toString()
                    + "---------------------------------------------------" + "\n");
                }
            }
        } else {
           txtResultEnviados.setText("No existe ningún pedido hecho.");
        }
    }

    private void listarTodosPedidosPorFiltroEnviados(String email)
    {
        txtResultEnviados.setText("");
        txtResultEnviados.setVisible(true);
        if (datos.clienteByEmail(email)==null)
        {
            txtResultEnviados.setText("No existe ningún cliente dado de alta con el coreo " + email);
            return;
        }
        List lista = datos.getEnviadosByCliente(email);
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                txtResultEnviados.setText(txtResultEnviados.getText() +lista.get(item).toString()
                        + "---------------------------------------------------" + "\n");
            }
        } else {
            txtResultEnviados.setText("No existe ningún pedido pendiente del cliente " + email);
        }

    }
}


