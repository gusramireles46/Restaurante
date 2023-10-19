package com.example.restaurante.pruebas;

import com.example.restaurante.modelo.CategoriaDB;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.restaurante.modelo.Conexion.conexion;

public class Pruebas {
    public boolean guardarImagen(String ruta,String nombre){
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
    }
}
