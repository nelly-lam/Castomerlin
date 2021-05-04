package controller;

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
	
	//constantes qui definissent les dimensions maximales permises pour dessiner le plan de cuisine
	public static final int largeurMaxPlan = 450;
	public static final int longueurMaxPlan = 850;
	
	//echelle qui permet de redimensionner les meubles
	private double echelle;
	
	//components de la page d'accueil
	@FXML private Button commencer;
	
	//components de la page des dimensions
	@FXML private TextField longueur;
	@FXML private TextField largeur;
	@FXML private Button creer;
	@FXML private Label information;
	
	//components de la page de creation
	private ControllerCreation cc;
	
	private Scene scene;
	
	@FXML private Rectangle rectangle;
	
	//attributs qui determinent les dimensions du plan (rectangle)
	private int LONG;
	private int LARG;

	//attributs qui definissent la taille des fenetres
	private double widthFrame = 1280;
	private double heightFrame = 720;
	
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
	public double getEchelle() {
		return echelle;
	}
	public void setEchelle(double echelle) {
		this.echelle = echelle;
	}
	
	
	public Stage launchWindow(String file, String title) throws IOException {
		Pane hboxD = (Pane)FXMLLoader.load(getClass().getResource(file));
		
        Stage stageD = new Stage();
        stageD.setTitle(title);
        Scene sceneD = new Scene(hboxD, widthFrame, heightFrame);
        
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
		launchWindow("/code/kitchen_builder_dimensions.xml", "CastoMerlin - dimension de votre cuisine");
		closeCurrentWindow(commencer);
    }
    
    
    /**
     * creer() : cree un canvas avec les dimensions valides donnees par l'utilisateur
     * dimensions valides : un nombre obligatoire pour la longueur et la largeur,
     * dimensions en cm
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
    			
    			//On cree une nouvelle fenetre que si les dimensions rentrees par l'utilisateur sont valides
                Stage stageD = new Stage();
                stageD.setTitle("CastoMerlin - création de votre plan de cuisine");
                
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/kitchen_builder_creation.xml"));
                Pane pane = (Pane) fxmlLoader.load();
                ControllerCreation ca = fxmlLoader.<ControllerCreation>getController();
                

                //On recupere les dimensions rentrees de la cuisine
                int longueurFictive, largeurFictive = 0;
                
                //On ecrit ces dimensions sur le plan mis a l'echelle
                String longueurSurPlanTxt;
                String largeurSurPlanTxt;
                
                if(Integer.valueOf(longueur.getText()) >= Integer.valueOf(largeur.getText())) {
                	longueurFictive = Integer.valueOf(longueur.getText());
                    largeurFictive = Integer.valueOf(largeur.getText());
                    
                    longueurSurPlanTxt = "Longueur = " + longueur.getText() + " cm";
            		largeurSurPlanTxt = "Largeur = " + largeur.getText() + " cm";
                }else {
                	largeurFictive = Integer.valueOf(longueur.getText());
                    longueurFictive = Integer.valueOf(largeur.getText());
                    
                    longueurSurPlanTxt = "Largeur = " + largeur.getText() + " cm";
            		largeurSurPlanTxt = "Longueur = " + longueur.getText() + " cm";
                }
        		
        		/* CALCUL DE L'ECHELLE
        		 * 
        		 * */

        		double rapportLongueurLargeur = longueurFictive / (double) largeurFictive;
        		double rapportLargeurLongueur = largeurFictive / (double) longueurFictive;
        		
        		//Si la longueur finale ne depasse pas la limite fixee par longueurMaxPlan
        		if(largeurMaxPlan * rapportLongueurLargeur <= longueurMaxPlan) {
        			setEchelle(largeurMaxPlan / (double) largeurFictive);
        			setLONG((int) (largeurMaxPlan * rapportLongueurLargeur));
        			setLARG(largeurMaxPlan);

        		}else {
        			setEchelle(longueurMaxPlan / (double) longueurFictive);
        			setLONG(longueurMaxPlan);
        			setLARG((int) (longueurMaxPlan * rapportLargeurLongueur));
        			
        		}
        		
        		//ecriture des dimensions 
        		ca.setLongueurSurPlan(longueurSurPlanTxt);
        		ca.setLargeurSurPlan(largeurSurPlanTxt);

        		
        		ca.setLayoutYMesure50cmTrait(getLARG()+15);
        		ca.setWidthmesure50cmTrait(50*getEchelle());
        		ca.setLayoutYMesure50cm(getLARG()+10);
        		
        		//on change les dimensions du plan (rectangle)
        		ca.setHeightPlanCuisine(getLARG());
        		ca.setWidthPlanCuisine(getLONG());
        		ca.setEchelle(getEchelle());

                scene = new Scene(pane, widthFrame, heightFrame);
                
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
