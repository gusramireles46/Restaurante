package com.example.restaurante.vistas;

import com.example.restaurante.modelo.ProductosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUDProductos extends Stage {
    private ProductosDAO productosDAO;
    private VBox vbox;
    private TableView<ProductosDAO> tbvProductos;
    private Button btnAgregar;
    private Scene escena;

    public CRUDProductos() {
        crearGUI();
        this.setTitle("CRUD de Productos");
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    private void crearGUI() {
        productosDAO = new ProductosDAO();
    }

}
