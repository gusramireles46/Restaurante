package com.example.restaurante.vistas;

import com.example.restaurante.Restaurante;
import com.example.restaurante.modelo.DetalleTicketDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.ByteArrayInputStream;

public class Productos extends Stage {
    private GridPane gdpPrincipal;
    private Scene escena;
    private VBox vProducto;
    private HBox hbox;
    private int id_categoria;
    private String categoria;
    private BorderPane bdpMain;
    private int id_ticket = Restaurante.id_ticket;

    public Productos(int id, String nombre) {
        id_categoria = id;
        categoria = nombre;
        crearGUI();
        hbox = new HBox(gdpPrincipal);
        hbox.setAlignment(Pos.CENTER);

        bdpMain = new BorderPane();
        bdpMain.setCenter(hbox);

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> this.close());
        btnSalir.getStyleClass().addAll("btn", "btn-warning");
        btnSalir.setPrefHeight(50);
        btnSalir.setPrefWidth(100);

        HBox hSalir = new HBox(btnSalir);
        hSalir.setAlignment(Pos.CENTER);
        hSalir.setSpacing(10);
        hSalir.setPadding(new Insets(10));

        bdpMain.setBottom(hSalir);

        escena = new Scene(bdpMain, 800, 600);
        escena.getStylesheets().addAll(BootstrapFX.bootstrapFXStylesheet(), getClass().getResource("/css/estilos_productos.css").toExternalForm());
        this.setScene(escena);
        this.setTitle("Productos de la categoría: " + categoria);
        this.show();
    }

    private void crearGUI() {
        ProductosDAO productosDAO = new ProductosDAO();
        ObservableList<ProductosDAO> listaProductos = productosDAO.listarProductos(id_categoria);

        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);
        gdpPrincipal.setPadding(new Insets(25));

        int columna = 0;
        int fila = 0;

        for (int i = 0; i < listaProductos.size(); i++) {
            ProductosDAO producto = listaProductos.get(i);

            ImageView imageView = null;

            byte[] imagenBytes = producto.getImagenBytes();
            if (imagenBytes != null) {
                imageView = new ImageView(new Image(new ByteArrayInputStream(imagenBytes)));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
            }

            Text txtProducto = new Text(producto.getNombre());
            Text txtPrecio = new Text("$" + producto.getPrecio());
            txtProducto.getStyleClass().add("texto-boton");
            txtPrecio.getStyleClass().add("texto-boton");

            Button btnAgregar = new Button("Agregar al carrito");
            btnAgregar.getStyleClass().addAll("btn", "btn-primary");
            //btnAgregar.setOnAction(e -> Restaurante.carritoCompras.agregarProducto(producto));
            btnAgregar.setOnAction(e -> {
                DetalleTicketDAO detalleTicketDAO = new DetalleTicketDAO();
                detalleTicketDAO.crearDetalleTicket(id_ticket, producto.getId_producto(), producto.getPrecio());
            });

            vProducto = new VBox(imageView, txtProducto, txtPrecio, btnAgregar);
            vProducto.setAlignment(Pos.CENTER);

            Button btnProducto = new Button();
            btnProducto.setGraphic(vProducto);

            gdpPrincipal.add(btnProducto, columna, fila);

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }
    }
}
