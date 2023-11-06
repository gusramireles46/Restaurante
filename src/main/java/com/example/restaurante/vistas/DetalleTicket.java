package com.example.restaurante.vistas;

import com.example.restaurante.Restaurante;
import com.example.restaurante.componentes.TicketButton;
import com.example.restaurante.modelo.DetalleTicketDAO;
import com.example.restaurante.modelo.ProductosDAO;
import com.example.restaurante.modelo.TicketDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;

public class DetalleTicket extends Stage {
    private VBox vbox;
    private DetalleTicketDAO detalleTicketDAO;
    private ProductosDAO productosDAO;
    private Label lblTitle, lblTotal;
    private TableView<DetalleTicketDAO> tbvTicket;
    private Button btnFinalizarOrden;
    private Scene escena;

    // Constructor
    public DetalleTicket() {
        crearGUI();
        escena = new Scene(vbox, 640, 480);
        //escena.getStylesheets().addAll(/*BootstrapFX.bootstrapFXStylesheet(), */"/css/estilos_ticket.css");
        escena.getStylesheets().add(getClass().getResource("/css/estilos_ticket.css").toExternalForm());
        this.setTitle("Detalle del ticket " + Restaurante.id_ticket);
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    public void actualizarTotal() {
        double total = detalleTicketDAO.obtenerTotal(Restaurante.id_ticket);
        lblTotal.setText("TOTAL: $" + total);
    }

    private void crearGUI() {
        detalleTicketDAO = new DetalleTicketDAO();
        tbvTicket = new TableView<>();
        crearTabla();
        lblTitle = new Label("Detalle del ticket " + Restaurante.id_ticket);
        lblTitle.getStyleClass().add("titulo");
        btnFinalizarOrden = new Button("Finalizar orden");
        btnFinalizarOrden.getStyleClass().add("btnFinalizar");
        btnFinalizarOrden.setOnAction(e -> {
            if (!Restaurante.finalizarOrden) {
                Restaurante.finalizarOrden = true;
            }
            TicketDAO ticketDAO = new TicketDAO();
            ticketDAO.actualizarTicket(Restaurante.id_ticket, detalleTicketDAO.obtenerTotal(Restaurante.id_ticket));
            this.close();
        });
        lblTotal = new Label("TOTAL: $" + detalleTicketDAO.obtenerTotal(Restaurante.id_ticket));
        lblTotal.getStyleClass().add("total");
        vbox = new VBox(lblTitle, tbvTicket, lblTotal, btnFinalizarOrden);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
    }

    private void crearTabla() {
        TableColumn<DetalleTicketDAO, String> tbcProducto = nombreProducto();
        TableColumn<DetalleTicketDAO, Double> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
        TableColumn<DetalleTicketDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<DetalleTicketDAO, String>, TableCell<DetalleTicketDAO, String>>() {
            @Override
            public TableCell<DetalleTicketDAO, String> call(TableColumn<DetalleTicketDAO, String> detalleTicketDAOStringTableColumn) {
                return new TicketButton();
            }
        });
        tbvTicket.getColumns().addAll(tbcProducto, tbcPrecio, tbcEliminar);
        tbvTicket.setItems(detalleTicketDAO.mostrarDetalles(Restaurante.id_ticket));
    }

    private TableColumn<DetalleTicketDAO, String> nombreProducto() {
        TableColumn<DetalleTicketDAO, String> tbcNombreProducto = new TableColumn<>("Producto");
        tbcNombreProducto.setCellValueFactory(cellData -> {
            productosDAO = new ProductosDAO();
            int id_producto = cellData.getValue().getId_producto();
            ProductosDAO producto = productosDAO.getById(id_producto);
            return new SimpleStringProperty(producto.getNombre());
        });
        return tbcNombreProducto;
    }
}
