package code;

import java.awt.Dimension;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bienvenue chez CastoMerlin");
        
		double width = 1280; 
		double height = 720; 
		
		//System.out.printf("width %f\n", width);
		//System.out.printf("height %f\n", height);
		
        HBox hboxPA = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_homepage.xml"));
        Scene myScenePA = new Scene(hboxPA, width, height);

        primaryStage.setScene(myScenePA);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
