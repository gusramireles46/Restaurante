module com.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mariadb.java.client;


    opens com.example.restaurante to javafx.fxml;
    exports com.example.restaurante;
}