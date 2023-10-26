package com.example.restaurante.vistas;

import com.example.restaurante.componentes.CategoriasButton;
import com.example.restaurante.modelo.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class CRUDCategorias extends Stage {
    private VBox vBox;
    private TableView<CategoriasDAO> tbvCategorias;
    private Button btnAgregar;
    private CategoriasDAO categoriasDAO;
    public CRUDCategorias(){
        CrearUI();
        Panel panel = new Panel("This is the title");
        panel.getStyleClass().add("panel-primary");                            //(2)
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        //Button button = new Button("Hello BootstrapFX");
        //button.getStyleClass().setAll("btn","btn-success");                     //(2)
        content.setCenter(vBox);
        panel.setBody(content);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

        this.setTitle("BootstrapFX");
        this.setScene(scene);
        this.sizeToScene();
        this.show();

    }
    private void CrearUI(){
        categoriasDAO = new CategoriasDAO();
        tbvCategorias = new TableView<>();
        CreateTable();
        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().setAll("btn","btn-success");
        btnAgregar.setOnAction(event -> new frmCategorias(tbvCategorias,  null));
        vBox = new VBox(tbvCategorias, btnAgregar);
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
