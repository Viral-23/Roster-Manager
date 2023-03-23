package tuition.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is a class that runs the GUI.
 * @author Viral Patel, Rohan Patel
 */

public class TuitionManagerMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TuitionManagerMain.class.getResource("TuitionManagerView.fxml"));
        Parent root = fxmlLoader.load();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth() * 0.9;
        double screenHeight = screenBounds.getHeight() * 0.9;

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, screenWidth, screenHeight);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}