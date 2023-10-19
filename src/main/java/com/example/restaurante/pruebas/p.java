package com.example.restaurante.pruebas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class p {
    private HBox hbox;
    private BorderPane bdpPrincipal;
    private Button btnCategorias, btnClientes, btnSalir;
    private Label lblNombre;

    @Override
    public void start(Stage stage) {
        CrearGUI();
        Scene scene = new Scene(bdpPrincipal, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/css/estilos_principal.css").toExternalForm());
        stage.setTitle("Cola de Gato");
        stage.setScene(scene);
        stage.show();
    }

    private void CrearGUI() {
        bdpPrincipal = new BorderPane();

        lblNombre = new Label("Nombre del restaurante");
        lblNombre.getStyleClass().add("title");

        bdpPrincipal.setTop(lblNombre);

        btnCategorias = createButton("Categorías", "/imagenes/categoria.png");

        hbox = new HBox(btnCategorias);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);

        bdpPrincipal.setCenter(hbox);
        bdpPrincipal.setPadding(new Insets(10));
    }
    private Button createButton(String text, String imageUrl) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imageUrl).toExternalForm()));
        Text textoBoton = new Text(text);
        textoBoton.getStyleClass().add("texto-boton");
        VBox vBoton = new VBox(imageView, textoBoton);
        vBoton.setAlignment(Pos.CENTER);
        Button boton = new Button();
        boton.setGraphic(vBoton);
        return boton;
    }
}
