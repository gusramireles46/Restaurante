package com.example.restaurante.modelo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class catDB {
    int id_Categoria;

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public void setNom_Categoria(String nom_Categoria) {
        this.nom_Categoria = nom_Categoria;
    }

    String nom_Categoria;

    public int getId_Categoria() {
        return id_Categoria;
    }

    public String getNom_Categoria() {
        return nom_Categoria;
    }


    public void insertar(){
        try {
            String query = "insert into tblCategorias (nom_Categoria) values ('" + this.nom_Categoria + "')";
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void actualizar(){
        try{
            String query = "update tblCategorias set nom_Categoria = '" + this.nom_Categoria + "' " + "where id_Categoria = " + this.id_Categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminar(){
        try {
            String query = "delete from tblCategorias where id_Categoria = " + this.id_Categoria;
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<catDB> listarCategorias(){
        ObservableList<catDB>listCat = FXCollections.observableArrayList();
        catDB objC;
        try {
            String query = "select * from tblCategorias";
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new catDB();
                objC.setId_Categoria(res.getInt("id_Categoria"));
                objC.setNom_Categoria(res.getString("nom_Categoria"));
                listCat.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCat;
    }
}

