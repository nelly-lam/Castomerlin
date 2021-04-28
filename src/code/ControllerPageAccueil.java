package code;

import java.awt.Dimension;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ControllerPageAccueil{
	
	//components de la page d'accueil
	@FXML private Button commencer;
	
	//components de la page des dimensions
	@FXML private TextField longueur;
	@FXML private TextField largeur;
	@FXML private Button creer;
	@FXML private Label information;
	
	//components de la page de creation
	@FXML private Pane plan;
	private Canvas canvas;
	
	@FXML private Button defaire;
	@FXML private Button refaire;
	@FXML private Button sauvegarder;
	@FXML private Button recommencer;
	
	@FXML private Button addEvier1;
	@FXML private Button addEvier2;
	
	//components de la page de sauvegarde
	@FXML private TextField email;
	@FXML private Button envoyer;
	@FXML private Button annulerEnvoie;
	
	private int LONG;
	private int LARG;
	private double width = 1280;
	private double height = 720;
	
	
	
	/*****************METHODES*******************/
	
	public int getLONG() {return LONG;}
	public void setLONG(int lONG) {LONG = lONG;}
	public int getLARG() {return LARG;}
	public void setLARG(int lARG) {LARG = lARG;}
	public Canvas getCanvas() {return canvas;}
	public void setCanvas(Canvas canvas) {this.canvas = canvas;}

	
	public Stage launchWindow(String file, String title) throws IOException {
		HBox hboxD = (HBox)FXMLLoader.load(getClass().getResource(file));
		
        Stage stageD = new Stage();
        stageD.setTitle(title);
        
        Scene sceneD = new Scene(hboxD, width, height);
        
        stageD.setScene(sceneD);
        stageD.show();
        return stageD;
	}
	
    protected void closeCurrentWindow(Button b) {
    	Stage currentWindow = (Stage) b.getScene().getWindow();
        currentWindow.close();
    }
    
    /**
     * commencer() : redirige vers une autre fenêtre Application 
     * pour récupérer les dimensions
     * @throws IOException 
     */
    @FXML
    protected void commencer() throws IOException {
		Stage newWindow = launchWindow("kitchen_builder_dimensions.xml", "CastoMerlin - dimension de votre cuisine");
        closeCurrentWindow(commencer);
    }
    
    
    /**
     * creer() : cree un canvas avec les dimensions donnees par l'utilisateur
     * @throws IOException 
     */
    @FXML
    protected void creerPlan() throws IOException {
    	if(longueur.getText().trim().isEmpty() && largeur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une longeur et une largeur");
    	} else if(longueur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une longueur");
    	} else if(largeur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une largeur");
    	} else {
    		
    		Stage newWindow = launchWindow("kitchen_builder_creation.xml", "CastoMerlin - création de votre plan de cuisine");
            
    		closeCurrentWindow(creer);
            
            //on recupere les dimensions de la cuisine
    		this.setLONG(Integer.valueOf(longueur.getText()));
    		this.setLARG(Integer.valueOf(largeur.getText()));

    		
    		//initialisation du canvas
    		//canvas = new Canvas(this.getLONG(), this.getLARG());
    		/*
    		canvas = new Canvas(300,300);
            
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.fillRect((int) plan.getLayoutX(),(int) plan.getLayoutY(),canvas.getWidth(),canvas.getHeight());
            
            plan.getChildren().add(canvas);
            hboxC.getChildren().add(plan);*/
            
    	}
    }
    
    @FXML
    protected void defaireObjet() {
    	
    }
    
    @FXML
    protected void refaireObjet() {
    	
    }
    
    @FXML
    protected void sauvegarderPlan() throws IOException {
		HBox hboxD = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_save.xml"));
		
        Stage stageD = new Stage();
        stageD.setTitle("CastoMerlin - sauvegarde de votre plan de cuisine");
        
        Scene sceneD = new Scene(hboxD, 470, 300);
        
        stageD.setScene(sceneD);
        stageD.show();
    }
    
    @FXML
    protected void recommencerPlan() {
    	
    }
    
    @FXML
    protected void annulerEnvoiePlan() {
    	
    	closeCurrentWindow(annulerEnvoie);
    }
}
