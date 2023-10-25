package com.example.restaurante.vistas;

import com.example.restaurante.componentes.ProductosButton;
import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.modelo.ProductosDAO;
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
import org.kordamp.bootstrapfx.scene.layout.Panel;

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
        escena.getStylesheets().addAll(/*BootstrapFX.bootstrapFXStylesheet(), */getClass().getResource("/css/estilos_crud.css").toExternalForm());
        this.setTitle("CRUD de Productos");
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    private void crearGUI() {
        productosDAO = new ProductosDAO();
        tbvProductos = new TableView<>();
        crearTabla();
        Label title = new Label("CRUD de Productos");
        title.getStyleClass().addAll("titulo");
        btnAgregar = new Button("Agregar producto");
        //btnAgregar.getStyleClass().addAll("btn", "btn-success");
        btnAgregar.getStyleClass().add("add-button");
        btnAgregar.setOnAction(e -> new frmProductos(tbvProductos, null));
        vbox = new VBox(title, tbvProductos, btnAgregar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        vbox.setPadding(new Insets(10));
    }

    private void crearTabla() {
        TableColumn<ProductosDAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));

        TableColumn<ProductosDAO, String> tbcNombreProducto = new TableColumn<>("Producto");
        tbcNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<ProductosDAO, Double> tbcPrecioProducto = new TableColumn<>("Precio");
        tbcPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ProductosDAO, String> tbcCategoria = nombreIdCategoria();

        TableColumn<ProductosDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> productosDAOStringTableColumn) {
                return new ProductosButton(1);
            }
        });

        TableColumn<ProductosDAO, String> tbcModificar = new TableColumn<>("Modificar");
        tbcModificar.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> productosDAOStringTableColumn) {
                return new ProductosButton(2);
            }
        });


        tbvProductos.getColumns().addAll(tbcIdProducto, tbcNombreProducto, tbcPrecioProducto, tbcCategoria, tbcModificar, tbcEliminar);
        tbvProductos.setItems(productosDAO.listarProductos());
    }

    private TableColumn<ProductosDAO, String> nombreIdCategoria() {
        TableColumn<ProductosDAO, String> tbcCategoria = new TableColumn<>("Categoría");
        tbcCategoria.setCellValueFactory(cellData -> {
            categoriasDAO = new CategoriasDAO();
            int id_categoria = cellData.getValue().getId_categoria();
            CategoriasDAO categoria = categoriasDAO.getById(id_categoria);
            return new SimpleStringProperty(categoria.getNom_Categoria());
        });
        return tbcCategoria;
    }

}
