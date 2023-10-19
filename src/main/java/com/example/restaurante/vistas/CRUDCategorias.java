package com.example.restaurante.vistas;

import com.example.restaurante.componentes.btnCell;
import com.example.restaurante.modelo.CategoriaDB;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CRUDCategorias extends Stage {
    CategoriaDB categoriasDAO;
    private VBox vbox;
    private TableView<CategoriaDB> tbvCategorias;
    private Button btnAgregar;
    private Scene escena;

    public CRUDCategorias() {
        createGUI();
        escena = new Scene(vbox, 640, 480);
        this.setTitle("CRUD Categorias");
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    public void createGUI() {
        categoriasDAO = new CategoriaDB();
        tbvCategorias = new TableView<>();
        createTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(event -> new vCategorias(tbvCategorias, null));
        vbox = new VBox(tbvCategorias, btnAgregar);
    }

    private void createTable() {
        TableColumn<CategoriaDB, Integer> tbcIdCategoria = new TableColumn<>("ID");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));

        TableColumn<CategoriaDB, String> tbcNomCategoria = new TableColumn<>("Categoría");
        tbcNomCategoria.setCellValueFactory(new PropertyValueFactory<>("nom_categoria"));

        TableColumn<CategoriaDB, String> tbcBorrar = new TableColumn<>("Eliminar");
        tbcBorrar.setCellFactory(new Callback<TableColumn<CategoriaDB, String>, TableCell<CategoriaDB, String>>() {
            @Override
            public TableCell<CategoriaDB, String> call(TableColumn<CategoriaDB, String> param) {
                return new btnCell(2);
            }
        });

        TableColumn<CategoriaDB, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<CategoriaDB, String>, TableCell<CategoriaDB, String>>() {
            @Override
            public TableCell<CategoriaDB, String> call(TableColumn<CategoriaDB, String> param) {
                return new btnCell(1);
            }
        });

        tbvCategorias.getColumns().addAll(tbcIdCategoria, tbcNomCategoria, tbcEditar, tbcBorrar);
        tbvCategorias.setItems(categoriasDAO.listarCategorias());
    }
}
