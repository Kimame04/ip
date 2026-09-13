package jiji.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jiji.Jiji;

/**
 * A GUI for Jiji using FXML.
 */
public class Main extends Application {

    private final Jiji jiji = new Jiji();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jiji - Personal Assistant");
            stage.setMinHeight(400);
            stage.setMinWidth(420);
            stage.setWidth(450);
            stage.setHeight(650);
            fxmlLoader.<MainWindow>getController().setJiji(jiji);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
