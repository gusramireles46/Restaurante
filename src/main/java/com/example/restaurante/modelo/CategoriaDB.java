package com.example.restaurante.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDB {
    int id_categoria;

    public void setId_Categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNom_Categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    String nom_categoria;

    public int getId_Categoria() {
        return id_categoria;
    }

    public String getNom_Categoria() {
        return nom_categoria;
    }


    public void insertar(){
        try {
            String query = "insert into categorias (nom_categoria) values ('" + this.nom_categoria + "')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actualizar(){
        try{
            String query = "update categorias set nom_categoria = '" + this.nom_categoria + "' " + "where id_categoria = " + this.id_categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminar(){
        try {
            String query = "delete from categorias where id_categoria = " + this.id_categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriaDB> listarCategorias(){
        ObservableList<CategoriaDB>listCat = FXCollections.observableArrayList();
        CategoriaDB objC;
        try {
            String query = "select * from categorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new CategoriaDB();
                objC.setId_Categoria(res.getInt("id_categoria"));
                objC.setNom_Categoria(res.getString("nom_categoria"));
                listCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCat;
    }
}

