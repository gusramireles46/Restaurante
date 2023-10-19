package com.example.restaurante.pruebas;

import com.example.restaurante.modelo.CategoriaDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.restaurante.modelo.Conexion.conexion;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Pruebas extends Stage{


    public static class FileChooserExample extends Stage {

        public FileChooserExample() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar un archivo");

            File selectedFile = fileChooser.showOpenDialog(this);

            if (selectedFile != null) {
                // Obtener el directorio actual de la aplicación
                String currentDirectory = System.getProperty("user.dir");

                // Crear una instancia de Path para el archivo seleccionado y para el directorio actual
                Path selectedPath = Paths.get(selectedFile.getAbsolutePath());
                Path currentPath = Paths.get(currentDirectory);

                // Calcular la ruta relativa
                Path relativePath = currentPath.relativize(selectedPath);

                System.out.println("Ruta absoluta: " + selectedPath.toString());
                System.out.println("Ruta relativa: " + relativePath.toString());
            }
            Button btnCargar = new Button("Cargar");

            Scene escena = new Scene(btnCargar);

            this.setScene(escena);
            this.show();

        }
    }

    /*public boolean guardarImagen(String ruta,String nombre){
        String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            File file = new File(ruta);
            fis = new FileInputStream(file);
            ps = conexion.prepareStatement(insert);
            ps.setBinaryStream(1,fis,(int)file.length());
            ps.setString(2, nombre);
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CategoriaDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                Logger.getLogger(CategoriaDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }*/
}
