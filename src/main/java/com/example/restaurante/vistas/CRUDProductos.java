package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CRUDProductos extends Stage {
    private ProductosDAO productosDAO;
    private CategoriasDAO categoriasDAO;
    private VBox vbox;
    private TableView<ProductosDAO> tbvProductos;
    private Button btnAgregar;
    private Scene escena;

    public CRUDProductos() {
        crearGUI();
        escena = new Scene(vbox, 640, 480);
        this.setTitle("CRUD de Productos");
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    private void crearGUI() {
        productosDAO = new ProductosDAO();
        categoriasDAO = new CategoriasDAO();
        tbvProductos = new TableView<>();
        crearTabla();
        btnAgregar = new Button("Agregar producto");
        btnAgregar.setOnAction(e -> {});
        vbox = new VBox(tbvProductos, btnAgregar);
    }

    private void crearTabla() {
        TableColumn<ProductosDAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));

        TableColumn<ProductosDAO, String> tbcNombreProducto = new TableColumn<>("Producto");
        tbcNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<ProductosDAO, Double> tbcPrecioProducto = new TableColumn<>("Precio");
        tbcPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ProductosDAO, String> tbcCategoria = new TableColumn<>("Categoría");
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));



        tbvProductos.getColumns().addAll(tbcIdProducto, tbcNombreProducto, tbcPrecioProducto, tbcCategoria);
        tbvProductos.setItems(productosDAO.listarProductos());
    }

}
