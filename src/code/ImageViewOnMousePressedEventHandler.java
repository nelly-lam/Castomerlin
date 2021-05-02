package code;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageViewOnMousePressedEventHandler implements EventHandler<MouseEvent> {
	private double pressedX, pressedY;
	private double ancienDeplacementX, ancienDeplacementY;
	
	
    @Override
    public void handle(MouseEvent mouseEvent) {
        pressedX = mouseEvent.getSceneX();
        pressedY = mouseEvent.getSceneY();
            
        ImageView iv = (ImageView) mouseEvent.getSource();
        ancienDeplacementX = iv.getTranslateX();
        ancienDeplacementY = iv.getTranslateY();
            
            //System.out.printf("positionX de iv = %d\n", (int) iv.getLayoutX());
            //eraseObjetSelectionne();
            //viewObjetSelectionne(iv);
            //objetSelectionne.setLayoutX(orgTranslateX);
            //objetSelectionne.setLayoutY(orgTranslateY);
    }
      

  	/**
  	 * @return the pressedX
  	 */
  	public double getPressedX() {
  		return pressedX;
  	}

  	/**
  	 * @param pressedX the pressedX to set
  	 */
  	public void setPressedX(double pressedX) {
  		this.pressedX = pressedX;
  	}

  	/**
  	 * @return the pressedY
  	 */
  	public double getPressedY() {
  		return pressedY;
  	}

  	/**
  	 * @param pressedY the pressedY to set
  	 */
  	public void setPressedY(double pressedY) {
  		this.pressedY = pressedY;
  	}

  	/**
  	 * @return the orgTranslateX
  	 */
  	public double getAncienDeplacementX() {
  		return ancienDeplacementX;
  	}

  	/**
  	 * @param orgTranslateX the orgTranslateX to set
  	 */
  	public void setAncienDeplacementX(double orgTranslateX) {
  		this.ancienDeplacementX = orgTranslateX;
  	}

  	/**
  	 * @return the orgTranslateY
  	 */
  	public double getAncienDeplacementY() {
  		return ancienDeplacementY;
  	}

  	/**
  	 * @param orgTranslateY the orgTranslateY to set
  	 */
  	public void setAncienDeplacementY(double orgTranslateY) {
  		this.ancienDeplacementY = orgTranslateY;
  	}

  	public ImageViewOnMousePressedEventHandler() {
  	}
      
      

        
}
