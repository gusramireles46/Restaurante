package com.example.restaurante.componentes;

import com.example.restaurante.Restaurante;
import com.example.restaurante.modelo.DetalleTicketDAO;
import com.example.restaurante.vistas.TicketView;
import javafx.scene.control.*;

import java.util.Optional;

public class TicketButton extends TableCell<DetalleTicketDAO, String> {
    private Button btnEliminar;
    private DetalleTicketDAO detalleTicketDAO;
    private TableView<DetalleTicketDAO> tbvTicket;
    private TicketView ticketView;  // Agrega una referencia a TicketView

    public TicketButton(TicketView ticketView) {
        this.ticketView = ticketView;  // Inicializa la referencia a TicketView
        btnEliminar = new Button("Eliminar");
        btnEliminar.getStyleClass().addAll("btn", "btn-danger");
        btnEliminar.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmar eliminación");
            alert.setContentText("¿Seguro que desea eliminar este elemento?");
            Optional<ButtonType> opc = alert.showAndWait();
            if (opc.get() == ButtonType.OK) {
                tbvTicket = this.getTableView();
                detalleTicketDAO = tbvTicket.getItems().get(this.getIndex());
                detalleTicketDAO.eliminarProducto();
                tbvTicket.setItems(detalleTicketDAO.mostrarDetalles(Restaurante.id_ticket));
                tbvTicket.refresh();
                // Ahora puedes llamar al método actualizarTotalDesdeBoton de TicketView
                ticketView.actualizarTotalDesdeBoton();
            }
        });
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b)
            this.setGraphic(btnEliminar);
    }
}



