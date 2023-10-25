package com.example.restaurante.componentes;

import com.example.restaurante.modelo.ProductosDAO;
import com.example.restaurante.vistas.frmProductos;
import javafx.scene.control.*;

import java.util.Optional;

public class ProductosButton  extends TableCell<ProductosDAO, String> {
    private Button btnCelda;
    private TableView<ProductosDAO> tbvProductos;
    private ProductosDAO productosDAO;
    private int opcion;

    public ProductosButton(int opc) {
        opcion = opc;
        String textoBoton = (opc == 1) ? "Eliminar" : "Modificar";
        if (opcion == 1) {
            btnCelda = new Button(textoBoton);
            btnCelda.setOnAction(e -> accionBoton());
        } else {
            btnCelda = new Button(textoBoton);
            btnCelda.setOnAction(e -> accionBoton());
        }
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b)
            this.setGraphic(btnCelda);
    }

    private void accionBoton() {
        if (opcion == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmar eliminación");
            alert.setContentText("¿Seguro que desea eliminar este elemento?");
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                tbvProductos = this.getTableView();
                productosDAO = tbvProductos.getItems().get(this.getIndex());
                productosDAO.eliminarProducto();
                tbvProductos.setItems(productosDAO.listarProductos());
                tbvProductos.refresh();
            }
        }  else {
            tbvProductos = this.getTableView();
            productosDAO = tbvProductos.getItems().get(this.getIndex());
            new frmProductos(tbvProductos, productosDAO);
        }
    }
}
