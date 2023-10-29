package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Categorias extends Stage {
    private GridPane gdpPrincipal;
    private Scene escena;
    public Categorias(){
        crearGUI();
        this.setScene(escena);
        this.show();
    }
    private void crearGUI(){
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        ObservableList<CategoriasDAO> listaCategorias = categoriasDAO.listarCategorias();

        gdpPrincipal = new GridPane();
        gdpPrincipal.setHgap(10);
        gdpPrincipal.setVgap(10);

        int columna = 0;
        int fila = 0;

        for (int i = 0; i < listaCategorias.size(); i++) {
            CategoriasDAO categoria = listaCategorias.get(i);
            Button categoriaButton = new Button(categoria.getNom_Categoria());
            gdpPrincipal.add(categoriaButton, columna, fila);

            columna ++;
            if (columna == 4){
                columna = 0;
                fila ++;
            }
        }
        escena = new Scene(gdpPrincipal, 800, 600);
    }
}
