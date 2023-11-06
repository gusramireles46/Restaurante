package com.example.restaurante.modelo;

import java.sql.*;

public class TicketDAO {
    private int id_ticket;
    private double total;

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void crearTicket() {
        String sql = "INSERT INTO ticket (fecha_creacion, total) VALUES (?, ?)";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            sentencia.setDouble(2, total);
            sentencia.executeUpdate();

            ResultSet generatedKeys = sentencia.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_ticket = generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID generado del ticket.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el ticket");
        }
    }


    public void actualizarTicket() {
        String sql = "UPDATE ticket SET total = ? WHERE id_ticket = ?";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            sentencia.setDouble(1, total);
            sentencia.setInt(2, id_ticket);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el ticket");
        }
    }

    public void actualizarTicket(int id_ticket, double total) {
        String sql = "UPDATE ticket SET total = ? WHERE id_ticket = ?";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            sentencia.setDouble(1, total);
            sentencia.setInt(2, id_ticket);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el ticket");
        }
    }

    public void eliminarTicket() {
        String sql = "DELETE FROM ticket WHERE id_ticket = ?";
        try {
            Conexion.createConnection();
            PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
            sentencia.setInt(1, id_ticket);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el ticket");
        }
    }


}
