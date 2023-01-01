package pooco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pooco.controlador.articulos.ControladorMenuArticulo;
import pooco.controlador.cliente.ControladorMenuCliente;
import pooco.modelo.Datos;
import pooco.vista.ArticuloVista;
import pooco.vista.ClienteVista;
import pooco.vista.OnlineStore;
import pooco.vista.PedidoVista;

import java.io.IOException;
import java.util.List;


public class Controlador {
    private Datos datos;
    private ArticuloVista articuloView = new ArticuloVista(); 
    private ClienteVista clienteVista = new ClienteVista();  
    private PedidoVista pedidoVista = new PedidoVista();

    @FXML
    private Button botonGestionarArticulos;

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
             Parent root=loader.load();
             ControladorMenuArticulo controlador =loader.getController();
             Scene scene= new Scene(root);
             Stage stage= new Stage();
             stage.setScene(scene);
             stage.show();

             stage.setOnCloseRequest(e -> controlador.closeWindows());
             Stage myStage=(Stage) this.botonGestionarArticulos.getScene().getWindow();
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
            Parent root=loader.load();
            ControladorMenuCliente controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage myStage=(Stage) this.botonGestionarArticulos.getScene().getWindow();
            myStage.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML    
    private void btnGestionPedidos(ActionEvent event) {
        String tato="kk";
    }

    @FXML    
    private void btnSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void OnlineStore()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/GestionOS.fxml"));
            Parent root=loader.load();
           // ControladorMenuCliente controlador =loader.getController();
            Scene scene= new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



 
//    public void menuArticulo() {
//    char resultado;
//    boolean salir = false;
//    do {
//        resultado = articuloView.menuPrincipal();
//        switch (resultado) {
//            case '1':
//                añadirArticulo();
//                break;
//            case '2':                    
//                muestraArticulo();
//                break;
//            }
//            if (resultado == '0') salir = true;
//        } while (!salir);
//    }
    
    public void menuCliente() {
    char resultado;
    boolean salir = false;
    do {
        resultado = clienteVista.menuPrincipal();
        switch (resultado) {
            case '1':                    
                añadirCliente();
                break;
            case '2':
                muestraClientes();
                break;
            case '3':
                showClientesPorTipo("Estandard");
                break;
            case '4':
                showClientesPorTipo("Premium");
                break;

            }
            if (resultado == '0') salir = true;
        } while (!salir);
    }

    public void menuPedido(){
        char resultado;
        boolean salir = false;
        do {
            resultado = pedidoVista.menuPrincipal();
            switch (resultado) {
                case '1':
                    añadirPedido();
                    break;
                case '2':
                    eliminarPedido();
                    break;
                case '3':                    
                    pedidosPendientes();
                    break;
                case '4':
                    pedidosEnviados();
                    break;

                }
                if (resultado == '0') salir = true;
            } while (!salir);
    }  
    
    
    /* private void muestraArticulo() {
        String codigo;
        articuloView.showCabecera();
        codigo=articuloView.codigoArticulo();        
        if (datos.getArticuloByCodigo(codigo)!=null)
        {
            articuloView.showArticulo( datos.getArticuloByCodigo(codigo).toString());
        } else articuloView.warning(codigo,false);
    }*/

    public void añadirCliente() {
        boolean success=false;
        String eMail;
        clienteVista.adCabecera();
        eMail = clienteVista.eMailCliente();
        if(datos.clienteByEmail(eMail)==null) {
            success = datos.setCliente(eMail, clienteVista.nombreCliente(), clienteVista.domicilioCliente()
                ,clienteVista.nifCliente(), clienteVista.tipoCliente()); 
        } else {
            clienteVista.warning(eMail,true);
        }         
        clienteVista.introducido(success);             
    }

    public void añadirPedido()
    {          
        int numPedido;
        String eMail;
        String codigo;           
        float gastos;  
        float descuento=0f;
        int cantidad;
        boolean success;
         
        pedidoVista.adCabecera();
        numPedido = datos.getNumeroPedido();
        numPedido++;
        pedidoVista.showNumPedido(numPedido);
        eMail = clienteVista.eMailCliente();        
        if (datos.clienteByEmail(eMail)==null)
        {
            clienteVista.warning(eMail,false);
            añadirCliente();            
        } 
        codigo = articuloView.codigoArticulo();         
        if (datos.getArticuloByCodigo(codigo)==null)
        {
            articuloView.warning(codigo,false);
            return;
        } 
        gastos= datos.getArticuloByCodigo(codigo).getGastosEnvio();       
       if (datos.clienteTipoSTD(eMail)!=null ) {
            descuento= datos.clienteTipoSTD(eMail).getDescuento();
       }
       else if (datos.clienteTipoPRM(eMail)!=null ) {
           descuento= datos.clienteTipoPRM(eMail).getDescuento();
       }    
        
        cantidad =  pedidoVista.cantidadPedido();
        pedidoVista.showpvpVenta(datos.getArticuloByCodigo(codigo).getPvpVenta(), cantidad);        
        pedidoVista.showGastosEnvio(gastos, descuento);
        
        success = datos.setPedido(numPedido,datos.getArticuloByCodigo(codigo), cantidad, datos.clienteByEmail(eMail));
        pedidoVista.introducido(success);   
    }
    
    public void eliminarPedido(){
        int numPedido;  
        boolean eliminado=false;
        pedidoVista.delCabecera();
        numPedido = pedidoVista.numPedido();
        if (datos.pedidoByNum(numPedido)==-1)
        {
            pedidoVista.warning(numPedido,false);
            return;
        }         
        if(!datos.pedidoEnviado(datos.pedidoByNum(numPedido))){
            eliminado = datos.eliminarPedido(numPedido);                       
        } 
        pedidoVista.eliminaOk(numPedido,eliminado); 
    }

    private void muestraClientes() {        
        clienteVista.showCabecera();
        List lista = datos.getListaClientes();   
        if (lista!=null){
            for(int item=0; item<(lista.size()); item++) {
                clienteVista.showClientes(lista.get(item).toString());
            }
        }  
    }

    private void showClientesPorTipo(String tipo){
        if (tipo.equals("Estandard")) {
            clienteVista.showCabeceraSTD();  
            List lista = datos.getListaClientesSTD();   
            if (lista!=null){
                for(int item=0; item<(lista.size()); item++) {
                    clienteVista.showClientes(lista.get(item).toString());
                }
            }         
        }
        else {
            clienteVista.showCabeceraPRM();  
            List lista = datos.getListaClientesPRM();   
            if (lista!=null){
                for(int item=0; item<(lista.size()); item++) {
                    clienteVista.showClientes(lista.get(item).toString());
                }
            }         
        }
    }

    public void pedidosPendientes(){
        pedidoVista.showPdteCabecera();        
        char resultado;
        boolean salir = false;
        do {
            resultado = pedidoVista.menuMostrar();
            switch (resultado) {
                case '1':
                    allPedidosPdte();
                    break;
                case '2':
                    pedidoPendienteFiltro();
                    break;

                }
                if (resultado == '0') salir = true;
            } while (!salir);
        }

    public void pedidosEnviados(){
        pedidoVista.showEnviosCabecera();        
        char resultado;
        boolean salir = false;
        do {
            resultado = pedidoVista.menuMostrar();
            switch (resultado) {
                case '1':
                    allPedidosEnviados();
                    break;
                case '2':
                    pedidoEnviadoFiltro();
                    break;

                }
            if (resultado == '0') salir = true;
        } while (!salir);
    }

    public void allPedidosPdte(){
        pedidoVista.showPdteCabecera();
        List lista = datos.getListaPedidos();
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                if(!datos.pedidoEnviado(item)){
                    pedidoVista.showPedido(lista.get(item).toString()); 
                } 
            }
        } else {
            pedidoVista.nadaQmostrar("pendientes.");            
        }
        
    }
 
    public void pedidoPendienteFiltro(){
        String eMail;
        eMail = clienteVista.eMailCliente();
        if (datos.clienteByEmail(eMail)==null)
        {
            clienteVista.warning(eMail,false);
            return;
        } 
        List lista = datos.getPendienteByCliente(eMail); 
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){           
                pedidoVista.showPedido(lista.get(item).toString());            
            }  
        } else {
            pedidoVista.nadaQmostrar("pendientes.");            
        }                
     } 

    public void allPedidosEnviados(){
        pedidoVista.showEnviosCabecera();
        List lista = datos.getListaPedidos();
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){
                if(datos.pedidoEnviado(item)){
                    pedidoVista.showPedido(lista.get(item).toString());                
                } 
            }    
        } else {
            pedidoVista.nadaQmostrar("enviados.");            
        }  
        
    }

    public void pedidoEnviadoFiltro(){
        String eMail;
        eMail = clienteVista.eMailCliente();
        if (datos.clienteByEmail(eMail)==null)
        {
            clienteVista.warning(eMail,false);
            return;
        } 
        List lista = datos.getEnviadosByCliente(eMail);  
        if (lista.size()>0) {
            for(int item=0; item<(lista.size()); item++){           
                pedidoVista.showPedido(lista.get(item).toString());           
            }
        } else {
            pedidoVista.nadaQmostrar("enviados.");            
        }         
          
    }



}
