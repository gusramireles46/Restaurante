package com.example.restaurante.componentes;

import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.vistas.frmCategorias;
import javafx.scene.control.*;

import java.util.Optional;

public class CategoriasButton extends TableCell <CategoriasDAO, String> {
    private Button btnCelda;
    private int opc;
    private TableView<CategoriasDAO> tbvCategorias;
    private CategoriasDAO objCat;
    public CategoriasButton(int opc){
        this.opc = opc;

        String txtBtn = this.opc == 1 ? "Editar" : "Eliminar";
        btnCelda = new Button(txtBtn);
        btnCelda.setOnAction(event -> accionBoton());
    }

    private void accionBoton(){
        tbvCategorias = CategoriasButton.this.getTableView();
        objCat = tbvCategorias.getItems().get(CategoriasButton.this.getIndex());
        if(this.opc == 1){
            new frmCategorias(tbvCategorias, objCat);
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
