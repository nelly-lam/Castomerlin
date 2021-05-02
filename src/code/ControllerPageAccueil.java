package code;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
	private Canvas canvas;
	private GraphicsContext gc;
	private ControllerCreation cc;
	
	private Scene scene;
	
	@FXML private Rectangle rectangle;
	
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

	
	/*****************GETTER ET SETTER*******************/
	
	public int getLONG() {return LONG;}
	public void setLONG(int lONG) {LONG = lONG;}
	public int getLARG() {return LARG;}
	public void setLARG(int lARG) {LARG = lARG;}
	public ControllerCreation getCc() {
		return cc;
	}
	public void setCc(ControllerCreation cc) {
		this.cc = cc;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
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

	
    protected void closeCurrentWindow(Button b) {
    	Stage currentWindow = (Stage) b.getScene().getWindow();
        currentWindow.close();
    }
    
    /**
     * commencer() : redirige vers une autre fenêtre qui recupere les dimensions
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
    		if(!isNumeric(largeur.getText()) && !isNumeric(longueur.getText())) {
    			information.setText("Veuillez rentrer une longueur et largeur valide (nombre en cm)");
    		}else if(!isNumeric(largeur.getText())) {
    			information.setText("Veuillez rentrer une largeur valide (nombre en cm)");
    		}else if(!isNumeric(longueur.getText())) {
    			information.setText("Veuillez rentrer une longueur valide (nombre en cm)");
    		}else{
                Stage stageD = new Stage();
                stageD.setTitle("CastoMerlin - création de votre plan de cuisine");
                
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kitchen_builder_creation.xml"));
                Pane pane = (Pane) fxmlLoader.load();
                ControllerCreation ca = fxmlLoader.<ControllerCreation>getController();
                
                //addEvier1.setOnMouseClicked(e->ca.ajouterObjet(e));
        		//ImageView iv = ca.ajouterObjet();
        		//pane.getChildren().add(iv);

                //on recupere les dimensions de la cuisine
        		this.setLONG(Integer.valueOf(longueur.getText()));
        		this.setLARG(Integer.valueOf(largeur.getText()));
        		
        		
        		//CALCUL PROPORTION
        		
        		
        		
        		//on change les dimensions du rectangle de dessin
        		ca.setHeightRectangle(getLARG());
        		ca.setWidthRectangle(getLONG());

                //creation du canvas
                this.canvas = new Canvas(894.0,449.0);
                this.canvas.setLayoutX(63.0);
                this.canvas.setLayoutY(164);
                
                this.gc = canvas.getGraphicsContext2D();
                //this.gc.setStroke(Color.BLACK);
                //this.gc.setFill(Color.BLACK);
                //this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                //rectangle.setHeight(this.getLARG());
                //rectangle.setWidth(this.getLONG());
                		
                pane.getChildren().add(canvas);

                scene = new Scene(pane, width, height);
                
                ca.setPane(pane);
                ca.setScene(scene);
                ca.setStage(stageD);
                
                stageD.setScene(scene);
                stageD.show();
        		closeCurrentWindow(creer);
    		}

    	}
    }
    

	private boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
    

}
