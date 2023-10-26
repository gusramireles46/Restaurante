package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class frmProductos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TextField txfNombre, txfPrecio;
    private ComboBox<CategoriasDAO> cbxCategoria;
    private Button btnGuardar;
    private ProductosDAO productosDAO;
    private CategoriasDAO categoriasDAO;
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
        txfNombre.setText(productosDAO.getNombre());
        txfNombre.setPromptText("Nombre del producto");
        txfNombre.setFocusTraversable(false);
        txfPrecio = new TextField();
        txfPrecio.setText(String.valueOf(productosDAO.getPrecio()));
        txfPrecio.setPromptText("Precio del producto");
        txfPrecio.setFocusTraversable(false);

        cbxCategoria = new ComboBox<>();
        categoriasDAO = new CategoriasDAO();
        categoriasDAO.setId_Categoria(productosDAO.getId_categoria());
        categoriasDAO.getById();
        cbxCategoria.setValue(categoriasDAO);
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
        try {
            productosDAO.setNombre(txfNombre.getText());
            double precio = Double.parseDouble(txfPrecio.getText());
            productosDAO.setPrecio(precio);

            CategoriasDAO catTemp = cbxCategoria.getValue();
            productosDAO.setId_categoria(catTemp.getId_Categoria());

            if (productosDAO.getId_producto() > 0)
                productosDAO.actualizarProducto();
            else
                productosDAO.insertarProducto();
            tbvProductos.setItems(productosDAO.listarProductos());
            tbvProductos.refresh();

            this.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Error en los datos");
            alert.setContentText("Por favor ingrese sólo valores válidos (Precio: #.##).");
            alert.showAndWait();
        }
    }
}
