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
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() - 60;
		
		System.out.printf("width %f\n", width);
		System.out.printf("height %f\n", height);
		
        //Group group = new Group();
        //String fxmlpath = "C:\\plugin\\pluginfxml.fxml";
        //Parent root = FXMLLoader.load(Class.getResourceAsStream(fxmlpath));
        //URL fxmlURL = Paths.get("C:\\Users\\nelly\\IdeaProjects\\Castomerlin2\\src\\Files_XML\\kitchenBuilder_03.fxml").toUri().toURL();
        //Parent root = FXMLLoader.load(fxmlURL);
		
        HBox hboxPA = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_homepage.xml"));
        //group.getChildren().add(vbox);
        

        Scene myScenePA = new Scene(hboxPA, width, height);
        
        //Charge une ImageView
        //ImageView imv = (ImageView)FXMLLoader.load(getClass().getResource("kitchenBuilder_03.xml"));
        //ajout dans vbox
        //vbox.getChildren().add(imv);
        
        //Creation
        //Image image = new Image(Main.class.getResourceAsStream("image.png"));
        //imv.setImage(image);

        //imv.fitWidthProperty().bind(myScene.widthProperty());
        //imv.fitHeightProperty().bind(myScene.heightProperty());

        primaryStage.setScene(myScenePA);
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
