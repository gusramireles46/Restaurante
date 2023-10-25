package com.example.restaurante;

import com.example.restaurante.modelo.Conexion;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Optional;

public class Restaurante extends Application {

    private HBox hbox, hHerramientas;
    private VBox vbox;
    private BorderPane bdpPrincipal;
    private Button btnCategorias, btnSalir, btnGestionCategorias, btnGestionProductos;
    private Label lblNombre;

    @Override
    public void start(Stage stage) {
        conectarDB();
        CrearGUI();
        Scene scene = new Scene(bdpPrincipal, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/css/estilos_principal.css").toExternalForm());
        stage.setTitle("Cola de Gato");
        stage.setScene(scene);
        stage.show();
    }

    public void conectarDB() {
        Conexion.createConnection();
    }

    private void CrearGUI() {
        bdpPrincipal = new BorderPane();

        lblNombre = new Label("Cola de gato");
        lblNombre.getStyleClass().add("title");

        bdpPrincipal.setTop(lblNombre);

        btnCategorias = createButton("Categorías", "/imagenes/categoria.png");

        btnGestionCategorias = createButton("Gestionar Categorías", "/imagenes/gestionCategorias.png");
        btnGestionCategorias.setPrefWidth(250);

        btnGestionProductos = createButton("Gestionar Productos", "/imagenes/gestionProductos.png");
        btnGestionProductos.setPrefWidth(250);

        btnSalir = createButton("Salir", "/imagenes/salida.png");
        btnSalir.setOnAction(e -> salir());
        btnSalir.setPrefWidth(250);

        hbox = new HBox(btnCategorias);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);

        hHerramientas = new HBox(btnGestionCategorias, btnGestionProductos, btnSalir);
        hHerramientas.setAlignment(Pos.CENTER);
        hHerramientas.setSpacing(30);

        vbox = new VBox(hbox, hHerramientas);
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);

        bdpPrincipal.setCenter(vbox);
        bdpPrincipal.setPadding(new Insets(10));
    }

    private void salir() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Mensaje del sistema");
        a.setHeaderText("Estas saliendo de la aplicación");
        a.setContentText("¿Seguro que deseas abandonar?");
        Optional<ButtonType> opc = a.showAndWait();
        if (opc.get() == ButtonType.OK)
            System.exit(0);
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

    public static void main(String[] args) {
        launch();
    }
}
