module com.example.restaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mariadb.java.client;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;

    opens com.example.restaurante to javafx.fxml;
    opens com.example.restaurante.modelo to javafx.base;
    exports com.example.restaurante;
}