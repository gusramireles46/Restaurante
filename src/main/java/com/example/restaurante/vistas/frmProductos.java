package com.example.restaurante.vistas;

import com.example.restaurante.modelo.CategoriasDAO;
import com.example.restaurante.modelo.ProductosDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class frmProductos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TextField txfNombre, txfPrecio;
    private ComboBox<CategoriasDAO> cbxCategoria;
    private Button btnGuardar;
    private ProductosDAO productosDAO;
    private CategoriasDAO categoriasDAO;
    private TableView<ProductosDAO> tbvProductos;
    ImageView imageView = new ImageView();
    private CheckBox chbActualizarImagen;

    public frmProductos(TableView<ProductosDAO> tbvProd, ProductosDAO objProd) {
        this.tbvProductos = tbvProd;
        this.productosDAO = (objProd == null) ? new ProductosDAO() : objProd;
        crearGUI();
        escena = new Scene(vbox);
        this.setTitle("Gestión de Productos");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI() {
        txfNombre = new TextField();
        txfNombre.setText(productosDAO.getNombre());
        txfNombre.setPromptText("Nombre del producto");
        txfNombre.setFocusTraversable(false);
        txfPrecio = new TextField();
        txfPrecio.setText(String.valueOf(productosDAO.getPrecio()));
        txfPrecio.setPromptText("Precio del producto");
        txfPrecio.setFocusTraversable(false);

        cbxCategoria = new ComboBox<>();
        categoriasDAO = new CategoriasDAO();
        categoriasDAO.setId_Categoria(productosDAO.getId_categoria());
        categoriasDAO.getById();
        cbxCategoria.setValue(categoriasDAO);
        ObservableList<CategoriasDAO> categorias = new CategoriasDAO().listarCategorias();
        cbxCategoria.setItems(categorias);
        if (!categorias.isEmpty()) {
            cbxCategoria.setValue(categorias.get(0));
        }

        FileChooser fileChooser = new FileChooser();
        String homeUsuario =System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Restaurante";
        fileChooser.setInitialDirectory(new File(homeUsuario));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        Button btnSeleccionarImagen = new Button("Seleccionar Imagen");
        btnSeleccionarImagen.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(this);
            if (selectedFile != null) {
                try {
                    byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                    productosDAO.setImagenBytes(imageBytes);
                    Image image = new Image(new ByteArrayInputStream(imageBytes));
                    imageView.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        chbActualizarImagen = new CheckBox("Actualizar imagen");

        btnGuardar = new Button("Guardar producto");
        btnGuardar.setOnAction(e -> guardarProducto());

        vbox = new VBox(txfNombre, txfPrecio, cbxCategoria, btnSeleccionarImagen, chbActualizarImagen, imageView, btnGuardar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
    }

    private void guardarProducto() {
        try {
            String nombre = txfNombre.getText();

            if (nombre == null || nombre.isEmpty()) {
                mostrarAlerta("El nombre del producto no puede estar vacío.");
                return;
            }

            productosDAO.setNombre(nombre);

            String precioStr = txfPrecio.getText();

            if (precioStr.isEmpty()) {
                mostrarAlerta("El precio no puede estar vacío.");
                return;
            }

            double precio = Double.parseDouble(precioStr);

            if (precio <= 0) {
                mostrarAlerta("El precio debe ser mayor que cero.");
                return;
            }

            productosDAO.setPrecio(precio);

            CategoriasDAO catTemp = cbxCategoria.getValue();
            productosDAO.setId_categoria(catTemp.getId_Categoria());

            if (productosDAO.getId_producto() > 0) {
                if (chbActualizarImagen.isSelected()) {
                    if (productosDAO.getImagenBytes() == null) {
                        mostrarAlerta("Debe seleccionar una imagen.");
                        return;
                    }
                    productosDAO.actualizarProducto();
                } else {
                    productosDAO.actualizarProductoSinImagen();
                }
            } else {
                if (chbActualizarImagen.isSelected() && productosDAO.getImagenBytes() == null) {
                    mostrarAlerta("Debe seleccionar una imagen.");
                    return;
                }
                productosDAO.insertarProducto();
            }
            tbvProductos.setItems(productosDAO.listarProductos());
            tbvProductos.refresh();

            this.close();
        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor ingrese solo valores válidos (Precio: #.##).");
        }
    }



    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText("Error en los datos");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
