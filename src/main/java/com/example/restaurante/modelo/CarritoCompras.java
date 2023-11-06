package com.example.restaurante.modelo;

import java.util.ArrayList;

public class CarritoCompras {
    private ArrayList<ProductosDAO> productos;

    public CarritoCompras() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(ProductosDAO producto) {
        productos.add(producto);
    }

    public void eliminarProducto(ProductosDAO producto) {
        productos.remove(producto);
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public ArrayList<ProductosDAO> getProductos() {
        return productos;
    }

}
