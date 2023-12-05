package com.example.restaurante.modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion{
    private static String server = "localhost";
    private static String user = "topicos";
    private static String pass = "1234567890";
    private static String db = "coladegato";

    public static Connection conexion;
    public static void createConnection(){
        try{
            /*Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://" + server + ":3306/" + db, user, pass);*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db, user, pass);
        }catch (Exception e){
            System.out.println("Conexion fallida");
        }
    }
}