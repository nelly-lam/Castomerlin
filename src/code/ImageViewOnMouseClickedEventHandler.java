package code;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ImageViewOnMouseClickedEventHandler implements EventHandler<MouseEvent>{
	private ArrayList<ImageView> imageList, imageListCopy;
	private Pane pane;
	private boolean isFlipped;
	
	
	public ImageViewOnMouseClickedEventHandler(Pane p, ArrayList<ImageView> imageList, ArrayList<ImageView> imageListCopy) {
		this.pane = p;
		this.imageList = imageList;
		this.setImageListCopy(imageListCopy);
		this.isFlipped = false;
	}
	
	
	@Override
    public void handle(MouseEvent mouseEvent) {
        //si c'est un clic droit
    	if(mouseEvent.getButton() == MouseButton.SECONDARY) {
    		addChoices(mouseEvent);
        }
    }
	
	
	public void addChoices(MouseEvent e) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem pivoterG = new MenuItem("Pivoter � gauche");
		MenuItem pivoterD = new MenuItem("Pivoter � droite");
		MenuItem retourner = new MenuItem("Retourner");
		MenuItem supprimer = new MenuItem("Supprimer");
		MenuItem annuler = new MenuItem("Annuler");
		contextMenu.getItems().addAll(pivoterG, pivoterD, retourner, supprimer, annuler);
		
		pivoterG.setOnAction(event -> { pivoterGObjet(e); });
		pivoterD.setOnAction(event -> { pivoterDObjet(e); });
		retourner.setOnAction(event -> { flipObject(e); });
		supprimer.setOnAction(event -> { supprimerObjet(e); });
		annuler.setOnAction(event -> { contextMenu.hide(); });
		
		contextMenu.show(this.pane, e.getScreenX(), e.getScreenY());
	}
	
	
	
	
	public void supprimerObjet(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		ImageView iv = imageList.get(i);
        		removeFromImageList(iv);
        		setImageListCopy(getImageList());
            }
        }
		//System.out.print("clic droit size ImageList = "+ imageList.size() + "\n");
	}
	
	
	public void pivoterGObjet(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		imageList.get(i).setRotate(imageList.get(i).getRotate() + 90); 
            }
        }
	}
	
	public void pivoterDObjet(MouseEvent mouseEvent) {
		for(int i = 0; i<imageList.size(); i++) {
        	if(mouseEvent.getSource().equals(imageList.get(i))){
        		imageList.get(i).setRotate(imageList.get(i).getRotate() - 90); 
            }
        }
	}
	
	
	public void flipObject(MouseEvent mouseEvent) {
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
     * removeFromImageList() ; supprime une imageView de la liste imageList puis cette liste de la pane
     * @param iv une ImageView
     */
    public void removeFromImageList(ImageView iv) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.remove(iv);
    	this.pane.getChildren().addAll(imageList);
    }

	/**
	 * @return the imageList
	 */
	public ArrayList<ImageView> getImageList() {
		return imageList;
	}

	/**
	 * @param imageList the imageList to set
	 */
	public void setImageList(ArrayList<ImageView> imageList) {
		this.imageList = imageList;
	}

	public ArrayList<ImageView> getImageListCopy() {
		return imageListCopy;
	}

	public void setImageListCopy(ArrayList<ImageView> imageListCopy) {
		this.imageListCopy = imageListCopy;
	}
}
