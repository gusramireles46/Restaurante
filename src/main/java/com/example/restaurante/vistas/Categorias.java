package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;

public class Categorias extends Stage {
    private GridPane gdpPrincipal;
    private Scene escena;
    private VBox vCategorias;

    public Categorias() {
        crearGUI();
        this.setScene(escena);
        escena.getStylesheets().add(getClass().getResource("/css/estilos_categorias.css").toExternalForm());
        this.show();
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

        for (int i = 0; i < listaCategorias.size(); i++) {
            CategoriasDAO categoria = listaCategorias.get(i);

            ImageView imageView = null;

            byte[] imagenBytes = categoria.getImagenBytes();
            if (imagenBytes != null) {
                imageView = new ImageView(new Image(new ByteArrayInputStream(imagenBytes)));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
            }

            Text txtCategoria = new Text(categoria.getNom_Categoria());
            txtCategoria.getStyleClass().add("texto-boton");
            vCategorias = new VBox(imageView, txtCategoria);
            vCategorias.setAlignment(Pos.CENTER);
            Button categoriaButton = new Button();
            categoriaButton.setGraphic(vCategorias);
            categoriaButton.setOnAction(e -> new Productos(categoria.getId_Categoria(), categoria.getNom_Categoria()));
            gdpPrincipal.add(categoriaButton, columna, fila);


            columna++;
            if (columna == 4) {
                columna = 0;
                fila++;
            }
        }
        escena = new Scene(gdpPrincipal, 800, 600);
    }
}