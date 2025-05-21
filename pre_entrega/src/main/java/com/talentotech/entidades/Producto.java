package com.talentotech.entidades;

import java.util.Objects;

public class Producto {
    public static int idGenerado = 100;
    private int id;
    protected String nombre;
    protected double precio;
    protected String descripcion;

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setNombre(String nuevoNombre) {
        if (Objects.isNull(nuevoNombre) || nuevoNombre.length() == 0)
            return;
        this.nombre = nuevoNombre;
    }

    public void setPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0d)
            return;
        this.precio = nuevoPrecio;
    }

    public void setDescripcion(String nuevaDescripcion) {
        if (Objects.isNull(nuevaDescripcion) || nuevaDescripcion.length() == 0)
            return;
        this.descripcion = nuevaDescripcion;
    }

    public Producto(String nombre, double precio, String descripcion) {
        this.id = idGenerado++;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this == null || obj.getClass() != this.getClass())
            return false;

        Producto articuloAComparar = (Producto) obj;
        return this.getNombre().equalsIgnoreCase(articuloAComparar.getNombre()) || this.getId() == articuloAComparar.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.precio, this.descripcion);
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();

        sB.append("Nombre del producto: ").append(this.nombre).append(System.lineSeparator());
        sB.append("ID: ").append(this.id).append(System.lineSeparator());
        sB.append("Precio: $").append(String.format("%.2f", this.precio)).append(System.lineSeparator());
        sB.append("Descripción: ").append(this.descripcion).append(System.lineSeparator());

        return sB.toString();
    }
}
