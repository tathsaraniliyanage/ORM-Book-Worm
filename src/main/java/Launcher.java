import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

           Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/LoginFrom.fxml"));
            Scene scene = new Scene(rootNode);
//            stage.getIcons().addAll(new Image("/view/loginForm.fxml"));
            stage.setTitle("Book Worm");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

    }
}
