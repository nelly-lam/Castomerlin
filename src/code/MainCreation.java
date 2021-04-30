package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainCreation extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("CastoMerlin - création de votre plan de cuisine");
		HBox hboxPA = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_creation.xml"));

		//Group group = new Group();
		// Create the Canvas
        Canvas canvas = new Canvas();
        canvas.setWidth(400);
        canvas.setHeight(200);
        
        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.setFill(Color.BLACK);
        //gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
		
        hboxPA.getChildren().add(canvas);
        //Scene myScenePA = new Scene(group, 1280, 720);
		Scene myScenePA = new Scene(hboxPA, 1280, 720);
        primaryStage.setScene(myScenePA);
        primaryStage.show();
	}
	
    public static void main(String[] args) {
        launch(args);
    }

}
