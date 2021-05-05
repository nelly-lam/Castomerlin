package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bienvenue chez CastoMerlin");
        
		double width = 1280; 
		double height = 720; 
		
        Pane pane = (Pane)FXMLLoader.load(getClass().getResource("kitchen_builder_homepage.xml"));
        Scene myScenePA = new Scene(pane, width, height);

        primaryStage.setScene(myScenePA);
        primaryStage.show();
    }


    public static void main(String[] args) {
    	launch(args);
    }

}
