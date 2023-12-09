package com.example.restaurante.vistas;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Principal extends Stage {
    private GridPane gridPane;
    private CategoriasView categoriasView;
    private ProductosView productosView;
    private TicketView ticketView;

    public Principal() {
        crearGUI();
        Scene escena = new Scene(gridPane, 800, 600);
        this.setMaximized(true);
        this.setResizable(false);
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        ticketView = new TicketView();
        categoriasView = new CategoriasView();
        productosView = new ProductosView(ticketView);

        productosView.setVisible(false);

        categoriasView.setOnCategoriaClicked((id, nombre) -> {
            productosView.actualizarProductos(id, nombre);
            productosView.setVisible(true);
            ticketView.actualizarTabla();
        });

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        GridPane.setConstraints(categoriasView, 0, 0, 1, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);
        gridPane.getChildren().add(categoriasView);

        GridPane.setConstraints(productosView, 0, 1, 1, 1, HPos.LEFT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS);
        gridPane.getChildren().add(productosView);

        GridPane.setConstraints(ticketView, 1, 0, 1, 1, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);
        gridPane.getChildren().add(ticketView);
    }
}
