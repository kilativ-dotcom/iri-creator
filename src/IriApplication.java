import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainScreen;


public class IriApplication extends Application {

    public static void requestLaunch(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        MainScreen mainScreen = new MainScreen();
        Scene currentScene = new Scene(mainScreen, 700, 400);
        primaryStage.setScene(currentScene);
        primaryStage.setTitle("Iri Application");
        primaryStage.show();
    }
}
