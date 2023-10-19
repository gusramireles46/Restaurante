package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriaDB;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class pCat extends Stage {

    private VBox vBox;
    private TableView<CategoriaDB> tbvCategorias;
    private Button btnAgregar;
    private CategoriaDB categoriasDAO;
    private Scene escena;
    private GridPane gdpCat;

    public pCat() {
        CrearUI();
        escena = new Scene(vBox, 480, 320);
        this.setTitle("Categorías");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        categoriasDAO = new CategoriaDB();
        tbvCategorias = new TableView<>();
        //CreateTable();
        crearGrid();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(event -> new vCategorias(tbvCategorias, null));
        vBox = new VBox(gdpCat, btnAgregar);
    }

    private void crearGrid() {
        categoriasDAO = new CategoriaDB();
        gdpCat = new GridPane();

        // Obtén los datos de la base de datos en un ObservableList
        ObservableList<CategoriaDB> categorias = categoriasDAO.listarCategorias();

        int row = 0;
        int col = 0;

        // Recorre la lista de categorías y crea botones en el GridPane
        for (CategoriaDB categoria : categorias) {
            Button categoriaButton = new Button(categoria.getNom_Categoria());
            // Agrega un manejador de evento para el botón si es necesario
            gdpCat.add(categoriaButton, col, row);
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }

    }
    /*private void CreateTable(){
        TableColumn<catDB, Integer> tbcIdCategoria = new TableColumn<>("ID");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_Categoria"));

        TableColumn<catDB, String> tbcNomCategoria = new TableColumn<>("Categoria");
        tbcNomCategoria.setCellValueFactory(new PropertyValueFactory<>("nom_Categoria"));

        TableColumn<catDB, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<catDB, String>, TableCell<catDB, String>>() {
            @Override
            public TableCell<catDB, String> call(TableColumn<catDB, String> categoriasDAOStringTableColumn) {
                return new btnCell(1);
            }
        });

        TableColumn<catDB, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<catDB, String>, TableCell<catDB, String>>() {
            @Override
            public TableCell<catDB, String> call(TableColumn<catDB, String> categoriasDAOStringTableColumn) {
                return new btnCell(2);
            }
        });

        tbvCategorias.getColumns().addAll(tbcIdCategoria, tbcNomCategoria, tbcEditar, tbcEliminar);
        tbvCategorias.setItems(categoriasDAO.listarCategorias());

    }*/
}