package com.example.restaurante.vistas;

import com.example.restaurante.componentes.btnCell;
import com.example.restaurante.modelo.catDB;
import com.example.tap2023.componentes.ButtonCell;
import com.example.tap2023.modelos.CategoriasDAO;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class pCat extends Stage {
    private VBox vBox;
    private TableView<catDB> tbvCategorias;
    private Button btnAgregar;
    private catDB categoriasDAO;
    public Restaurante(){
        CrearUI();


    }
    private void CrearUI(){
        categoriasDAO = new catDB();
        tbvCategorias = new TableView<>();
        CreateTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().setAll("btn","btn-success");
        btnAgregar.setOnAction(event -> new vCategorias(tbvCategorias,  null));
        vBox = new VBox(tbvCategorias, btnAgregar);
    }
    private void CreateTable(){
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

    }
}