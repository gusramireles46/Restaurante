package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;

public class CategoriasView extends BorderPane {

    private GridPane gdpPrincipal;
    private VBox vCategorias;
    private HBox hbox;
    private BorderPane bdpMain;

    public CategoriasView() {
        crearGUI();
    }

    private void crearGUI() {
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        ObservableList<CategoriasDAO> listaCategorias = categoriasDAO.listarCategoriasConImagen();

        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);
        gdpPrincipal.setPadding(new Insets(25));

        vCategorias = new VBox();  // Mover la inicialización fuera del bucle

        int columna = 0;
        int fila = 0;

        double buttonWidth = 150;
        double buttonHeight = 150;

        for (int i = 0; i < listaCategorias.size(); i++) {
            CategoriasDAO categoria = listaCategorias.get(i);

            ImageView imageView = null;

            byte[] imagenBytes = categoria.getImagenBytes();
            if (imagenBytes != null) {
                imageView = new ImageView(new Image(new ByteArrayInputStream(imagenBytes)));
                imageView.setFitWidth(125);
                imageView.setFitHeight(125);
            }

            Text txtCategoria = new Text(categoria.getNom_Categoria());
            txtCategoria.getStyleClass().add("texto-boton");

            if (imageView != null) {
                vCategorias.getChildren().addAll(imageView, txtCategoria);
            } else {
                vCategorias.getChildren().add(txtCategoria);
            }

            Button categoriaButton = new Button();
            categoriaButton.getStyleClass().addAll("btn", "btn-default");
            categoriaButton.setPrefWidth(175);
            categoriaButton.setPrefHeight(175);
            categoriaButton.setOnAction(e -> new Productos(categoria.getId_Categoria(), categoria.getNom_Categoria()));

            if (imageView != null) {
                categoriaButton.setGraphic(vCategorias);
            } else {
                categoriaButton.setText(categoria.getNom_Categoria());
            }

            gdpPrincipal.add(categoriaButton, columna, fila);

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }

        hbox = new HBox(gdpPrincipal);
        hbox.setAlignment(Pos.CENTER);
        bdpMain = new BorderPane();
        bdpMain.setCenter(hbox);
        this.setCenter(bdpMain);  // Agrega esto para que el BorderPane principal tenga el centro correctamente
    }
}
