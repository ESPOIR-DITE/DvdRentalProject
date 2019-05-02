package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("homeUI.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //drag
        root.setOnMousePressed(event ->{
            x = event.getScreenX();
            y = event.getScreenY();

        } );
        root.setOnMouseDragged(event ->{
            primaryStage.setX(event.getScreenX() -x);
            primaryStage.setY(event.getScreenY() -y);
        });


        // Scene scene =new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        // stage.setScene(scene);
        // this.stage=stage;

        // primaryStage.setScene(new Scene(root, 630, 415));
        primaryStage.show();
        //  stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
