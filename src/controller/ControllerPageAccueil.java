package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerPageAccueil{

	/*constantes qui definissent les dimensions maximales permises
	 * pour dessiner le plan de cuisine */
	public static final int maxHeightPlan = 450;
	public static final int maxWidthPlan = 850;

	/*echelle qui permet de redimensionner les meubles*/
	private double scale;

	/*components de la page d'accueil*/
	@FXML private Button begin;

	/*components de la page des dimensions*/
	@FXML private TextField longueur;
	@FXML private TextField largeur;
	@FXML private Button create;
	@FXML private Label information;

	private ControllerCreation cc;

	private Scene scenePageCreation;

	/*attributs qui determinent les dimensions du plan (rectangle)*/
	private int widthPlan;
	private int heightPlan;

	/*attributs qui definissent la taille des fenetres*/
	private double widthFrame = 1280;
	private double heightFrame = 720;



	/**
	 * launchWindow() : ouvre une autre fenetre
	 * @param file le nom du fichier qu'on veut charger dans cette nouvelle fenetre
	 * @param title le titre de la nouvelle fenetre
	 * @return le stage de la nouvelle fenetre
	 * @throws IOException
	 */
	public Stage launchWindow(String file, String title) throws IOException {
		Pane hboxD = (Pane)FXMLLoader.load(getClass().getResource(file));

        Stage stageD = new Stage();
        stageD.setTitle(title);
        Scene sceneD = new Scene(hboxD, widthFrame, heightFrame);

        stageD.setScene(sceneD);
        stageD.show();
        return stageD;
	}

	/**
	 * closeCurrentWindow() : ferme la fenetre qui contient le bouton passe en parametre
	 * @param b Button
	 */
    protected void closeCurrentWindow(Button b) {
    	Stage currentWindow = (Stage) b.getScene().getWindow();
        currentWindow.close();
    }

    /**
     * begin() : redirige vers une autre fen�tre qui recupere les dimensions
     * @throws IOException
     */
    @FXML
    protected void begin() throws IOException {
		launchWindow("/code/kitchen_builder_dimensions.xml", "CastoMerlin - dimension de votre cuisine");
		closeCurrentWindow(begin);
    }


    /**
     * createPlan() : ouvre une fenetre de creation avec un plan de cuisine defini par
     * les dimensions valides donnees par l'utilisateur
     * dimensions valides : un nombre obligatoire pour la longueur et la largeur,
     * dimensions en cm
     * @throws IOException
     */
    @FXML
    protected void createPlan() throws IOException {
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
    			/*On cree une nouvelle fenetre que
    			 * si les dimensions rentrees par l'utilisateur sont valides*/

                Stage stageD = new Stage();
                stageD.setTitle("CastoMerlin - cr�ation de votre plan de cuisine");

        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/kitchen_builder_creation.xml"));
                Pane pane = (Pane) fxmlLoader.load();
                ControllerCreation ca = fxmlLoader.<ControllerCreation>getController();
                
                /*On recupere les dimensions de la cuisine rentrees par l'utilisateur*/
                int fictionalWidth, fictionalHeight = 0;

                /*On ecrit ces dimensions sur le plan mis a l'echelle*/
                String textWidth;
                String textHeight;

                /*Arrangement tel que largeurFictive <= longueurFictive*/
                if(Integer.valueOf(longueur.getText()) >= Integer.valueOf(largeur.getText())) {
                	fictionalWidth = Integer.valueOf(longueur.getText());
                    fictionalHeight = Integer.valueOf(largeur.getText());

                    textWidth = "Longueur = " + longueur.getText() + " cm";
            		textHeight = "Largeur = " + largeur.getText() + " cm";
                }else {
                	fictionalHeight = Integer.valueOf(longueur.getText());
                    fictionalWidth = Integer.valueOf(largeur.getText());

                    textWidth = "Largeur = " + largeur.getText() + " cm";
            		textHeight = "Longueur = " + longueur.getText() + " cm";
                }

        		/*****************CALCUL DE L'ECHELLE***********/
        		double ratioWidthHeight = fictionalWidth / (double) fictionalHeight;
        		double ratioHeightWidth = fictionalHeight / (double) fictionalWidth;

        		/*Si la longueur finale ne depasse pas la limite fixee par longueurMaxPlan*/
        		if(maxHeightPlan * ratioWidthHeight <= maxWidthPlan) {
        			setScale(maxHeightPlan / (double) fictionalHeight);
        			setWidthPlan((int) (maxHeightPlan * ratioWidthHeight));
        			setHeightPlan(maxHeightPlan);
        		}else {
        			setScale(maxWidthPlan / (double) fictionalWidth);
        			setWidthPlan(maxWidthPlan);
        			setHeightPlan((int) (maxWidthPlan * ratioHeightWidth));

        		}

        		/*ecriture des dimensions sur le plan de cuisine*/
        		ca.setLongueurSurPlan(textWidth);
        		ca.setLargeurSurPlan(textHeight);
        		ca.setLayoutYMesure50cmTrait(getHeightPlan()+15); //on place l'echelle en dessous du plan
        		ca.setWidthmesure50cmTrait(50*getScale()); //on determine la taille sur 50cm
        		ca.setLayoutYMesure50cm(getHeightPlan()+10); //on place le texte de l'echelle en dessous du plan

        		/*on change les dimensions d'ecran du plan de cuisine*/
        		ca.setHeightPlanCuisine(getHeightPlan());
        		ca.setWidthPlanCuisine(getWidthPlan());
        		ca.setEchelle(getScale());

                scenePageCreation = new Scene(pane, widthFrame, heightFrame);

                ca.setPane(pane);
                ca.setScene(scenePageCreation);
                ca.setStage(stageD);

                stageD.setScene(scenePageCreation);
                stageD.show();
        		closeCurrentWindow(create);
    		}

    	}
    }

	/**
	 * isNumeric() : verifie que les dimensions que l'utilisateur a rentre
	 * est bien un nombre
	 * @param str un texte (venant de dimension)
	 * @return true si c'est un nombre, false sinon
	 */
	private boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }



	/*****************GETTER ET SETTER*******************/
	public int getWidthPlan() {return widthPlan;}
	public void setWidthPlan(int lONG) {widthPlan = lONG;}

	public int getHeightPlan() {return heightPlan;}
	public void setHeightPlan(int lARG) {heightPlan = lARG;}

	public ControllerCreation getCc() {return cc;}
	public void setCc(ControllerCreation cc) {this.cc = cc;}

	public Scene getScenePageCreation() {return scenePageCreation;}
	public void setScenePageCreation(Scene scene) {this.scenePageCreation = scene;}

	public double getScale() {return scale;}
	public void setScale(double echelle) {this.scale = echelle;}


}
