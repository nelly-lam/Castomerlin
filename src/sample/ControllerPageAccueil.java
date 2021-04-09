package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControllerPageAccueil{
	
	private float hauteur;
	private float largeur;
	
    @FXML
    protected void commencer(ActionEvent e) {
        if(e.getSource() == "Commencer"){
            //se rediriger vers la deuxieme page fxml
        }
        if(e.getSource() == "Dimensions") {
        	
        }
    }

	public float getLargeur() {
		return largeur;
	}

	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}
}
