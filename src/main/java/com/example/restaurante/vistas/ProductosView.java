package com.example.restaurante.vistas;

import com.example.restaurante.Restaurante;
import com.example.restaurante.modelo.DetalleTicketDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.ByteArrayInputStream;

public class ProductosView extends BorderPane {
    private GridPane gdpPrincipal;
    private VBox vProducto;
    private int id_categoria;
    private String categoria;
    private int id_ticket = Restaurante.id_ticket;
    private TicketView ticketView;

    public ProductosView(TicketView ticketView) {
        this.ticketView = ticketView;
        crearGUI();
        this.setCenter(gdpPrincipal);
    }

    public void actualizarProductos(int id, String nombre) {
        id_categoria = id;
        categoria = nombre;
        gdpPrincipal.getChildren().clear();
        crearProductos();
    }

    private void crearGUI() {
        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);
        gdpPrincipal.setPadding(new Insets(25));
        this.setVisible(false);
        crearProductos();

        this.getStylesheets().addAll(BootstrapFX.bootstrapFXStylesheet(), getClass().getResource("/css/estilos_productos.css").toExternalForm());
    }

    private void crearProductos() {
        ProductosDAO productosDAO = new ProductosDAO();
        ObservableList<ProductosDAO> listaProductos = productosDAO.listarProductos(id_categoria);

        int columna = 0;
        int fila = 0;

        for (int i = 0; i < listaProductos.size(); i++) {
            ProductosDAO producto = listaProductos.get(i);

            ImageView imageView = null;

            byte[] imagenBytes = producto.getImagenBytes();
            if (imagenBytes != null) {
                imageView = new ImageView(new Image(new ByteArrayInputStream(imagenBytes)));
                imageView.setFitWidth(125);
                imageView.setFitHeight(125);
            }

            Text txtProducto = new Text(producto.getNombre());
            Text txtPrecio = new Text("$" + producto.getPrecio());
            txtProducto.getStyleClass().add("texto-boton");
            txtPrecio.getStyleClass().add("texto-boton");

            Button btnAgregar = new Button("Agregar al carrito");
            btnAgregar.getStyleClass().addAll("btn", "btn-primary");
            btnAgregar.setOnAction(e -> {
                DetalleTicketDAO detalleTicketDAO = new DetalleTicketDAO();
                detalleTicketDAO.crearDetalleTicket(id_ticket, producto.getId_producto(), producto.getPrecio());

                ticketView.actualizarTotal();
                ticketView.actualizarTabla();
            });

            vProducto = new VBox();
            vProducto.setAlignment(Pos.CENTER);

            if (imageView != null) {
                vProducto.getChildren().addAll(imageView, txtProducto, txtPrecio, btnAgregar);
            } else {
                vProducto.getChildren().addAll(txtProducto, txtPrecio, btnAgregar);
            }

            Button btnProducto = new Button();
            btnProducto.setGraphic(vProducto);
            btnProducto.setPrefWidth(175);
            btnProducto.setPrefHeight(175);

            gdpPrincipal.add(btnProducto, columna, fila);

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }
    }
}
