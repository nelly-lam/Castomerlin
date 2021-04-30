package code;

import java.awt.Dimension;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	//@FXML private Pane plan;
	
	@FXML private Button defaire;
	@FXML private Button refaire;
	@FXML private Button sauvegarder;
	@FXML private Button recommencer;
	
	@FXML private Button addEvier1;
	@FXML private Button addEvier2;
	@FXML private Button addEvier3;
	@FXML private Button addEvier4;
	
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
	//public Canvas getCanvas() {return canvas;}
	//public void setCanvas(Canvas canvas) {this.canvas = canvas;}

	
	public Stage launchWindow(String file, String title) throws IOException {
		Pane hboxD = (Pane)FXMLLoader.load(getClass().getResource(file));
		
        Stage stageD = new Stage();
        stageD.setTitle(title);
        Scene sceneD = new Scene(hboxD, width, height);
        
        stageD.setScene(sceneD);
        stageD.show();
        return stageD;
	}
	
	public Stage launchWindowCanvas(String file, String title) throws IOException {
		Pane hboxD = (Pane)FXMLLoader.load(getClass().getResource(file));
		
        Stage stageD = new Stage();
        stageD.setTitle(title);

        //creation du canvas
        Canvas canvas = new Canvas();
        canvas.setWidth(400);
        canvas.setHeight(200);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,width,canvas.getWidth(),canvas.getHeight());
        hboxD.getChildren().add(canvas);

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
    		
    		//Stage newWindow = launchWindowCanvas("kitchen_builder_creation.xml", "CastoMerlin - création de votre plan de cuisine");
    		Pane hboxD = (Pane)FXMLLoader.load(getClass().getResource("kitchen_builder_creation.xml"));
    		
            Stage stageD = new Stage();
            stageD.setTitle("CastoMerlin - création de votre plan de cuisine");

            //creation du canvas
            Canvas canvas = new Canvas(894.0,449.0);
            canvas.setLayoutX(63.0);
            canvas.setLayoutY(164);
            
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            hboxD.getChildren().add(canvas);

            Scene sceneD = new Scene(hboxD, width, height);
            
            stageD.setScene(sceneD);
            stageD.show();
    		closeCurrentWindow(creer);
            
            //on recupere les dimensions de la cuisine
    		this.setLONG(Integer.valueOf(longueur.getText()));
    		this.setLARG(Integer.valueOf(largeur.getText()));
            
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
