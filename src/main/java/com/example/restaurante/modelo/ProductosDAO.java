package com.example.restaurante.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductosDAO {

    int id_producto;
    String nombre;
    double precio;
    int id_categoria;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void insertarProducto() throws SQLException {
        String query = "INSERT INTO productos (nombre, precio, id_categoria) VALUES ('" + nombre + "', " + precio + ", " + id_categoria + ")";
        Statement stmt = Conexion.conexion.createStatement();
        stmt.executeUpdate(query);
    }

    public void actualizarProducto() throws SQLException {
        String query = "UPDATE productos SET nombre = '" + nombre + "', precio = " + precio + ", id_categoria = " + id_categoria + " WHERE id_producto = " + id_producto;
        Statement stmt = Conexion.conexion.createStatement();
        stmt.executeUpdate(query);
    }

    public void eliminarProducto() throws SQLException {
        String query = "DELETE FROM productos WHERE id_producto = " + id_producto;
        Statement stmt = Conexion.conexion.createStatement();
        stmt.executeUpdate(query);
    }

    public ObservableList<ProductosDAO> listarProductos() throws SQLException {
        ObservableList<ProductosDAO> listaProductos = FXCollections.observableArrayList();
        ProductosDAO productosDAO = null;
        String query = "SELECT * FROM productos";
        Statement stmt = Conexion.conexion.createStatement();
        ResultSet res = stmt.executeQuery(query);
        while (res.next()) {
            productosDAO = new ProductosDAO();
            productosDAO.setId_producto(res.getInt("id_producto"));
            productosDAO.setNombre(res.getString("nombre"));
            productosDAO.setPrecio(res.getDouble("precio"));
            listaProductos.add(productosDAO);
        }
        return listaProductos;
    }
}
