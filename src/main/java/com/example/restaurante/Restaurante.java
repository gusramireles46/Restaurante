package com.example.restaurante;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Restaurante extends Application {

    private HBox hbox;
    private BorderPane bdpPrincipal;
    private Button btnCategorias, btnClientes, btnSalir;

    @Override
    public void start(Stage stage) {
        CrearGUI();
        Scene scene = new Scene(bdpPrincipal, 1280, 720);
        stage.setTitle("Restaurante");
        stage.setScene(scene);
        stage.show();
    }

    private void CrearGUI() {
        bdpPrincipal = new BorderPane();

        btnCategorias = createButton("Categorías", "/imagenes/categoria.png");
        btnClientes = createButton("Clientes", "/imagenes/clientes.png");
        btnSalir = createButton("Salir", "/imagenes/salida.png");

        hbox = new HBox(btnCategorias, btnClientes, btnSalir);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);

        bdpPrincipal.setCenter(hbox);
        bdpPrincipal.setPadding(new Insets(10));
    }

    private Button createButton(String text, String imageUrl) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imageUrl).toExternalForm()));
        Text textoBoton = new Text(text);
        VBox vBoton = new VBox(imageView, textoBoton);
        vBoton.setAlignment(Pos.CENTER);
        Button button = new Button();
        button.setGraphic(vBoton);
        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}
