package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	/*Pour enregistrer les meubles ajoutes*/
	private ArrayList<ImageView> imageList = new ArrayList<>();

	/*Copie de la liste des meubles ajoutes pour les actions refaire et defaire*/
	private int historyMaxCapacity = 20;
	private ArrayList<ArrayList<ImageView>> history = new ArrayList<ArrayList<ImageView>>(historyMaxCapacity);

	/*Pour l'affichage de l'echelle sur le plan de la cuisine*/
	private double echelle;
	@FXML private Label longueurSurPlan;
	@FXML private Label largeurSurPlan;
	@FXML private Line mesure50cmTrait;
	@FXML private Label mesure50cm;

	private Scene scene;
	private Stage stage;
	private Pane pane;
	private double width;
	private double height;

	/*Pour le deplacement des objets*/
    private double orgTranslateX, orgTranslateY;

	private double orgSceneX, orgSceneY;

	@FXML private Button defaire;
	@FXML private Button refaire;
	@FXML private Button sauvegarder;
	@FXML private Button recommencer;

	/*Le plan sur lequel on concoit la cuisine*/
	@FXML private Rectangle planCuisine;
	@FXML private Line topPlanCuisine;
	@FXML private Line leftPlanCuisine;
	@FXML private Line rightPlanCuisine;
	@FXML private Line bottomPlanCuisine;

	@FXML private ImageView frame;

	/*Pour laisser un message a l'utilisateur*/
	@FXML private Label indication;

	/*pour dessiner un liseret rouge sur l'objet courant*/
	private Rectangle objetSelectionne;

	/*boutons du mobilier de cuisine*/
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

	/*components de la page de sauvegarde*/
	@FXML private TextField email;
	@FXML private Button envoyer;
	@FXML private Button annulerEnvoi;


	/***********************************METHODES*********************************/

	/**
	 * closeCurrentWindow() : ferme une fenetre qu'on recupere grace a l'emplacement d'un bouton
	 * @param b un Button situe sur la fenetre qu'on veut fermer
	 */
    protected void closeCurrentWindow(Button b) {
    	Stage currentWindow = (Stage) b.getScene().getWindow();
        currentWindow.close();
    }

    
    /**
     * ajouterObjet() : ajoute l'image correspondante a l'objet qu'on veut ajouter dans le plan
     * @param e un MouseEvent, ici un clic de souris sur des boutons
     * @throws IOException
     */
	@SuppressWarnings("null")
	@FXML
    protected void ajouterObjet(MouseEvent e) throws IOException{
    	//insertion d'une ImageView dans le coin haut gauche du plan
    	ImageView iv = new ImageView();
    	iv.setLayoutX(getLayoutXPlanCuisine()+10);
    	iv.setLayoutY(getLayoutYPlanCuisine()+10);

    	@SuppressWarnings("unused")
    	Button b = ((Button)e.getSource());
		String btnName = b.getId();
		String tex = getTex(btnName);
		int[] size = getTexSize(btnName);
		int sizeX = size[0];
		int sizeY = size[1];
		Image image = new Image(tex, sizeX*this.echelle, sizeY*this.echelle, false, false);
		iv.setImage(image);

    	addToImageList(iv);

    	/*Pour le deplacement des meubles*/
    	iv.setOnMousePressed(pressEvent);
    	iv.setOnMouseDragged(dragEvent);
        iv.setOnMouseReleased(releaseEvent);
        /*Pour la rotation et suppression des meubles*/
    	ImageViewOnMouseClickedEventHandler imageViewClickEvent = new ImageViewOnMouseClickedEventHandler(
												this.pane, this.imageList, this.orgTranslateX,
												this.orgTranslateY, this.planCuisine);
        iv.setOnMouseClicked(imageViewClickEvent);
        /*Pour informer l'utilisateur*/
        indication.setTextFill(Color.color(0, 0, 0));
        indication.setText("Vous pouvez pivoter, inverser et supprimer vos meubles avec un clic droit.");
		//addHistoryEvent();
    }

    /**
     * addToImageList() : ajoute une imageView dans la liste imageList
     * puis cette liste dans le pane
     * @param iv une ImageView
     */
    public void addToImageList(ImageView iv) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.add(iv);
    	this.pane.getChildren().addAll(imageList);
    }

    /**
     * removeFromImageList() : supprime une imageView de la liste imageList
     * puis cette liste de la pane
     * @param iv une ImageView
     */
    public void removeFromImageList(ImageView iv) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.remove(iv);
    	this.pane.getChildren().addAll(imageList);
    }

    /**
     * putImageViewInFront() : met une imageView devant tous les autres components
     * qui sont ajoutes a la pane de la fenetre de creation
     * @param imageView l'imageView qu'on veut mettre en premier plan
     */
    public void putImageViewInFront(ImageView imageView) {
    	this.pane.getChildren().removeAll(imageList);
    	imageList.remove(imageView);
    	imageList.add(imageView);
    	this.pane.getChildren().addAll(imageList);
    }

	/**
	 * addHistoryEvent: Ajoute un element a la pile d'historique
	 */
    protected void addHistoryEvent(){
		history.add(0, imageList);
		if (history.size() > historyMaxCapacity){
			history.remove(history.size()-1);
		}
	}

	/*
	 * defaireObjet : annule la derniere action
	 */
    @FXML
    protected void defaireObjet(MouseEvent e) {
		if(history.size()>0){
			this.pane.getChildren().removeAll(imageList);
			this.imageList = history.get(0);
			history.remove(0);
			this.pane.getChildren().addAll(imageList);
		} else {
			indication.setTextFill(Color.color(1, 0, 0));
			indication.setText("Plus rien a annuler!");
		}
    }

	//Non implementee
    @FXML
    protected void refaireObjet() {
    	//
    }

    /**
     * sauvegarderPlan() : ouvre une autre fenetre de sauvegarde du plan
     * @throws IOException
     */
    @FXML
    protected void sauvegarderPlan() throws IOException {
		HBox hboxD = (HBox)FXMLLoader.load(getClass().getResource("/code/kitchen_builder_save.xml"));

        Stage stageD = new Stage();
        stageD.setTitle("CastoMerlin - sauvegarde de votre plan de cuisine");

        Scene sceneD = new Scene(hboxD, 470, 300);

        stageD.setScene(sceneD);
        stageD.show();
    }

    /*
	* annulerEnvoiPlan
	*/
    @FXML
    protected void annulerEnvoiPlan() {
    	closeCurrentWindow(annulerEnvoi);
    }


	/*****************EVENTS******************/

	/**
	 * pressEvent : controleur
	 */
	EventHandler<MouseEvent> pressEvent = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent mouseEvent) {
            orgSceneX = mouseEvent.getSceneX();
            orgSceneY = mouseEvent.getSceneY();
            orgTranslateX = ((ImageView)(mouseEvent.getSource())).getTranslateX();
            orgTranslateY = ((ImageView) (mouseEvent.getSource())).getTranslateY();

            putImageViewInFront(((ImageView)(mouseEvent.getSource())));
        }
    };

    /*
     * dragEvent : controleur
     */
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

	/*
	 * releaseEvent : controleur qui gere la collision des meubles entre-eux
	 * et des meubles avec le plan
	 * - Les meubles ne doivent pas se chevaucher
	 * - Les meubles ne peuvent pas depasser le cadre du plan de cuisine
	 */
    EventHandler<MouseEvent> releaseEvent = new EventHandler<MouseEvent>(){
    	@Override
        public void handle(MouseEvent mouseEvent) {
    		ImageView iv = (ImageView) mouseEvent.getSource();

    		/*Si l'imageView touche le trace du plan de cuisine,
    		 * on replace l'imageView a son anicenne position*/
    		if(iv.getBoundsInParent().intersects(topPlanCuisine.getBoundsInParent())
    				|| iv.getBoundsInParent().intersects(leftPlanCuisine.getBoundsInParent())
    				|| iv.getBoundsInParent().intersects(rightPlanCuisine.getBoundsInParent())
    				|| iv.getBoundsInParent().intersects(bottomPlanCuisine.getBoundsInParent())) {
    			((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    			((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);
    			indication.setTextFill(Color.color(1, 0, 0));
    			indication.setText("Les meubles ne doivent pas depasser le plan!");
    		}

    		if(!iv.getBoundsInParent().intersects(planCuisine.getBoundsInParent())) {

    			/* Si l'imageView et le plan de la cuisine ne se chevauchent plus,
    			 * on replace l'imageView a son anicenne position
    			 */
    			((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    			((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);
    			indication.setTextFill(Color.color(1, 0, 0));
    			indication.setText("Les meubles ne doivent pas depasser le plan!");
    		}else {

    			/*Pour repertorier les imageView autre que celui qu'on regarde*/
    			ArrayList<ImageView> otherImages = new ArrayList<ImageView>();

    			for(int i = 0; i < getImageList().size(); i++) {
    				if(!iv.equals(getImageList().get(i))) {
    					otherImages.add(getImageList().get(i));
    				}
    			}

    			for(int i = 0; i < otherImages.size(); i++) {
    				if(iv.getBoundsInParent().intersects(otherImages.get(i).getBoundsInParent())) {

    					/*Si l'imageView et une autre image se chevauchent,
    					 * on replace l'imageView a son anicenne position*/
    					((ImageView)(mouseEvent.getSource())).setTranslateX(orgTranslateX);
    					((ImageView)(mouseEvent.getSource())).setTranslateY(orgTranslateY);

    					/*Pour laisser un message aux utilisateurs*/
    					indication.setTextFill(Color.color(1, 0, 0));
    					indication.setText("Les meubles ne doivent pas se chevaucher!");
    				}
    			}
    		}

    	}
    };

	/*****************POUR DIMENSIONNER LE PLAN DE LA CUISINE******************/
	public int getLayoutXPlanCuisine() { return (int) this.planCuisine.getLayoutX(); }
	public void setLayoutXPlanCuisine(int d) {this.planCuisine.setLayoutX(d);}

	public int getLayoutYPlanCuisine() { return (int) this.planCuisine.getLayoutY(); }
	public void setLayoutYPlanCuisine(int d) {this.planCuisine.setLayoutY(d);}

	public int getHeightPlanCuisine() { return (int) this.planCuisine.getHeight(); }
	public void setHeightPlanCuisine(int d) {
		this.planCuisine.setHeight(d);
		this.leftPlanCuisine.setEndY(getLayoutYPlanCuisine()+d);
		this.bottomPlanCuisine.setStartY(getLayoutYPlanCuisine()+d);
		this.bottomPlanCuisine.setEndY(getLayoutYPlanCuisine()+d);
		this.rightPlanCuisine.setEndY(getLayoutYPlanCuisine()+d);
	}

	public int getWidthPlanCuisine() { return (int) this.planCuisine.getWidth(); }
	public void setWidthPlanCuisine(int d) {
		this.planCuisine.setWidth(d);
		this.topPlanCuisine.setEndX(getLayoutXPlanCuisine()+d);
		this.rightPlanCuisine.setStartX(getLayoutXPlanCuisine()+d);
		this.rightPlanCuisine.setEndX(getLayoutXPlanCuisine()+d);
		this.bottomPlanCuisine.setEndX(getLayoutXPlanCuisine()+d);
	}


	/*****************POUR RECUPERER L'ECHELLE******************/
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

	public ArrayList<ImageView> getImageList() {return imageList;}

	public void setScene(Scene scene) {this.scene = scene;}
	public void setStage(Stage stage) {this.stage = stage;}
	public void setPane(Pane pane) {this.pane = pane;}

	public String getTex(String name){
		String tex = "";
		switch(name) {
			case "addEvier1":
				tex = "photos/evier_gris.png";
				break;
			case "addEvier2":
				tex = "photos/evier_inox.png";
				break;
			case "addEvier3":
				tex = "photos/evier_marbre.png";
				break;
			case "addEvier4":
				tex = "photos/evier_sable.png";
				break;
			case "addCounter1":
				tex = "photos/counter_marbreBlanc.png";
				break;
			case "addCounter2":
				tex = "photos/counter_marbreNoir.png";
				break;
			case "addFour1":
				tex = "photos/four_gris.png";
				break;
			case "addFour2":
				tex = "photos/four_inox.png";
				break;
			case "addFour3":
				tex = "photos/four_marbre.png";
				break;
			case "addFour4":
				tex = "photos/four_sable.png";
				break;
			case "addFrigo1":
				tex = "photos/evier_gris.png";
				break;
			case "addFenetre":
				tex = "photos/window.png";
				break;
			case "addPorte":
				tex = "photos/door.png";
				break;
			default:
				System.out.println("ERROR: tex not found");
				break;
		}
		return tex;
	}

	public static int[] getTexSize(String name){
		if(name.contains("Evier")){
			int[] s = {120,60};
			return s;
		} else if (name.contains("Counter")){
			int[] s = {60,60};
			return s;
		} else if (name.contains("Four")){
			int[] s = {70,60};
			return s;
		} else if (name.contains("Frigo")){
			int[] s = {60,75};
			return s;
		} else if (name.contains("Fenetre")){
			int[] s = {100,15};
			return s;
		} else if (name.contains("Porte")){
			int[] s = {70,70};
			return s;
		} else {
			System.out.println("ERROR: Tex size couldn't be set!");
		}
		return null;
	}
}
