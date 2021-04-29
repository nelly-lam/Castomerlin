package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bienvenue chez CastoMerlin");
        
		double width = 1280; 
		double height = 720; 
		
		//System.out.printf("width %f\n", width);
		//System.out.printf("height %f\n", height);
		
        Pane hboxPA = (Pane)FXMLLoader.load(getClass().getResource("kitchen_builder_homepage.xml"));
        
        Scene myScenePA = new Scene(hboxPA, width, height);

        primaryStage.setScene(myScenePA);
        primaryStage.show();
    }


    public static void main(String[] args) {
    	launch(args);
    }

}
