package code;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ImageViewOnMouseClickedEventHandler implements EventHandler<MouseEvent>{
	private ArrayList<ImageView> imageList, imageListCopy;
	private Pane pane;
	
	
	public ImageViewOnMouseClickedEventHandler(Pane p, ArrayList<ImageView> imageList, ArrayList<ImageView> imageListCopy) {
		this.pane = p;
		this.imageList = imageList;
		this.setImageListCopy(imageListCopy);
	}
	
	@Override
    public void handle(MouseEvent mouseEvent) {

        //si c'est un clic droit
    	if(mouseEvent.getButton() == MouseButton.SECONDARY) {
    		//System.out.print("ici\n");
        	for(int i = 0; i<imageList.size(); i++) {
            	if(mouseEvent.getSource().equals(imageList.get(i))){
            		ImageView iv = imageList.get(i);
            		removeFromImageList(iv);
            		setImageListCopy(getImageList());
                }
            }
         }
         System.out.print("clic droit size ImageList = "+ imageList.size() + "\n");
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
