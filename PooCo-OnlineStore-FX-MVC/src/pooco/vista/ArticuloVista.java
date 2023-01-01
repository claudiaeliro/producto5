package pooco.vista;

import java.util.Scanner;

public class ArticuloVista {
    Scanner teclado = new Scanner(System.in);

    public char menuPrincipal() {        
        char opcion;            
            System.out.println(); 
            System.out.println("1. Añadir Articulo");
            System.out.println("2. Mostrar Articulo");            
            System.out.println("0. Salir");
            opcion = pedirOpcion();
        return opcion;        
    }

    private char pedirOpcion() {
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
        System.out.println("===== Introducir Artículo =====");
        System.out.println();         
    }

    public void warning(String codigo, boolean exist){        
        System.out.print("El articulo " + codigo);
        if (exist) System.out.print(" ya existe.");
        else System.out.print(" no existe");
        System.out.println(); 
    }

    public void introducido(boolean success) {
        if(success) System.out.println("Introducido correctamente en BBDD");
        else System.out.println("No se pudo guardar en BBDD");
    }

    public String codigoArticulo()
    {        
        String codigo;
        System.out.print("Codigo del Arículo: ");
        codigo= teclado.nextLine();        
        return codigo;
    }
    
    public String descripcionArticulo()
    {        
        String descripcion;
        System.out.print("Descripcion: "); 
        descripcion= teclado.nextLine();        
        return descripcion;
    }

    public float pvpVentaArticulo()
    {
        float pvp=0;
        do{                     
            try {
                System.out.print("Pvp de venta: ");
                pvp= teclado.nextFloat();               
                return pvp;         
            } catch (Exception e){
                System.out.println("Debe introducir un numero.");
                teclado.nextLine();
            }
        } while(true);
    }

    public float gastosEnvioArticulo()
    {
        float gastosEnvio;
        do{                    
            try {
                System.out.print("Gastos de envio: ");
                gastosEnvio=teclado.nextFloat();               
                return gastosEnvio;
            } catch (Exception e){
                System.out.println("Debe introducir un numero.");
               teclado.nextLine();
            }
        } while(true);
    }

    public int tiempoPreparacionArticulo()
    {
        int tiempoPreparacion;
        do{                       
            try {
                System.out.print("Tiempo preparacion (min): ");
                tiempoPreparacion=teclado.nextInt();  
                teclado.nextLine();
                return tiempoPreparacion;
            } catch (Exception e){
                System.out.println("Debe introducir un numero entero.");
                teclado.nextLine();              
            }
        } while(true);
    }

    public void showCabecera(){
        System.out.println(); 
        System.out.println("===== Mostar Artículo =====");
        System.out.println();         
    }

    public void showArticulo(String articulo){
        System.out.println(articulo);
    }


}
