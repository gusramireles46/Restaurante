package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class frmProductos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TextField txfNombre, txfPrecio;
    private ComboBox<CategoriasDAO> cbxCategoria;
    private Button btnGuardar;
    private ProductosDAO productosDAO;
    private TableView<ProductosDAO> tbvProductos;

    public frmProductos(TableView<ProductosDAO> tbvProd, ProductosDAO objProd) {
        this.tbvProductos = tbvProd;
        this.productosDAO = (objProd == null) ? new ProductosDAO() : objProd;
        crearGUI();
        escena = new Scene(vbox);
        this.setTitle("Gestión de Productos");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        txfNombre = new TextField();
        txfNombre.setPromptText("Nombre del producto");
        txfNombre.setFocusTraversable(false);
        txfPrecio = new TextField();
        txfPrecio.setPromptText("Precio del producto");
        txfPrecio.setFocusTraversable(false);

        cbxCategoria = new ComboBox<>();
        ObservableList<CategoriasDAO> categorias = new CategoriasDAO().listarCategorias();
        cbxCategoria.setItems(categorias);
        if (!categorias.isEmpty()) {
            cbxCategoria.setValue(categorias.get(0));
        }


        btnGuardar = new Button("Guardar producto");
        btnGuardar.setOnAction(e -> guardarProducto());

        vbox = new VBox(txfNombre, txfPrecio, cbxCategoria, btnGuardar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
    }

    private void guardarProducto() {
    }
}
