package com.talentotech.validaciones;

public class Validador {
    private Validador() {
    }

    public static boolean validarNombreProducto(String nombreAValidar) {
        return validar(nombreAValidar, 3, 30);
    }

    public static boolean validarPrecioProducto(double precioAValidar) {
        if (precioAValidar < 0d)
            return false;
        return true;
    }

    public static boolean validarDescripcionProducto(String descAValidar) {
        return validar(descAValidar, 0, 100);
    }

    private static boolean validar(String strAValidar, int minimo, int maximo) {
        if (!strAValidar.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\s]+$"))
            return false; // Si el string contiene SOLO números (no importa lo demás) devuelve false
        if (strAValidar.length() < minimo || strAValidar.length() > maximo)
            return false;
        return true;
    }
}
