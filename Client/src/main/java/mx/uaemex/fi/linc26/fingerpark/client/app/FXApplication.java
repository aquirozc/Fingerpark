package mx.uaemex.fi.linc26.fingerpark.client.app;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;

public class FXApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(Main.class.getClassLoader().getResourceAsStream("Khula-Light.ttf"),20);
        new MainController(stage);
    }

}
