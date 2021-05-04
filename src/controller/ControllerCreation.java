package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerCreation{

	//enregistre les objets ajoutes
	private ArrayList<ImageView> imageList = new ArrayList<ImageView>();
	//copie de la liste des objets ajoutes pour les actions refaire et defaire
	private ArrayList<ImageView> imageListCopy = new ArrayList<ImageView>();

	//private ArrayList<Rectangle> furnitureHitboxList = new ArrayList<Rectangle>();
	
	//private ArrayList<ObjetCuisine> listeObjetCuisine = new ArrayList<ObjetCuisine>();
	
	private double echelle;
	//@FXML private Line longueurSurPlanTrait;
	@FXML private Label longueurSurPlan;
	//@FXML private Line largeurSurPlanTrait;
	@FXML private Label largeurSurPlan;
	@FXML private Line mesure50cmTrait;
	@FXML private Label mesure50cm;
	
	private Scene scene;
	private Stage stage;
	private Pane pane;
	private double width;
	private double height;
	
	//pour le deplacement des objets
    //private double curseurDebutX, curseurDebutY;
    private double orgTranslateX, orgTranslateY;

	private double orgSceneX, orgSceneY;
	
	@FXML private Button defaire;
	@FXML private Button refaire;
	@FXML private Button sauvegarder;
	@FXML private Button recommencer;
	
	//le plan sur lequel on concoit la cuisine
	@FXML private Rectangle planCuisine;
	
	@FXML private ImageView frame;
	@FXML private Label indication;
	
	//pour dessiner un liseret rouge sur l'objet courant
	private Rectangle objetSelectionne;
	
	//boutons du mobilier de cuisine
	@FXML private Button addEvier1;
	@FXML private Button addEvier2;
	@FXML private Button addEvier3;
	@FXML private Button addEvier4;
	@FXML private Button addFrigo1;
	@FXML private Button addCounter1;
	@FXML private Button addCounter2;
	@FXML private Button addFour1;
	@FXML private Button addFour2;
	@FXML private Button addFour3;
	@FXML private Button addFour4;
	
	@FXML private Button addPorte;
	@FXML private Button addFenetre;
	
	//components de la page de sauvegarde
	@FXML private TextField email;
	@FXML private Button envoyer;
	@FXML private Button annulerEnvoie;
	

	
	/**
	 * getObjetCuisineWithImageView() : recupere un objet a partir de son imageView
	 * @param iv
	 * @return
	 */
	/*public ObjetCuisine getObjetCuisineWithImageView(ImageView iv) {
		ObjetCuisine obj = null;
		for(int i = 0; i < getListeObjetCuisine().size(); i++) {
			if(getListeObjetCuisine().get(i).getIv() == iv) {
					obj = getListeObjetCuisine().get(i);
			}
		}
		return obj;
	}*/
	
	/**
	 * closeCurrentWindow() : ferme une fenetre qu'on recupere grace a l'emplacement d'un bouton
	 * @param b un Button situe sur la fenetre qu'on veut fermer
	 */
    protected void closeCurrentWindow(Button b) {
    	Stage currentWindow = (Stage) b.getScene().getWindow();
        currentWindow.close();
    }
	
    
    /**
     * ajouterPorteFenetre() : ajoute l'image correspondant a une porte ou une fenetre au plan
     * @param e un MouseEvent, ici un clic de souris sur des boutons
     */
    @FXML
    protected void ajouterPorteFenetre(MouseEvent e) {
    	//insertion d'une ImageView dans le coin haut gauche du plan
    	ImageView iv = new ImageView();
    	iv.setLayoutX(getLayoutXPlanCuisine());
    	iv.setLayoutY(getLayoutYPlanCuisine());
    	
    	Button b = ((Button)e.getSource());
    	
    	if(b.equals(addFenetre)) {
        	Image image = new Image("photos/window.png", 100*getEchelle(), 15*getEchelle(), false, false);
        	iv.setImage(image);
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/window.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());
    	}else if(b.equals(addPorte)) {
    		Image image = new Image("photos/door.png", 70*getEchelle(), 70*getEchelle(), false, false);
        	iv.setImage(image);
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/door.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());
    	}

    	addToImageList(iv);
    	
    	//on copie la liste des objets dans la liste "copie"
    	setImageListCopy(getImageList());
    	
    	iv.setOnMousePressed(pressEvent);
    	iv.setOnMouseDragged(dragEvent);
        iv.setOnMouseReleased(releaseEvent);
        
    	ImageViewOnMouseClickedEventHandler imageViewClickEvent = new ImageViewOnMouseClickedEventHandler(getPane(), getImageList(), getImageListCopy(),
    			orgTranslateX, orgTranslateY, getPlanCuisine());
        iv.setOnMouseClicked(imageViewClickEvent);
        

    }
    
    
    /**
     * ajouterObjet() : ajoute l'image correspondante a l'objet qu'on veut ajouter dans le plan
     * @param e un MouseEvent, ici un clic de souris sur des boutons
     * @throws IOException
     */
	@SuppressWarnings("null")
	@FXML
    protected void ajouterObjet(MouseEvent e) throws IOException{
		//ObjetCuisine obj = null;
		//String tex; 
		//int sizeX, sizeY;
		
    	//insertion d'une ImageView dans le coin haut gauche du plan
    	ImageView iv = new ImageView();
    	iv.setLayoutX(getLayoutXPlanCuisine()+10);
    	iv.setLayoutY(getLayoutYPlanCuisine()+10);
    	
    	//implementation d'un rectangle de la meme dimension et position que l'imageView iv
    	//alias de l'imageView
    	//Rectangle hitbox = new Rectangle();
    	//hitbox.setX(getLayoutXPlanCuisine()+10);
    	//hitbox.setY(getLayoutYPlanCuisine()+10);
        
    	@SuppressWarnings("unused")
    	Button b = ((Button)e.getSource());
		/*
		switch(b) {
			case addEvier1:
				tex = "photos/evier_gris.png";
				sizeX= 120;
				sizeY = 60;
				break;
			case addEvier2:
				tex = "photos/evier_inox.png";
				sizeX= 120;
				sizeY = 60;
				break;
			case addEvier3:
				tex = "photos/evier_marbre.png";
				sizeX= 120;
				sizeY = 60;
				break;
			case addEvier4:
				tex = "photos/evier_sable.png";
				sizeX= 120;
				sizeY = 60;
				break;
			case addCounter1:
				tex = "photos/counter_marbreBlanc.png";
				sizeX= 60;
				sizeY = 60;
				break;
			case addCounter2:
				tex = "photos/counter_marbreNoir.png";
				sizeX= 60;
				sizeY = 60;
				break;
			case addFour1:
				tex = "photos/four_gris.png";
				sizeX= 70;
				sizeY = 60;
				break;
			case addFour2:
				tex = "photos/four_inox.png";
				sizeX= 70;
				sizeY = 60;
				break;
			case addFour3:
				tex = "photos/four_marbre.png";
				sizeX= 70;
				sizeY = 60;
				break;
			case addFour4:
				tex = "photos/four_sable.png";
				sizeX= 70;
				sizeY = 60;
				break;
			case addFrigo1:
				tex = "photos/evier_gris.png";
				sizeX= 60;
				sizeY = 75;
				break;
		}
		
		Image image = new Image(tex, sizeX*getEchelle(), sizeY*getEchelle(), false, false);
		iv.setImage(image);
		obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, tex, new Point((int)iv.getLayoutX(),
								(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

		
		
		*/

		if(b.equals(addEvier1)) {
        	Image image = new Image("photos/evier_gris.png", 120*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(120*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/evier_gris.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());
    	
    	}else if(b.equals(addEvier2)) {
        	Image image = new Image("photos/evier_inox.png", 120*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(120*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/evier_inox.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());
    	
    	}else if(b.equals(addEvier3)) {
        	Image image = new Image("photos/evier_marbre.png", 120*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(120*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/evier_marbre.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addEvier4)) {
        	Image image = new Image("photos/evier_sable.png", 120*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(120*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/evier_sable.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addCounter1)) {
        	Image image = new Image("photos/counter_marbreBlanc.png", 60*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(60*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/counter_marbreBlanc.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addCounter2)) {
        	Image image = new Image("photos/counter_marbreNoir.png", 60*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(60*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/counter_marbreNoir.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addFour1)) {
        	Image image = new Image("photos/four_gris.png", 70*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(70*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/four_gris.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addFour2)) {
        	Image image = new Image("photos/four_inox.png", 70*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(70*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/four_inox.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addFour3)) {
        	Image image = new Image("photos/four_marbre.png",70*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(70*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/four_marbre.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addFour4)) {
        	Image image = new Image("photos/four_sable.png", 70*getEchelle(), 60*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(70*getEchelle(), 60*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/four_sable.png", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());

    	}else if(b.equals(addFrigo1)) {
        	Image image = new Image("photos/frigo.jpg", 60*getEchelle(), 75*getEchelle(), false, false);
        	iv.setImage(image);
        	//hitbox = new Rectangle(60*getEchelle(), 75*getEchelle());
    		//obj = new ObjetCuisine(getListeObjetCuisine().size(), iv, "photos/frigo.jpg", new Point((int)iv.getLayoutX(),(int)iv.getLayoutY()), (int) image.getWidth(), (int) image.getHeight());
    	}

		
    	addToImageList(iv);
    	//getFurnitureHitboxList().add(hitbox);
    	//this.pane.getChildren().addAll(hitbox);
    	
    	//on copie la liste des objets dans la liste "copie"
    	setImageListCopy(getImageList());
    	
    	//ajout de cet objet dans la liste des objets de cuisine
		//getListeObjetCuisine().add(obj);
    	
    	iv.setOnMousePressed(pressEvent);
    	iv.setOnMouseDragged(dragEvent);
        iv.setOnMouseReleased(releaseEvent);
        
    	//pour la suppression de l'imageView
    	ImageViewOnMouseClickedEventHandler imageViewClickEvent = new ImageViewOnMouseClickedEventHandler(
			getPane(), getImageList(), getImageListCopy(), getOrgTranslateX(), getOrgTranslateY(), getPlanCuisine());
        iv.setOnMouseClicked(imageViewClickEvent);
        
        indication.setTextFill(Color.color(0, 0, 0));
        indication.setText("Vous pouvez pivoter, inverser et supprimer vos meubles avec un clic droit.");
 
    }

	EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((ImageView)(mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((ImageView) (mouseEvent.getSource())).getTranslateY();
            
        }
    };
    
    EventHandler<MouseEvent> dragEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            double offsetX = mouseEvent.getSceneX() - orgSceneX;
            double offsetY = mouseEvent.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((ImageView)(mouseEvent.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(mouseEvent.getSource())).setTranslateY(newTranslateY);

        }
    };

	
    EventHandler<MouseEvent> releaseEvent = new EventHandler<MouseEvent>(){
    	@Override
        public void handle(MouseEvent mouseEvent) {
    		ImageView iv = (ImageView) mouseEvent.getSource();
    		
    		// distanceX = distance en abscisse entre le clic de la souris et le point haut gauche du rectangle correspondant a l'imageView
    		//double distanceX = orgSceneX - getObjetCuisineWithImageView(iv).getHautGauche().getX();
    		//double distanceY = orgSceneY - getObjetCuisineWithImageView(iv).getHautGauche().getY();
    		
    		//double distanceX = orgSceneX - iv.getLayoutX();
    		//double distanceY = orgSceneY - iv.getLayoutY();
    		
    		//Rectangle newRectangle = new Rectangle(iv.getImage().getWidth(),iv.getImage().getHeight());
    		//newRectangle.setX(mouseEvent.getSceneX() - distanceX);
    		//newRectangle.setY(mouseEvent.getSceneY() - distanceY);   
            
    		//addToFurnitureHitboxList(newRectangle);
    		
    		//getObjetCuisineWithImageView(iv).setZone(newRectangle);
            
    		//Shape.intersect(newRectangle, static_bloc);
    		/*
    		for(int i = 0; i < getFurnitureHitboxList().size(); i++) {
    			if (newRectangle.getBoundsInLocal().intersects(getFurnitureHitboxList().get(i).getBoundsInLocal())) {
    				((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    	            ((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);
    			}else {
    				//((ImageView)(mouseEvent.getSource())).setLayoutX(newRectangle.getX());
    				//((ImageView)(mouseEvent.getSource())).setLayoutY(newRectangle.getY());
    				getFurnitureHitboxList().get(i).setLayoutX(newRectangle.getX());
    				getFurnitureHitboxList().get(i).setLayoutY(newRectangle.getY());
    			}
    		}*/

    		if(!iv.getBoundsInParent().intersects(planCuisine.getBoundsInParent())) {
    			((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    			((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);
    			indication.setTextFill(Color.color(1, 0, 0));
    			indication.setText("Les meubles ne doivent pas dépasser le plan!");
    		}else {
    		
    			//System.out.printf("getImageList().size() = %d\n", getImageList().size());
    			ArrayList<ImageView> otherImages = new ArrayList<ImageView>();
    		
    			for(int i = 0; i < getImageList().size(); i++) {
    				if(!iv.equals(getImageList().get(i))) {
    					otherImages.add(getImageList().get(i));
    				}
    			}
    			
    			for(int i = 0; i < otherImages.size(); i++) {
    				if(iv.getBoundsInParent().intersects(otherImages.get(i).getBoundsInParent())) {
    					//System.out.print("collision!\n");
    					((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    					((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);
    					indication.setTextFill(Color.color(1, 0, 0));
    					indication.setText("Les meubles ne doivent pas se chevaucher!");
    				}
    			}
    		}
    	}
    };
    
    
    public void eraseObjetSelectionne() {
        if(getObjetSelectionne() != null) {
    		this.pane.getChildren().remove(getObjetSelectionne());
    	}
    }
    
    public void putObjetSelectionne(ImageView iv) {
    	if(getObjetSelectionne() != null) {
    		this.pane.getChildren().remove(getObjetSelectionne());
    	}
    	Rectangle rect = new Rectangle(iv.getImage().getWidth()+6, iv.getImage().getHeight()+6, Color.RED);
    	rect.setLayoutX(iv.getLayoutX()-3);
    	rect.setLayoutY(iv.getLayoutY()-3);
    	setObjetSelectionne(rect);
    	
    	this.pane.getChildren().add(getObjetSelectionne());
    }
    
    /**
     * addToImageList() : ajoute une imageView dans la liste imageList puis cette liste dans le pane
     * @param iv une ImageView
     */
    public void addToImageList(ImageView iv) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.add(iv);
    	this.pane.getChildren().addAll(imageList);
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
     * 
     * @param hitbox
     */
    /*public void addToFurnitureHitboxList(Rectangle hitbox) {
    	this.pane.getChildren().removeAll(furnitureHitboxList);
    	furnitureHitboxList.add(hitbox);
    	this.pane.getChildren().addAll(furnitureHitboxList);
    }*/
    
    /**
     * 
     * @param hitbox
     */
    /*public void removeFromFurnitureHitboxList(Rectangle hitbox) {
    	this.pane.getChildren().removeAll(furnitureHitboxList);
    	furnitureHitboxList.remove(hitbox);
    	this.pane.getChildren().addAll(furnitureHitboxList);
    }*/
    
    
	/*
	 * defaireObjet : annule la derniere action
	 */
    @FXML
    protected void defaireObjet() {
    	
    }
    
    
    @FXML
    protected void refaireObjet() {
    	
    }
    
    @FXML
    protected void sauvegarderPlan() throws IOException {
		HBox hboxD = (HBox)FXMLLoader.load(getClass().getResource("/code/kitchen_builder_save.xml"));
		
        Stage stageD = new Stage();
        stageD.setTitle("CastoMerlin - sauvegarde de votre plan de cuisine");
        
        Scene sceneD = new Scene(hboxD, 470, 300);
        
        stageD.setScene(sceneD);
        stageD.show();
    }

    
    @FXML
    protected void annulerEnvoiePlan() {
    	closeCurrentWindow(annulerEnvoie);
    }
    
    @FXML
    protected void recommencerPlan() {
    	
    }



	/*****************POUR DIMENSIONNER LE PLAN DE LA CUISINE******************/
	public int getLayoutXPlanCuisine() { return (int) this.planCuisine.getLayoutX(); }
	public int getLayoutYPlanCuisine() { return (int) this.planCuisine.getLayoutY(); }
	
	public void setLayoutXPlanCuisine(int d) {this.planCuisine.setLayoutX(d);}
	public void setLayoutYPlanCuisine(int d) {this.planCuisine.setLayoutY(d);}
	
	public int getHeightPlanCuisine() { return (int) this.planCuisine.getHeight(); }
	public int getWidthPlanCuisine() { return (int) this.planCuisine.getWidth(); }
	
	public void setHeightPlanCuisine(int d) {this.planCuisine.setHeight(d);}
	public void setWidthPlanCuisine(int d) {this.planCuisine.setWidth(d);}
	
	
	/*****************POUR RECUPERER L'ECHELLE******************/
	public double getEchelle() {return echelle;}
	public void setEchelle(double echelle) {this.echelle = echelle;}
	public void setLongueurSurPlan(String s) { this.longueurSurPlan.setText(s); }
	public void setLargeurSurPlan(String s) { this.largeurSurPlan.setText(s); }
	
	public void setLayoutYMesure50cmTrait(int ord) { 
		this.mesure50cmTrait.setStartY(ord+getLayoutYPlanCuisine()); 
		this.mesure50cmTrait.setEndY(ord+getLayoutYPlanCuisine()); 
	}
	public void setWidthmesure50cmTrait(double width) { 
		this.mesure50cmTrait.setStartX(this.mesure50cmTrait.getEndX()+width);
	}
	public void setLayoutYMesure50cm(int ord ) { this.mesure50cm.setLayoutY(ord+getLayoutYPlanCuisine()); }
	public void setMesure50cm(String txt) { this.mesure50cm.setText(txt); }


	/*********************GETTER ET SETTER**********************/
	public double getWidth() {return width;}
	public void setWidth(double width) {this.width = width;}

	public double getHeight() {return height;}
	public void setHeight(double height) {this.height = height;}

	public ArrayList<ImageView> getImageList() {return imageList;}
	public void setImageList(ArrayList<ImageView> imageList) {this.imageList = imageList;}

	public Scene getScene() {return scene;}
	public void setScene(Scene scene) {this.scene = scene;}

	public Stage getStage() {return stage;}
	public void setStage(Stage stage) {this.stage = stage;}

	public void setPane(Pane pane) {this.pane = pane;}
	public Pane getPane() {return pane;}
	
	public Rectangle getPlanCuisine() {return planCuisine;}
	public void setPlanCuisine(Rectangle planCuisine) {this.planCuisine = planCuisine;}

	public ArrayList<ImageView> getImageListCopy() {return imageListCopy;}
	public void setImageListCopy(ArrayList<ImageView> imageListCopy) {this.imageListCopy = imageListCopy;}
	
	public Rectangle getObjetSelectionne() {return objetSelectionne;}
	public void setObjetSelectionne(Rectangle objetSelectionne) {this.objetSelectionne = objetSelectionne;}

	//public ArrayList<ObjetCuisine> getListeObjetCuisine() {return listeObjetCuisine;}
	//public void setListeObjetCuisine(ArrayList<ObjetCuisine> listeObjet) {this.listeObjetCuisine = listeObjet;}

	public double getOrgTranslateX() {return orgTranslateX;}
	public void setOrgTranslateX(double orgTranslateX) {this.orgTranslateX = orgTranslateX;}

	public double getOrgTranslateY() {return orgTranslateY;}
	public void setOrgTranslateY(double orgTranslateY) {this.orgTranslateY = orgTranslateY;}

	//public ArrayList<Rectangle> getFurnitureHitboxList() {return furnitureHitboxList;}
	//public void setFurnitureHitboxList(ArrayList<Rectangle> furnitureHitboxList) {this.furnitureHitboxList = furnitureHitboxList;}



}
