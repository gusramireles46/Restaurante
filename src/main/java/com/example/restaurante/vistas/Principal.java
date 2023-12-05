package com.example.restaurante.vistas;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Stage {
    private HBox hbox;
    private VBox vbox;
    private Scene escena;

    public Principal() {
        crearGUI();
        escena = new Scene(vbox, 800, 600);
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        CategoriasView categoriasView = new CategoriasView();
        vbox = new VBox(categoriasView);
    }
}
