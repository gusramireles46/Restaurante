package com.example.restaurante.vistas;

import com.example.restaurante.componentes.CategoriasButton;
import com.example.restaurante.modelo.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class CRUDCategorias extends Stage {
    private VBox vbox;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnAgregar;
    private CategoriasDAO categoriasDAO;
    private Scene escena;

    public CRUDCategorias(){
        CrearUI();
        escena = new Scene(vbox, 640, 480);
        escena.getStylesheets().addAll(/*BootstrapFX.bootstrapFXStylesheet(), */getClass().getResource("/css/estilos_crud.css").toExternalForm());
        this.setTitle("CRUD de Categorías");
        this.setScene(escena);
        this.sizeToScene();
        this.show();

    }
    private void CrearUI(){
        categoriasDAO = new CategoriasDAO();
        tbvCategorias = new TableView<>();
        Label title = new Label("CRUD de Categorías");
        title.getStyleClass().addAll("titulo");
        CreateTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().setAll("add-button");
        btnAgregar.setOnAction(event -> new frmCategorias(tbvCategorias,  null));
        vbox = new VBox(title, tbvCategorias, btnAgregar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        vbox.setPadding(new Insets(10));
    }
    private void CreateTable(){
        TableColumn<CategoriasDAO, Integer> tbcIdCategoria = new TableColumn<>("ID");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_Categoria"));

        TableColumn<CategoriasDAO, String> tbcNomCategoria = new TableColumn<>("Categoria");
        tbcNomCategoria.setCellValueFactory(new PropertyValueFactory<>("nom_Categoria"));

        TableColumn<CategoriasDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
            @Override
            public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                return new CategoriasButton(1);
            }
        });

        TableColumn<CategoriasDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
            @Override
            public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> categoriasDAOStringTableColumn) {
                return new CategoriasButton(2);
            }
        });

        tbvCategorias.getColumns().addAll(tbcIdCategoria, tbcNomCategoria, tbcEditar, tbcEliminar);
        tbvCategorias.setItems(categoriasDAO.listarCategorias());
    }
}
