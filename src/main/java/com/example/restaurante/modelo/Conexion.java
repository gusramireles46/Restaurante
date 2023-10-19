package com.example.restaurante.modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion{
    private static String server = "127.0.0.1";
    private static String user = "topicos";
    private static String pass = "1234567890";
    private static String db = "cdg";

    public static Connection conexion;
    public static void createConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://" + server + ":3306/" + db, user, pass);
        }catch (Exception e){
            System.out.println("Conexion faliida");
        }
    }
}