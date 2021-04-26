package code;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PageDimension extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CastoMerlin - dimensions de votre cuisine");
		
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() - 60;
		
        HBox hbox = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_dimensions.xml"));

        Scene myScene = new Scene(hbox, width, height);
        
        primaryStage.setScene(myScene);
        primaryStage.show();
		
	}
	
}
