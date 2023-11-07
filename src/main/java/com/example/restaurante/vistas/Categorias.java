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
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.ByteArrayInputStream;

public class Categorias extends Stage {
    private GridPane gdpPrincipal;
    private Scene escena;
    private VBox vCategorias;
    private HBox hbox;
    private BorderPane bdpMain;

    public Categorias() {
        crearGUI();
        this.setScene(escena);
        escena.getStylesheets().addAll(BootstrapFX.bootstrapFXStylesheet(), getClass().getResource("/css/estilos_categorias.css").toExternalForm());
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
            categoriaButton.getStyleClass().addAll("btn", "btn-default");
            categoriaButton.setOnAction(e -> new Productos(categoria.getId_Categoria(), categoria.getNom_Categoria()));
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

        Button btnSalir = new Button("X");
        btnSalir.setOnAction(e -> this.close());
        btnSalir.getStyleClass().addAll("btn", "btn-danger");
        btnSalir.setStyle("-fx-font-weight: bold; -fx-font-size: 18px");
        btnSalir.setPrefHeight(50);
        btnSalir.setPrefWidth(100);

        HBox hSalir = new HBox(btnSalir);
        hSalir.setAlignment(Pos.CENTER);
        hSalir.setSpacing(10);
        hSalir.setPadding(new Insets(10));

        bdpMain.setBottom(hSalir);

        escena = new Scene(bdpMain, 800, 600);
    }
}