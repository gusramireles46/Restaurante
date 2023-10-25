package com.example.restaurante.vistas;

import com.example.restaurante.modelo.ProductosDAO;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Productos extends Stage {
    private GridPane gdpPrincipal;
    private Scene escena;

    public Productos() {
        crearGUI();
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        ProductosDAO productosDAO = new ProductosDAO();
        ObservableList<ProductosDAO> listaProductos = productosDAO.listarProductos();

        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);

        int columna = 0;
        int fila = 0;

        for (int i = 0; i < listaProductos.size(); i++) {
            ProductosDAO producto = listaProductos.get(i);
            Button productoButton = new Button(producto.getNombre() + " - Precio: $" + producto.getPrecio());
            gdpPrincipal.add(productoButton, columna, fila); // Agregar el botón al GridPane

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }

        escena = new Scene(gdpPrincipal, 800, 600);
    }
}
