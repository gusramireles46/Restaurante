package com.example.restaurante.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductosDAO {

    int id_producto;
    String nombre;
    double precio;
    int id_categoria;
    private byte[] imagenBytes;

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }

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

    public void insertarProducto() {
        String query = "INSERT INTO productos (nombre, precio, id_categoria, imagen_producto) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, id_categoria);
            stmt.setBytes(4, imagenBytes);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto() {
        String query = "UPDATE productos SET nombre = ?, precio = ?, id_categoria = ?, imagen_producto = ? WHERE id_producto = ?";
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, id_categoria);
            stmt.setBytes(4, imagenBytes);
            stmt.setInt(5, id_producto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProductoSinImagen() {
        String query = "UPDATE productos SET nombre = ?, precio = ?, id_categoria = ? WHERE id_producto = ?";
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, id_categoria);
            stmt.setInt(4, id_producto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto() {
        String query = "DELETE FROM productos WHERE id_producto = ?";
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(query);
            stmt.setInt(1, id_producto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductosDAO> listarProductos() {
        ObservableList<ProductosDAO> listaProductos = FXCollections.observableArrayList();
        ProductosDAO productosDAO = null;
        String query = "SELECT * FROM productos";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                productosDAO = new ProductosDAO();
                productosDAO.setId_producto(res.getInt("id_producto"));
                productosDAO.setNombre(res.getString("nombre"));
                productosDAO.setPrecio(res.getDouble("precio"));
                productosDAO.setId_categoria(res.getInt("id_categoria"));
                listaProductos.add(productosDAO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    public ObservableList<ProductosDAO> listarProductos(int id_cat) {
        ObservableList<ProductosDAO> listaProductos = FXCollections.observableArrayList();
        ProductosDAO productosDAO = null;
        String query = "SELECT * FROM productos WHERE id_categoria = ?";
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(query);
            stmt.setInt(1, id_cat);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                productosDAO = new ProductosDAO();
                productosDAO.setId_producto(res.getInt("id_producto"));
                productosDAO.setNombre(res.getString("nombre"));
                productosDAO.setPrecio(res.getDouble("precio"));
                productosDAO.setId_categoria(res.getInt("id_categoria"));
                productosDAO.setImagenBytes(res.getBytes("imagen_producto"));
                listaProductos.add(productosDAO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    public ProductosDAO getById(int id_prod) {
        ProductosDAO prod = new ProductosDAO();
        String sql = "SELECT nombre FROM productos WHERE id_producto = " + id_prod;
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                prod.setNombre(res.getString("nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prod;
    }

    public Image cargarImagenDesdeBytes() {
        if (imagenBytes != null) {
            ByteArrayInputStream stream = new ByteArrayInputStream(imagenBytes);
            return new Image(stream);
        }
        return null;
    }
}
