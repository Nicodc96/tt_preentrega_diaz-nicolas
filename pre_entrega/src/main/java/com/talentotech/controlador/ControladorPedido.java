package com.talentotech.controlador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.talentotech.entidades.Pedido;
import com.talentotech.entidades.Producto;
import com.talentotech.menu.Menu;

public class ControladorPedido {
    private ControladorPedido() {
    }

    public static boolean manejarCrearPedido(Scanner scanner, List<Producto> listaProductos, List<Pedido> listaPedidos) {
        boolean salir = false;
        Pedido pedidoAGenerar = new Pedido();

        do {
            switch (Menu.menuCreacionPedido(scanner, pedidoAGenerar.getId())) {
                case 1:
                    try {
                        if (listaProductos.size() == 0){
                            System.out.println("No existen productos para agregar al pedido.");
                            break;
                        }
                        List<Producto> listaFiltrada = new ArrayList<>();
                        final int idIngresado;

                        for (Producto productoListaOriginal : listaProductos) {
                            if (!pedidoAGenerar.getListaProductos().contains(productoListaOriginal)) {
                                listaFiltrada.add(productoListaOriginal);
                            }
                        }

                        if (listaFiltrada.size() > 0) {
                            System.out.println("Productos disponibles:");
                            for (Producto productoFiltrado : listaFiltrada) {
                                System.out.printf("- ID: %d | Producto: %s\n", productoFiltrado.getId(),
                                        productoFiltrado.getNombre());
                            }
                        } else {
                            System.out.println("Todos los productos actuales se encuentran en el pedido.");
                            break;
                        }

                        System.out.print("Ingrese el ID del producto a agregar (0 para cancelar): ");
                        idIngresado = scanner.nextInt();

                        if (idIngresado == 0) break;                        
                        if (!listaProductos.stream().anyMatch(p -> p.getId() == idIngresado)) {
                            System.out.println("**ERROR**, no se ha encontrado el ID del producto solicitado.");
                            break;
                        }

                        pedidoAGenerar.agregarProducto(listaProductos.stream()
                                                                    .filter(p -> p.getId() == idIngresado)
                                                                    .toList()
                                                                    .get(0));
                        
                        System.out.println("Se ha agregado el producto al pedido correctamente.");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("**ERROR**, ¡Se debe ingresar un número como ID!");
                    }
                    break;
                case 2:
                    try {
                        final int idIngresado;
                        if (pedidoAGenerar.getListaProductos().size() == 0){
                            System.out.println("No hay productos dentro del pedido actual.");
                            break;
                        }
                        System.out.printf("""
                                Productos actualmente en el pedido:

                                %s

                                Escriba el ID del producto a eliminar:
                                """, pedidoAGenerar.mostrarPedidoId());
                        idIngresado = scanner.nextInt();

                        if (!pedidoAGenerar.getListaProductos().stream().anyMatch(p -> p.getId() == idIngresado)) {
                            System.out.println("**ERROR**, no se ha encontrado el ID del producto en el pedido.");
                            break;
                        }

                        pedidoAGenerar.getListaProductos().remove(pedidoAGenerar.getListaProductos()
                                                                                .stream()
                                                                                .filter(p -> p.getId() == idIngresado)
                                                                                .toList().get(0));
                        
                        System.out.println("Se ha quitado el producto del pedido correctamente.");
                    } catch (InputMismatchException e) {
                        System.out.println("**ERROR**, ¡Se debe ingresar un número como ID!");
                    } catch (NullPointerException e){
                        System.out.println("**ERROR**, no se ha encontrado el elemento a eliminar dentro de la lista.");
                    }
                    break;
                case 5:
                    if(pedidoAGenerar.getListaProductos().size() == 0){
                        System.out.println("No es posible generar un pedido sin productos.");
                        return false;
                    }
                    listaPedidos.add(pedidoAGenerar);
                    System.out.println("¡Pedido creado exitosamente!");
                    return true;
                case 9:
                    Pedido.idGenerado--;
                    System.out.println("Se ha cancelado la operación.");
                    return false;
            }
            Menu.enterParaContinuar(scanner);
        } while (!salir);
        return false;
    }

    public static void manejarMostrarPedidos(List<Pedido> listaDePedidos){
        if (listaDePedidos.size() == 0){
            System.out.println("¡No hay pedidos generados actualmente!");
            return;
        }
        System.out.println("------- Lista de pedidos actuales -------");
        for(Pedido pedido : listaDePedidos){
            System.out.println(pedido);
        }
    }
}
