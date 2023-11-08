package com.example.restaurante.componentes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Toast {

    public static void show(Stage ownerStage, String message, double x, double y) {
        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1, 1);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);

        Label label = new Label(message);
        label.setFont(Font.font(18));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 5px;");
        label.setMinWidth(250);
        label.setAlignment(Pos.CENTER);

        pane.getChildren().add(label);

        label.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            toastStage.setWidth(newValue.getWidth() + 20);
            toastStage.setHeight(newValue.getHeight() + 20);
        });

        toastStage.setX(x);
        toastStage.setY(y);

        toastStage.show();

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.5),
                event -> toastStage.close()
        );

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }
}
