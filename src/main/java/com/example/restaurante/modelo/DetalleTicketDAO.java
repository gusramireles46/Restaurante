package com.example.restaurante.modelo;

import com.example.restaurante.Restaurante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleTicketDAO {

    private int id_detalle;
    private int id_ticket;
    private int id_producto;
    private double precio_unitario;
    private int total;

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void crearDetalleTicket(int id_ticket, int id_producto, double precio_unitario) {
        String sql = "INSERT INTO detalle_ticket (id_ticket, id_producto, precio_unitario) VALUES (?, ?, ?)";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            sentencia.setInt(1, id_ticket);
            sentencia.setInt(2, id_producto);
            sentencia.setDouble(3, precio_unitario);
            //sentencia.setInt(4, total);
            sentencia.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al crear el detalle del ticket");
        }
    }

    public void actualizarDetalleTicket() {
        String sql = "UPDATE detalle_ticket SET id_ticket = ?, id_producto = ?, precio_unitario = ? WHERE id_detalle = ?";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            sentencia.setInt(1, id_ticket);
            sentencia.setInt(2, id_producto);
            sentencia.setDouble(3, precio_unitario);
            sentencia.setInt(4, id_detalle);
            sentencia.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el detalle del ticket");
        }
    }

    public double obtenerTotal(int id_ticket) {
        String sql = "SELECT SUM(precio_unitario) AS total FROM detalle_ticket WHERE id_ticket = " + id_ticket;
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                total = res.getInt("total");
                Restaurante.total = total;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el total del ticket");
        }
        return Restaurante.total;
    }

    public ObservableList<DetalleTicketDAO> mostrarDetalles(int id_ticket) {
        ObservableList<DetalleTicketDAO> det = FXCollections.observableArrayList();
        DetalleTicketDAO detalle = null;
        String sql = "SELECT * FROM detalle_ticket WHERE id_ticket = " + id_ticket;
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                detalle = new DetalleTicketDAO();
                detalle.setId_detalle(res.getInt("id_detalle"));
                detalle.setId_ticket(res.getInt("id_ticket"));
                detalle.setId_producto(res.getInt("id_producto"));
                detalle.setPrecio_unitario(res.getDouble("precio_unitario"));
                det.add(detalle);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los detalles del ticket");
        }
        return det;
    }

    public void eliminarProducto() {
        String sql = "DELETE FROM detalle_ticket WHERE id_detalle = " + id_detalle;
        try {
            PreparedStatement stmt = Conexion.conexion.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
