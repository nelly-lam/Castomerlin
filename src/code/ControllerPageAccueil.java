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
	
	
	
	
	/*****************METHODES*******************/
	
	public int getLONG() {return LONG;}
	public void setLONG(int lONG) {LONG = lONG;}
	public int getLARG() {return LARG;}
	public void setLARG(int lARG) {LARG = lARG;}
	public Canvas getCanvas() {return canvas;}
	public void setCanvas(Canvas canvas) {this.canvas = canvas;}

    /**
     * commencer() : redirige vers une autre fenêtre Application 
     * pour récupérer les dimensions
     * @throws IOException 
     */
    @FXML
    protected void commencer() throws IOException {
        //new PageDimension();
        //System.out.println("Commencer");
        //se rediriger vers la deuxieme page xml
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() - 60;
		
        HBox hboxD = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_dimensions.xml"));
        Stage stageD = new Stage();
        stageD.setTitle("CastoMerlin - dimension de votre cuisine");
        Scene sceneD = new Scene(hboxD, width, height);
        stageD.setScene(sceneD);
        stageD.show();
		
    	/*
    	HBox hbox = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_dimensions.xml"));
        ChildController controller = loader.getController();
        controller.setMainWindow(this);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(button.getScene().getWindow());
        Scene scene = new Scene(newWindow);
        stage.setScene(scene);
        stage.show();
        */
        
    }
    
    
    /**
     * creer() : cree un canvas avec les dimensions donnees par l'utilisateur
     * @throws IOException 
     */
    @FXML
    protected void creerPlan() throws IOException {
    	if(longueur.getText().trim().isEmpty() && largeur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une longeur et une largeur");
    	}else if(longueur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une longueur");
    	}else if(largeur.getText().trim().isEmpty()) {
    		information.setText("Veuillez rentrer une largeur");
    	}else {
    		//on cree une nouvelle fenetre
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    		double width = screenSize.getWidth();
    		double height = screenSize.getHeight() - 60;
    		
            HBox hboxC = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_creation.xml"));
            
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
            
            Stage stageC = new Stage();
            stageC.setTitle("CastoMerlin - création de votre plan de cuisine");
            Scene sceneC = new Scene(hboxC, width, height);
            stageC.setScene(sceneC);
            stageC.show();
            
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
        HBox hboxS = (HBox)FXMLLoader.load(getClass().getResource("kitchen_builder_save.xml"));
        Stage stageS = new Stage();
        stageS.setTitle("CastoMerlin - sauvegarde de votre plan de cuisine");
        Scene sceneS = new Scene(hboxS);
        stageS.setScene(sceneS);
        stageS.show();
    }
    
    @FXML
    protected void recommencerPlan() {
    	
    }
    
    @FXML
    protected void annulerEnvoiePlan() {
        Stage stageS = (Stage) annulerEnvoie.getScene().getWindow();
        stageS.close();
    }

    @FXML
    protected void retournerKitchenBuilder() throws IOException {
        Stage stageS = (Stage) annulerEnvoie.getScene().getWindow();
        stageS.close();
        commencer();
    }

}
