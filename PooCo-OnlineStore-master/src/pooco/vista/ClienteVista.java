package pooco.vista;


import java.util.Scanner;

public class ClienteVista {
    Scanner teclado = new Scanner(System.in);

    public char menuPrincipal() {
        char opcion;
        
        System.out.println(); 
        System.out.println("1. Añadir Cliente");
        System.out.println("2. Mostrar Clientes");
        System.out.println("3. Mostrar Clientes Estándar");
        System.out.println("4. Mostrar Clientes Premium");
        System.out.println("0. Salir");
        opcion = pedirOpcion();
        return opcion;        
    }

    private char pedirOpcion() {
        String resp;
        System.out.print("Elige una opción (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void adCabecera(){
        System.out.println(); 
        System.out.println("===== Introducir Cliente =====");
        System.out.println();         
    }
    public void showCabecera(){
        System.out.println();
        System.out.println("===== Mostrar clientes =====");
        System.out.println();       
    }
    public void showCabeceraSTD(){
        System.out.println();
        System.out.println("===== Mostrar clientes estandar =====");
        System.out.println();          
    }
    public void showCabeceraPRM(){
        System.out.println(); 
        System.out.println("===== Mostar clientes premium =====");
        System.out.println();         
    }
    

    public String eMailCliente()
    {
        String email;
        System.out.print("eMail del cliente: ");
        email=teclado.nextLine();
        return email;
    }
    
    public void warning(String email, boolean exist){
        System.out.print("El cliente con eMail: " + email);
        if(exist) System.out.print(" ya existe.");
        else System.out.print(" no existe.");
        System.out.println(); 
    }

    public void introducido(boolean success) {
        if(success) System.out.println("Introducido correctamente en BBDD");
        else System.out.println("No se pudo guardar en BBDD");
    }
    
    public String nombreCliente(){
        String nombre;
        System.out.print("Nombre: ");
        nombre = teclado.nextLine();
        return nombre;
    }

    public String domicilioCliente(){
        String domicilio;
        System.out.print("Domicilio: ");
        domicilio = teclado.nextLine();
        return domicilio;
    }

    public String nifCliente(){
        String nif;
        do {
        System.out.print("NIF: ");
        nif = teclado.nextLine();
        if(!(nif.length()<=9)) {
            System.out.println("    Maximo 9 digitos!");
        }
        }  while (!(nif.length()<=9));            
        return nif;
    }

    public String tipoCliente(){
        String tipo=null;                
        do {
            System.out.print("(1)Estandard / (2)Premium: ");
            tipo = teclado.nextLine();
        }  while (!(tipo.equals("1")) && !(tipo.equals("2")));     
        return tipo;        
    }

    public void showClientes(String clientes){
        System.out.println(clientes);
    }
    



}
