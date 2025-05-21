package com.talentotech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.talentotech.entidades.*;
import com.talentotech.menu.Menu;
import com.talentotech.controlador.*;

public class Main {
    private static List<Pedido> listaDePedidos = new ArrayList<>();
    private static List<Producto> listaDeProductosTotales = new ArrayList<>();
    public static void main(String[] args) {
        String respuesta = "no";
        Scanner entradaDeDatos = new Scanner(System.in);

        /* Descomentar esta línea para razones de testeo */
        ControladorProducto.hardcodearProductos(listaDeProductosTotales);

        do{
            switch (Menu.menuDeOpciones(entradaDeDatos)) {
                case 1 -> ControladorProducto.manejarAgregarProducto(entradaDeDatos, listaDeProductosTotales);
                case 2 -> ControladorProducto.manejarMostrarProductos(listaDeProductosTotales);
                case 3 -> ControladorProducto.manejarModificarProducto(listaDeProductosTotales, entradaDeDatos);
                case 4 -> ControladorProducto.manejarEliminarProducto(listaDeProductosTotales, entradaDeDatos);
                case 5 -> ControladorPedido.manejarCrearPedido(entradaDeDatos, listaDeProductosTotales, listaDePedidos);
                case 6 -> ControladorPedido.manejarMostrarPedidos(listaDePedidos);
                case 9 -> respuesta = Menu.salirDelPrograma();
            }
            Menu.enterParaContinuar(entradaDeDatos);
        }while(respuesta.equalsIgnoreCase("no"));

        entradaDeDatos.close();
    }
}