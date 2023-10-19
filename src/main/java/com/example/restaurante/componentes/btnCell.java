package com.example.restaurante.componentes;

import com.example.restaurante.modelo.CategoriaDB;
import com.example.restaurante.vistas.vCategorias;
import javafx.scene.control.*;

import java.util.Optional;

public class btnCell extends TableCell<CategoriaDB, String> {
    private Button btnCelda;
    private int opc;
    private TableView<CategoriaDB> tbvCategorias;
    private CategoriaDB objCat;
    public btnCell(int opc){
        this.opc = opc;

        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda = new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton(){
        tbvCategorias = btnCell.this.getTableView();
        objCat = tbvCategorias.getItems().get(btnCell.this.getIndex());
        if(this.opc == 1){
            new vCategorias(tbvCategorias, objCat);
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Tópicos Avazados de Programación");
            alert.setHeaderText("Confirmación del Sistema");
            alert.setContentText("¿Deseas eliminar la categoría?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                objCat.eliminar();
                tbvCategorias.setItems(objCat.listarCategorias());
                tbvCategorias.refresh();
            }
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(btnCelda);
        }
    }
}