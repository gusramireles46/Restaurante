package com.example.restaurante.modelo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoriasDAO {
    int id_categoria;
    String nom_categoria;

    public void setId_Categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNom_Categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public int getId_Categoria() {
        return id_categoria;
    }

    public String getNom_Categoria() {
        return nom_categoria;
    }


    public void insertar(){
        try {
            String query = "INSERT INTO categorias (nom_categoria) VALUES ('" + this.nom_categoria + "')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actualizar(){
        try{
            String query = "UPDATE categorias SET nom_categoria = '" + this.nom_categoria + "' " + "WHERE id_categoria = " + this.id_categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminar(){
        try {
            String query = "DELETE FROM categorias WHERE id_categoria = " + this.id_categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriasDAO> listarCategorias(){
        ObservableList<CategoriasDAO>listCat = FXCollections.observableArrayList();
        CategoriasDAO objC;
        try {
            String query = "SELECT * FROM categorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriasDAO();
                objC.setId_Categoria(res.getInt("id_categoria"));
                objC.setNom_Categoria(res.getString("nom_categoria"));
                listCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCat;
    }

    public CategoriasDAO getById(int idCategoria) {
        CategoriasDAO cat = new CategoriasDAO();
        String query = "SELECT nom_categoria FROM categorias WHERE id_categoria = " + idCategoria;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                cat.setNom_Categoria(res.getString("nom_categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }
}
