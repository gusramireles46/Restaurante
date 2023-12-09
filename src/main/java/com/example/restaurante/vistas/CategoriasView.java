package com.example.restaurante.vistas;

import com.example.restaurante.componentes.CategoriaClickListener;
import com.example.restaurante.modelo.CategoriasDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.ByteArrayInputStream;

public class CategoriasView extends BorderPane {

    private CategoriaClickListener categoriaClickListener;
    private GridPane gdpPrincipal;

    public void setOnCategoriaClicked(CategoriaClickListener listener) {
        this.categoriaClickListener = listener;
    }

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

        int columna = 0;
        int fila = 0;

        for (CategoriasDAO categoria : listaCategorias) {
            ImageView imageView = null;

            byte[] imagenBytes = categoria.getImagenBytes();
            if (imagenBytes != null) {
                imageView = new ImageView(new Image(new ByteArrayInputStream(imagenBytes)));
                imageView.setFitWidth(125);
                imageView.setFitHeight(125);
            }

            Text txtCategoria = new Text(categoria.getNom_Categoria());
            txtCategoria.getStyleClass().add("texto-boton");

            Button categoriaButton = new Button();
            categoriaButton.getStyleClass().addAll("btn", "btn-default");
            categoriaButton.setPrefWidth(175);
            categoriaButton.setPrefHeight(175);
            int idCat = categoria.getId_Categoria();
            String nomCat = categoria.getNom_Categoria();
            categoriaButton.setOnAction(e -> {
                if (categoriaClickListener != null) {
                    categoriaClickListener.onCategoriaClicked(idCat, nomCat);
                }
            });

            VBox categoriaBox = new VBox();
            categoriaBox.setAlignment(Pos.CENTER);
            categoriaBox.setSpacing(5);

            if (imageView != null) {
                categoriaBox.getChildren().add(imageView);
            }

            categoriaBox.getChildren().add(txtCategoria);
            categoriaButton.setGraphic(categoriaBox);
            gdpPrincipal.add(categoriaButton, columna, fila);

            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }

        this.setCenter(gdpPrincipal);
        this.getStylesheets().addAll(BootstrapFX.bootstrapFXStylesheet(), getClass().getResource("/css/estilos_productos.css").toExternalForm());
    }
}


