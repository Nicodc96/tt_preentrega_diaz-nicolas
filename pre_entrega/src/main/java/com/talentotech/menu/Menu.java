package com.talentotech.menu;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Menu(){}

    public static int menuDeOpciones(Scanner scanner){
        try{
            System.out.print("""
                ***********************************************
                ************   MENU DE OPCIONES   *************
                ***********************************************
                ** Escriba el número de la operación deseada **
    
                1) Crear un producto
                2) Listar productos
                3) Buscar/actualizar un producto
                4) Eliminar un producto
                5) Crear un pedido
                6) Listar pedidos
    
                9) Salir
    
                Elija una opción: """);
            int opcionSeleccionada = scanner.nextInt();
            List<Integer> listaDeOpciones = Arrays.asList(1,2,3,4,5,6,9);
            while(!listaDeOpciones.contains(opcionSeleccionada)){
                System.out.print("""
                                    **ERROR**
                        Por favor, seleccione una opción correcta: 
                """);
                opcionSeleccionada = scanner.nextInt();
            }
            return opcionSeleccionada;
        } catch(InputMismatchException e){
            System.out.println("**ERROR**, ¡Se debe ingresar un número como opción!");
            return 0;
        }
    }

    public static int menuModificarProducto(Scanner scanner, String infoProductos){
        try{
            System.out.printf("""
                **********************************************************
                ************   ADMINISTRACIÓN DE PRODUCTOS   *************
                **********************************************************
                ******* Escriba el número de la operación deseada ********

                    ----- Productos actualmente registrados -----
                %s

                1) Mostrar información completa del producto
                2) Actualizar un producto
    
                9) Volver
    
                Elija una opción: """, infoProductos);
            
            int opcionSeleccionada = scanner.nextInt();
            List<Integer> listaDeOpciones = Arrays.asList(1,2,9);
            while(!listaDeOpciones.contains(opcionSeleccionada)){
                System.out.print("""
                                    **ERROR**
                        Por favor, seleccione una opción correcta: """);
                opcionSeleccionada = scanner.nextInt();
            }
            return opcionSeleccionada;
        } catch(InputMismatchException e){
            System.out.println("**ERROR**, ¡Se debe ingresar un número como opción!");
            return 0;
        }
    }

    public static int menuCreacionPedido(Scanner scanner, int idPedido){
        try{
            System.out.printf("""
                ********************************************************
                ************   ADMINISTRACIÓN DE PEDIDOS   *************
                ********************************************************
                ****** Escriba el número de la operación deseada *******    
                
                            -- PEDIDO GENERADO -- ID: %d --

                1) Agregar productos al pedido
                2) Quitar productos al pedido
                
                5) CONFIRMAR PEDIDO
    
                9) CANCELAR PEDIDO
    
                Elija una opción: """, idPedido);
            int opcionSeleccionada = scanner.nextInt();
            List<Integer> listaDeOpciones = Arrays.asList(1,2,5,9);
            while(!listaDeOpciones.contains(opcionSeleccionada)){
                System.out.print("""
                                    **ERROR**
                        Por favor, seleccione una opción correcta: 
                """);
                opcionSeleccionada = scanner.nextInt();
            }
            return opcionSeleccionada;
        } catch(InputMismatchException e){
            System.out.println("**ERROR**, ¡Se debe ingresar un número como opción!");
            return 0;
        }
    }

    public static void enterParaContinuar(Scanner scanner){ 
        System.out.println("Presione 'Enter' para continuar...");
        try{
            System.in.read();
            scanner.nextLine();
        }
        catch(Exception e){}  
    }

    public static String salirDelPrograma(){
        System.out.println("Saliendo del programa...");
        return "si";
    }
}
