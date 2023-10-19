package com.example.restaurante.vistas;

import com.example.restaurante.modelo.catDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class vCategorias extends Stage {
    private Scene escena;
    private HBox hBox;
    private TextField txtNameCat;
    private Button btnGuardar;
    private catDB catdb;
    TableView<catDB> tbvCategorias;
    public vCategorias(TableView<catDB> tbvCat, catDB catdb){
        this.tbvCategorias = tbvCat;
        this.catdb = catdb == null ? new catDB() : catdb;
        CrearUI();
        escena = new Scene(hBox);
        this.setTitle("Categorias");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        catdb = new catDB();
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
        else
            catdb.insertar();
        catdb.insertar();
        tbvCategorias.setItems(catdb.listarCategorias());
        tbvCategorias.refresh();
        this.close();
    }
}
