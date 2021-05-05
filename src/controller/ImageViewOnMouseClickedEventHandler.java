package controller;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ImageViewOnMouseClickedEventHandler implements EventHandler<MouseEvent>{
	private ArrayList<ImageView> imageList, imageListCopy;
	private Pane pane;
	private boolean isFlipped;
	private double orgTranslateX;
	private double orgTranslateY;
	private Rectangle planCuisine;
	
	
	public ImageViewOnMouseClickedEventHandler(Pane p, ArrayList<ImageView> imageList, 
			ArrayList<ImageView> imageListCopy, double orgTranslateX, double orgTranslateY, Rectangle planCuisine) {
		this.pane = p;
		this.imageList = imageList;
		this.setImageListCopy(imageListCopy);
		this.isFlipped = false;
		this.orgTranslateX = orgTranslateX;
		this.orgTranslateY = orgTranslateY;
		this.planCuisine = planCuisine;
	}
	
	
	@Override
    public void handle(MouseEvent mouseEvent) {
        //si c'est un clic droit
    	if(mouseEvent.getButton() == MouseButton.SECONDARY) {
    		addChoices(mouseEvent);
        }
    }
	
	/**
	 * addChoices() : ajoute un contextMenu qui affiche les options de pivot, 
	 * inversement et suppression d'une image
	 * @param e un MouseEvent
	 */
	public void addChoices(MouseEvent e) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem pivoterG = new MenuItem("Pivoter à gauche");
		MenuItem pivoterD = new MenuItem("Pivoter à droite");
		MenuItem retourner = new MenuItem("Retourner");
		MenuItem supprimer = new MenuItem("Supprimer");
		MenuItem annuler = new MenuItem("Annuler");
		contextMenu.getItems().addAll(pivoterG, pivoterD, retourner, supprimer, annuler);
		
		pivoterG.setOnAction(event -> { rotateLeftFurniture(e); });
		pivoterD.setOnAction(event -> { rotateRightFurniture(e); });
		retourner.setOnAction(event -> { flipFurniture(e); });
		supprimer.setOnAction(event -> { removeFurniture(e); });
		annuler.setOnAction(event -> { contextMenu.hide(); });
		
		contextMenu.show(this.pane, e.getScreenX(), e.getScreenY());
	}
	
	/**
	 * rotateLeftFurniture() : pivote vers la gauche le meuble selectionne
	 * @param mouseEvent un MouseEvent
	 */
	public void rotateLeftFurniture(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		imageList.get(i).setRotate(imageList.get(i).getRotate() + 90); 
            }
        }
		collisionPlan(mouseEvent);
	}
	
	/**
	 * rotateRightFurniture() : pivote vers la droite le meuble selectionne
	 * @param mouseEvent un MouseEvent
	 */
	public void rotateRightFurniture(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		imageList.get(i).setRotate(imageList.get(i).getRotate() - 90); 
            }
        }
		collisionPlan(mouseEvent);
	}
	
	
	/**
	 * flipFurniture() : retourne le meuble selectionne
	 * @param mouseEvent un MouseEvent
	 */
	public void flipFurniture(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		if(!isFlipped) {
        			Translate flipTranslation = new Translate(0, imageList.get(i).getImage().getHeight());
        			Rotate flipRotation = new Rotate(180,Rotate.X_AXIS);
        			imageList.get(i).getTransforms().addAll(flipTranslation,flipRotation);
        			imageList.get(i).setRotate(180);
        			isFlipped = true;
        		}else if(isFlipped){
        			imageList.get(i).setRotate(0); 
        			isFlipped = false;
        		}
            }
        }
	}
	
	/**
	 * supprimerObjet() : supprime le meuble selectionne 
	 * de la liste des meubles ajoutes par l'utilisateur
	 * @param mouseEvent un MouseEvent
	 */
	public void removeFurniture(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		ImageView iv = imageList.get(i);
        		removeFromImageList(iv);
        		setImageListCopy(getImageList());
            }
        }
	}
	
	
	/**
	 * collisionPlan() : verifie si le meuble selectionne chevauche d'autres meubles,
	 * si oui le replace a son ancienne position
	 * @param mouseEvent un MouseEvent
	 */
	public void collisionPlan(MouseEvent mouseEvent) {
		ImageView iv = (ImageView)(mouseEvent.getSource());
		
		if(!iv.getBoundsInParent().intersects(planCuisine.getBoundsInParent())) {
			((ImageView)(mouseEvent.getSource())).setTranslateX(this.orgTranslateX);
			((ImageView)(mouseEvent.getSource())).setTranslateY(this.orgTranslateY);
		}else {
		
			ArrayList<ImageView> otherImages = new ArrayList<ImageView>();
		
			for(int i = 0; i < getImageList().size(); i++) {
				if(!iv.equals(getImageList().get(i))) {
					otherImages.add(getImageList().get(i));
				}
			}
			
			for(int i = 0; i < otherImages.size(); i++) {
				if(iv.getBoundsInParent().intersects(otherImages.get(i).getBoundsInParent())) {
					((ImageView)(mouseEvent.getSource())).setTranslateX(this.orgTranslateX);
					((ImageView)(mouseEvent.getSource())).setTranslateY(this.orgTranslateY);
				}
			}
		}
	}
	
    /**
     * removeFromImageList() ; supprime un meuble de la liste des meubles ajoutes 
     * par l'utilisateur
     * @param iv une ImageView
     */
    public void removeFromImageList(ImageView iv) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.remove(iv);
    	this.pane.getChildren().addAll(imageList);
    }

    
    
    /*********************GETTER ET SETTER**********************/
	public ArrayList<ImageView> getImageList() {return imageList;}
	public void setImageList(ArrayList<ImageView> imageList) {this.imageList = imageList;}

	public ArrayList<ImageView> getImageListCopy() {return imageListCopy;}
	public void setImageListCopy(ArrayList<ImageView> imageListCopy) {this.imageListCopy = imageListCopy;}
}
