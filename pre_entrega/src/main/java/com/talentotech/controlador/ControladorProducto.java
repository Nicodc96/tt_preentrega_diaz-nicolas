package com.talentotech.controlador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.talentotech.entidades.Producto;
import com.talentotech.menu.Menu;
import com.talentotech.validaciones.Validador;

public class ControladorProducto {
    private ControladorProducto() {
    }

    public static void manejarAgregarProducto(Scanner scanner, List<Producto> listaProductos) {
        try {
            String nombreProducto;
            double precioProducto;
            String descripcionProducto;
            String datoIngresado = "";

            System.out.print("Ingrese el nombre del producto a registrar (o -1 para cancelar operación): ");
            scanner.nextLine(); // Limpiar buffer
            datoIngresado = scanner.nextLine();

            if (evaluarCancelarOperacion(datoIngresado)) return;

            while (!Validador.validarNombreProducto(datoIngresado)) {
                if (evaluarCancelarOperacion(datoIngresado)) return;
                    
                System.out.print("""
                        ¡Se ha ingresado un nombre incorrecto!

                        - El nombre debe tener mínimo 3 caracteres y máximo 30.
                        - El nombre no debe tener únicamente números.

                        Reingrese: """);
                datoIngresado = scanner.nextLine();
            }
            nombreProducto = datoIngresado.substring(0, 1).toUpperCase() + datoIngresado.substring(1).toLowerCase();

            System.out.print("Ingrese el precio del producto a registrar (o -1 para cancelar operación): ");
            datoIngresado = scanner.nextLine();
            if (evaluarCancelarOperacion(datoIngresado)) return;
            while (!Validador.validarPrecioProducto(Double.parseDouble(datoIngresado))) {
                if (evaluarCancelarOperacion(datoIngresado)) return;

                System.out.print("""
                        ¡Se ha ingresado un precio incorrecto!

                        - El precio debe ser un número.
                        - El precio no debe ser negativo.

                        Reingrese: """);
                datoIngresado = scanner.nextLine();
            }
            precioProducto = Double.parseDouble(datoIngresado);

            System.out.print("Ingrese una descripción del producto a registrar (o -1 para cancelar operación): ");
            datoIngresado = scanner.nextLine();
            if (evaluarCancelarOperacion(datoIngresado)) return;

            while (!Validador.validarDescripcionProducto(datoIngresado)) {
                if (evaluarCancelarOperacion(datoIngresado)) return;

                System.out.println("""
                        ¡Se ha ingresado una descripción incorrecta!

                        - La descripción debe tener como máximo 100 caracteres.

                        Reingrese: """);
                datoIngresado = scanner.nextLine();
            }
            descripcionProducto = datoIngresado.substring(0, 1).toUpperCase()
                    + datoIngresado.substring(1).toLowerCase();

            System.out.printf("""
                    ¿Confirma ingresar el siguiente producto?

                    Nombre: %s
                    Precio: $%.2f
                    Descripción: %s

                    Ingrese "si" para confirmar o "no" para cancelar: """
                    , nombreProducto, precioProducto, descripcionProducto);
            datoIngresado = scanner.nextLine();
            while (!datoIngresado.equalsIgnoreCase("no") && !datoIngresado.equalsIgnoreCase("si")) {
                System.out.print("Por favor, ingrese 'si' para confirmar o 'no' para cancelar: ");
                datoIngresado = scanner.nextLine();
            }

            if (evaluarCancelarOperacion(datoIngresado)) return;

            Producto nuevoProducto = new Producto(nombreProducto, precioProducto, descripcionProducto);
            listaProductos.add(nuevoProducto);
            System.out.println("¡Producto creado exitosamente!");
        } catch (InputMismatchException e) {
            System.out.println("**ERROR**, se ha ingresado incorrectamente el dato solicitado.");
        } catch (NumberFormatException e) {
            System.out.println("**ERROR**, el precio debe ser un número. Operación cancelada.");
        }
    }

    public static void manejarMostrarProductos(List<Producto> listaDeProductos) {
        if (listaDeProductos.size() == 0) {
            System.out.println("¡No hay productos registrados actualmente!");
            return;
        }

        System.out.println("---- Lista de productos actualmente ----\n");
        for (Producto producto : listaDeProductos) {
            System.out.println(producto);
        }
    }

    public static void manejarModificarProducto(List<Producto> listaDeProductos, Scanner scanner) {
        if (listaDeProductos.size() == 0) {
            System.out.println("¡No hay productos registrados para modificar/buscar!");
            return;
        }

        int respuesta = -1;
        StringBuilder sBInfoProductos = new StringBuilder();
        for (Producto producto : listaDeProductos) {
            sBInfoProductos
                    .append(String.format("- Nombre: %s | ID: %d", producto.getNombre(), producto.getId()))
                    .append(System.lineSeparator());
        }

        do {
            switch (Menu.menuModificarProducto(scanner, sBInfoProductos.toString())) {
                case 1:
                    try {
                        Producto productoAMostrar = buscarProducto(scanner, listaDeProductos, "buscar");

                        if (Objects.isNull(productoAMostrar)) break;

                        System.out.println("\n" + productoAMostrar);
                    } catch (InputMismatchException e) {
                        System.out.println("**ERROR**, ¡El ID del producto debe ser un número!");
                    }
                    break;
                case 2:
                    try {
                        Producto productoAModificar = buscarProducto(scanner, listaDeProductos, "modificar");

                        if (Objects.isNull(productoAModificar)) break;

                        String nuevoNombre = "";
                        double nuevoPrecio = 0;
                        String nuevaDescripcion = "";
                        String datoIngresado = "";
                        System.out.print(
                                "Ingrese el nuevo nombre (ingrese -1 para cancelar o -2 para mantener el nombre): ");
                        scanner.nextLine();
                        datoIngresado = scanner.nextLine();

                        if (evaluarCancelarOperacion(datoIngresado)) break;

                        while (!Validador.validarNombreProducto(datoIngresado) && !datoIngresado.equals("-2")) {
                            if (evaluarCancelarOperacion(datoIngresado)) break;

                            System.out.print("""
                                    ¡Se ha ingresado un nombre incorrecto!

                                    - El nombre debe tener mínimo 3 caracteres y máximo 30.
                                    - El nombre no debe tener únicamente números.

                                    Reingrese: """);
                            datoIngresado = scanner.nextLine();
                        }

                        nuevoNombre = datoIngresado.equals("-2") 
                                        ? productoAModificar.getNombre() 
                                        : formatearNombreDescripcion(datoIngresado);

                        System.out.print("Ingrese el nuevo precio (ingrese -1 para cancelar o -2 para mantener el precio): ");
                        datoIngresado = scanner.nextLine();

                        if (evaluarCancelarOperacion(datoIngresado)) break;

                        while (!Validador.validarPrecioProducto(Double.parseDouble(datoIngresado)) && !datoIngresado.equals("-2")) {
                            if (evaluarCancelarOperacion(datoIngresado)) break;

                            System.out.print("""
                                    ¡Se ha ingresado un precio incorrecto!

                                    - El precio debe ser un número.
                                    - El precio no debe ser negativo.

                                    Reingrese: """);
                            datoIngresado = scanner.nextLine();
                        }
                        nuevoPrecio = datoIngresado.equals("-2") 
                                        ? productoAModificar.getPrecio() 
                                        : Double.parseDouble(datoIngresado);

                        System.out.println("Ingrese la nueva descripción (ingrese -1 para cancelar o -2 para mantener la descripción): ");
                        datoIngresado = scanner.nextLine();

                        if (evaluarCancelarOperacion(datoIngresado)) break;

                        while (!Validador.validarDescripcionProducto(datoIngresado) && !datoIngresado.equals("-2")) {
                            if (evaluarCancelarOperacion(datoIngresado)) break;

                            System.out.println("""
                                    ¡Se ha ingresado una descripción incorrecta!

                                    - La descripción debe tener como máximo 100 caracteres.

                                    Reingrese: """);
                            datoIngresado = scanner.nextLine();
                        }

                        nuevaDescripcion = datoIngresado.equals("-2")
                                            ? productoAModificar.getDescripcion()
                                            : formatearNombreDescripcion(datoIngresado);

                        System.out.printf("""
                                ¿Confirma los cambios para el producto?

                                Anterior nombre: %s | Nuevo nombre: %s
                                Precio anterior: $%.2f | Nuevo precio: $%.2f
                                Descripción anterior: %s | Nueva descripción: %s

                                Ingrese "si" para confirmar o "no" para cancelar: """, productoAModificar.getNombre(),
                                nuevoNombre,
                                productoAModificar.getPrecio(),
                                nuevoPrecio,
                                productoAModificar.getDescripcion(),
                                nuevaDescripcion);
                        datoIngresado = scanner.nextLine();

                        while (!datoIngresado.equalsIgnoreCase("no") && !datoIngresado.equalsIgnoreCase("si")) {
                            System.out.print("Por favor, ingrese 'si' para confirmar o 'no' para cancelar: ");
                            datoIngresado = scanner.nextLine();
                        }

                        if (evaluarCancelarOperacion(datoIngresado)) return;

                        productoAModificar.setNombre(nuevoNombre);
                        productoAModificar.setPrecio(nuevoPrecio);
                        productoAModificar.setDescripcion(nuevaDescripcion);
                        System.out.println("¡Producto modificado exitosamente!");
                    } catch (InputMismatchException e) {
                        System.out.println("**ERROR**, ¡El ID del producto debe ser un número!");
                    } catch (NumberFormatException e) {
                        System.out.println("**ERROR**, ¡El precio ingresado debe ser un número!");
                    }
                    break;
                case 9:
                    return;
            }
            Menu.enterParaContinuar(scanner);
        } while (respuesta == -1);
    }

    public static void manejarEliminarProducto(List<Producto> listaDeProductos, Scanner scanner) {
        try {
            if (listaDeProductos.size() == 0) {
                System.out.println("No hay productos actualmente para eliminar.");
                return;
            }

            Producto productoAEliminar = buscarProducto(scanner, listaDeProductos, "eliminar");            
            if (Objects.isNull(productoAEliminar)) return;
            StringBuilder sBInfoProductos = new StringBuilder();

            for (Producto producto : listaDeProductos) { // Genero un string con una breve info de cada producto
                sBInfoProductos
                    .append(String.format("- Nombre: %s | ID: %d", producto.getNombre(), producto.getId()))
                    .append(System.lineSeparator());
            }

            System.out.println(sBInfoProductos.toString());

            String datoIngresado = "";
            System.out.printf("""
                    ¿Confirma eliminar el siguiente producto?

                    %s

                    Ingrese "si" para confirmar o "no" para cancelar la operación: """, productoAEliminar.toString());
            scanner.nextLine();
            datoIngresado = scanner.nextLine();

            while (!datoIngresado.equalsIgnoreCase("no") && !datoIngresado.equalsIgnoreCase("si")) {
                System.out.print("Por favor, ingrese 'si' para confirmar o 'no' para cancelar: ");
                datoIngresado = scanner.nextLine();
            }

            if (evaluarCancelarOperacion(datoIngresado)) return;
            
            listaDeProductos.remove(productoAEliminar);
            System.out.println("Producto eliminado correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("**ERROR**, ¡El ID del producto debe ser un número!");
        }
    }

    private static String formatearNombreDescripcion(String datoEntrada){
        return datoEntrada.substring(0, 1).toLowerCase() + datoEntrada.substring(1).toLowerCase();
    }

    private static Producto buscarProducto(Scanner scanner, List<Producto> listaProductos, String operacion) throws InputMismatchException {
        System.out.printf("Ingrese el ID del producto a %s: ", operacion);
        int idIngresado = scanner.nextInt();

        if (!listaProductos.stream().anyMatch(p -> p.getId() == idIngresado)) {
            System.out.println("¡No se ha encontrado ningún producto con el ID ingresado!");
            return null;
        }
        return listaProductos.stream().filter(p -> p.getId() == idIngresado).collect(Collectors.toList()).get(0);
    }

    private static boolean evaluarCancelarOperacion(String datoEntrada){
        if (datoEntrada.equals("-1") || datoEntrada.equals("no")){
            System.out.println("Se ha cancelado la operación");
            return true;
        }
        return false;
    }
}