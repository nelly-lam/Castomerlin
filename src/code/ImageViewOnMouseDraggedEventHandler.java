package code;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageViewOnMouseDraggedEventHandler implements EventHandler<MouseEvent>{
	private double pressedX, pressedY;
	private double abscissePlan, ordonneePlan;
	private int longueurPlan, largeurPlan;
	private double ancienDeplacementX, ancienDeplacementY;
	
	public ImageViewOnMouseDraggedEventHandler(double x, double y, double abs, double ord, int longueur, int largeur, double orgTranslateX, double orgTranslateY) {
		pressedX = x;
		pressedY = y;
		abscissePlan = abs;
		ordonneePlan = ord;
		longueurPlan = longueur;
		largeurPlan = largeur;
		this.setAncienDeplacementX(orgTranslateX);
		this.setAncienDeplacementY(orgTranslateY);
	}
	
	 @Override
     public void handle(MouseEvent mouseEvent) {
         double deplacementX = mouseEvent.getSceneX() - pressedX;
         double deplacementY = mouseEvent.getSceneY() - pressedY;
         
         ImageView iv = (ImageView) mouseEvent.getSource();
         //eraseObjetSelectionne();
         
         //TODO
         if(abscissePlan < mouseEvent.getSceneX() && mouseEvent.getSceneX() < abscissePlan+longueurPlan) {
         	double newTranslateX = ancienDeplacementX + deplacementX;
         	iv.setTranslateX(newTranslateX);
         }
         if(ordonneePlan < mouseEvent.getSceneY() && mouseEvent.getSceneY() < ordonneePlan+largeurPlan) {
         	double newTranslateY = ancienDeplacementY + deplacementY;
         	iv.setTranslateY(newTranslateY);
         }
         
         System.out.printf("positionTranslateX de iv = %d\n", (int) iv.getTranslateX());
         //putObjetSelectionne(iv);
     }

	public double getOrdonneePlan() {
		return ordonneePlan;
	}

	public void setOrdonneePlan(double ordonneePlan) {
		this.ordonneePlan = ordonneePlan;
	}

	public double getAbscissePlan() {
		return abscissePlan;
	}

	public void setAbscissePlan(double abscissePlan) {
		this.abscissePlan = abscissePlan;
	}

	public int getLargeurPlan() {
		return largeurPlan;
	}

	public void setLargeurPlan(int largeurPlan) {
		this.largeurPlan = largeurPlan;
	}

	public int getLongueurPlan() {
		return longueurPlan;
	}

	public void setLongueurPlan(int longueurPlan) {
		this.longueurPlan = longueurPlan;
	}

	public double getAncienDeplacementX() {
		return ancienDeplacementX;
	}

	public void setAncienDeplacementX(double ancienDeplacementX) {
		this.ancienDeplacementX = ancienDeplacementX;
	}

	public double getAncienDeplacementY() {
		return ancienDeplacementY;
	}

	public void setAncienDeplacementY(double ancienDeplacementY) {
		this.ancienDeplacementY = ancienDeplacementY;
	}

}
