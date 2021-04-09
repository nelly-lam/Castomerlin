package sample;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bienvenue chez CastoMerlin");
        
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() - 60;

        //Group group = new Group();
        //String fxmlpath = "C:\\plugin\\pluginfxml.fxml";
        //Parent root = FXMLLoader.load(Class.getResourceAsStream(fxmlpath));
        //URL fxmlURL = Paths.get("C:\\Users\\nelly\\IdeaProjects\\Castomerlin2\\src\\Files_XML\\kitchenBuilder_03.fxml").toUri().toURL();
        //Parent root = FXMLLoader.load(fxmlURL);
        VBox vbox = (VBox)FXMLLoader.load(getClass().getResource("kitchenBuilder_03.xml"));
        //group.getChildren().add(vbox);

        Scene myScene = new Scene(vbox, width, height);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
