package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class frmCategorias extends Stage {
    private Scene escena;
    private VBox vbox;
    private TextField txtNameCat;
    private Button btnGuardar;
    private CategoriasDAO objCatDAO;
    private TableView<CategoriasDAO> tbvCategorias;
    private ImageView imageView = new ImageView();
    private CheckBox chbActualizarImagen;

    public frmCategorias(TableView<CategoriasDAO> tbvCat, CategoriasDAO objCatDAO) {
        this.tbvCategorias = tbvCat;
        this.objCatDAO = objCatDAO == null ? new CategoriasDAO() : objCatDAO;
        crearGUI();
        escena = new Scene(vbox);
        this.setTitle("Gestión de Categorias");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        txtNameCat = new TextField();
        txtNameCat.setText(objCatDAO.getNom_Categoria());
        txtNameCat.setPromptText("Nombre de la Categoria");
        txtNameCat.setFocusTraversable(false);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        Button btnSeleccionarImagen = new Button("Seleccionar Imagen");
        btnSeleccionarImagen.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(this);
            if (selectedFile != null) {
                try {
                    byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                    objCatDAO.setImagenBytes(imageBytes);
                    Image image = new Image(new ByteArrayInputStream(imageBytes));
                    imageView.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        chbActualizarImagen = new CheckBox("Actualizar imagen");

        btnGuardar = new Button("Guardar Categoría");
        btnGuardar.setOnAction(e -> guardarCategoria());

        vbox = new VBox(txtNameCat, btnSeleccionarImagen, chbActualizarImagen, imageView, btnGuardar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
    }

    private void guardarCategoria() {
        String nombreCategoria = txtNameCat.getText();

        if (nombreCategoria == null || nombreCategoria.isEmpty()) {
            mostrarAlerta("El nombre de la categoría no puede estar vacío.");
            return;
        }

        objCatDAO.setNom_Categoria(nombreCategoria);

        if (chbActualizarImagen.isSelected() && objCatDAO.getImagenBytes() == null) {
            mostrarAlerta("Debe seleccionar una imagen.");
            return;
        }

        if (objCatDAO.getId_Categoria() > 0) {
            if (chbActualizarImagen.isSelected()) {
                objCatDAO.actualizarCategoria();
            } else {
                objCatDAO.actualizarCategoriaSinImagen();
            }
        } else {
            objCatDAO.insertar();
        }

        tbvCategorias.setItems(objCatDAO.listarCategorias());
        tbvCategorias.refresh();

        this.close();
    }


    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText("Error en los datos");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}