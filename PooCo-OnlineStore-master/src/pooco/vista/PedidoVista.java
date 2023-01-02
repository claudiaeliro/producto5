package pooco.vista;

import java.util.Scanner;

public class PedidoVista {
    Scanner teclado = new Scanner(System.in);

    public char menuPrincipal() {
        char opcion;
        System.out.println();
        System.out.println("1. Añadir Pedido");
        System.out.println("2. Eliminar Pedido");
        System.out.println("3. Mostrar pedidos pendientes de envío");
        System.out.println("4. Mostrar pedidos enviados");
        System.out.println("0. Salir");
        opcion = pedirOpcion();           
        return opcion; 
    }

    public char menuMostrar() {
        char opcion;
        System.out.println();
        System.out.println("1. Mostrar Todos");
        System.out.println("2. Filtrar por Cliente");
        System.out.println("0. Salir");
        opcion = pedirOpcion2();           
        return opcion; 
    }
    
    
    char pedirOpcion() {
        String resp;
        System.out.print("Elige una opción (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);        
    }
    char pedirOpcion2() {
        String resp;
        System.out.print("Elige una opción (1,2 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);        
    }
    
    public void adCabecera(){
        System.out.println(); 
        System.out.println("===== Introducir Pedido =====");
        System.out.println(); 
    }
    public void delCabecera(){
        System.out.println(); 
        System.out.println("===== Eliminar Pedido =====");
        System.out.println(); 
    }
    public void showPdteCabecera(){
        System.out.println(); 
        System.out.println("===== Mostrando pedidos pendientes de envío =====");
        System.out.println(); 
    }
    public void showEnviosCabecera(){
        System.out.println(); 
        System.out.println("===== Mostrar pedidos enviados =====");
        System.out.println(); 
    }

    public int numPedido(){
        int numPedido;
        do{                     
            try {
                System.out.print("Numero de pedido: ");
                numPedido = teclado.nextInt();        
                return numPedido;
            } catch (Exception e){
                System.out.println("Debe introducir un numero entero.");
                teclado.nextLine();
            }
        } while(true);
    } 
    
    public void showNumPedido(int numero){
        System.out.print("Numero de pedido: ");
        System.out.println(numero);
    }

    public int cantidadPedido(){
        int cantidad;
        do{                       
            try {
                System.out.print("Cantidad: ");
                cantidad=teclado.nextInt();
                teclado.nextLine();
                return cantidad;  
            } catch (Exception e){
                System.out.println("Debe introducir un numero entero.");
                teclado.nextLine();
            }
        } while(true);     
    }




    public void showpvpVenta(float pvpVentaArticlulo, int cantidad){
        System.out.println("Pvp Venta Artculo: " + pvpVentaArticlulo);
        System.out.println("Total pedido: " + cantidad*pvpVentaArticlulo);
    }
    public void showGastosEnvio(float gasto, float descuento){
        System.out.println("Gastos de envio: " + gasto);
        System.out.println("Descuento envio: " + descuento + "%");
        System.out.println("Total gastos: " + gasto*((100f-descuento)/100f));
    }

    public void warning(int numPedido, boolean exist){
        System.out.print("El pedido " + numPedido);
        if (exist) System.out.print(" ya existe.");
        else System.out.print(" no existe");
        System.out.println(); 
        //teclado.nextLine();
    }    
    
    public void introducido(boolean success) {
        if(success) System.out.println("Introducido correctamente en BBDD");
        else System.out.println("No se pudo guardar en BBDD");
    }

    public void eliminaOk(int numPedido, boolean eliminado){
        if(eliminado) {
            System.out.print("El pedido " + numPedido + " se ha eliminado.");        
            System.out.println(); 
            teclado.nextLine();
        } else {
            System.out.print("El pedido " + numPedido + " ya se envio y no puede ser eliminado.");        
            System.out.println(); 
            teclado.nextLine();
        }
        
    }

    public void showPedido(String pedido){
        System.out.println(pedido);
    }
    
    public void nadaQmostrar(String tipoPedido) {
        System.out.println("No existen pedidos " + tipoPedido);
    }
   
}
