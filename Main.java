package Tetris;

import Tetris.Controller.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene/Level.fxml"));
        loader.load();

        Level controller = loader.getController();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root, 440, 400);

        scene.getStylesheets().add("Tetris/Style/Tetris.css");
        controller.set(scene).start();

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
