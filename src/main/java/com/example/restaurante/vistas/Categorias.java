package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.collections.ObservableList;
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
    public Categorias() {
        crearGUI();
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        ObservableList<CategoriasDAO> listaCategorias = categoriasDAO.listarCategoriasConImagen();

        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);

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

            Button categoriaButton = new Button();
            categoriaButton.setGraphic(new VBox(imageView, new Text(categoria.getNom_Categoria())));
            categoriaButton.setOnAction(e -> new Productos(categoria.getId_Categoria()));
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