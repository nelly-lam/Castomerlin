package code;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ControllerCreation  implements Initializable{

	private Canvas canvas;
	private GraphicsContext gc;
	
	public ControllerCreation(Canvas c) {
		
		this.canvas = c;
		this.gc = canvas.getGraphicsContext2D();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
	}

}
