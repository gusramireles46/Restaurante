package com.example.restaurante.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketDAO {
    private static TicketDAO instance = new TicketDAO();
    private ObservableList<DetalleTicketDAO> detalles;

    private TicketDAO() {
        detalles = FXCollections.observableArrayList();
    }

    public static TicketDAO getInstance() {
        return instance;
    }

    public void agregarDetalle(DetalleTicketDAO detalle) {
        detalles.add(detalle);
    }

    public void eliminarDetalle(DetalleTicketDAO detalle) {
        detalles.remove(detalle);
    }

    public ObservableList<DetalleTicketDAO> getDetalles() {
        return detalles;
    }

    public void limpiarTicket() {
        detalles.clear();
    }
}
