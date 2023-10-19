package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriaDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class vCategorias extends Stage {
    private Scene escena;
    private HBox hBox;
    private TextField txtNameCat;
    private Button btnGuardar;
    private CategoriaDB catdb;
    TableView<CategoriaDB> tbvCategorias;
    public vCategorias(TableView<CategoriaDB> tbvCat, CategoriaDB catdb){
        this.tbvCategorias = tbvCat;
        this.catdb = catdb == null ? new CategoriaDB() : catdb;
        CrearUI();
        escena = new Scene(hBox);
        this.setTitle("Categorias");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        catdb = new CategoriaDB();
        txtNameCat = new TextField();
        txtNameCat.setText(catdb.getNom_Categoria());
        txtNameCat.setPromptText("Nombre de la Categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarCategoria());
        hBox = new HBox(txtNameCat, btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
    }
    private void guardarCategoria(){
        catdb.setNom_Categoria(txtNameCat.getText());
        if(catdb.getId_Categoria() > 0)
            catdb.actualizar();
        catdb.insertar();
        tbvCategorias.setItems(catdb.listarCategorias());
        tbvCategorias.refresh();
        this.close();
    }
}
