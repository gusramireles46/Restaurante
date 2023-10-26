package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class frmCategorias extends Stage {
    private Scene escena;
    private HBox hBox;
    private TextField txtNameCat;
    private Button btnGuardar;
    private CategoriasDAO objCatDAO;
    TableView<CategoriasDAO> tbvCategorias;
    public frmCategorias(TableView<CategoriasDAO> tbvCat, CategoriasDAO objCatDAO){
        this.tbvCategorias = tbvCat;
        this.objCatDAO = objCatDAO == null ? new CategoriasDAO() : objCatDAO;
        CrearUI();
        escena = new Scene(hBox);
        this.setTitle("Gestión de Categorias");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        objCatDAO = new CategoriasDAO();
        txtNameCat = new TextField();
        txtNameCat.setText(objCatDAO.getNom_Categoria());
        txtNameCat.setPromptText("Nombre de la Categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarCategoria());
        hBox = new HBox(txtNameCat, btnGuardar);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
    }
    private void guardarCategoria(){
        objCatDAO.setNom_Categoria(txtNameCat.getText());
        if(objCatDAO.getId_Categoria() > 0)
            objCatDAO.actualizar();
        else
            objCatDAO.insertar();
        objCatDAO.insertar();
        tbvCategorias.setItems(objCatDAO.listarCategorias());
        tbvCategorias.refresh();
        this.close();
    }
}
