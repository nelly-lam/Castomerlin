package code;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObjetCuisine {

	/*****************ATTRIBUTS*******************/
	private Point hautGauche;
	private int largeur;
	private int hauteur;
	private String type;
	private ImageView iv;
	private int idObjet;
	private Rectangle zone;
	
	
	/*****************CONSTRUCTEUR*******************/
	public ObjetCuisine(int indice, ImageView iv, String s, Point p, int l, int h) {
		this.idObjet = indice;
		this.hautGauche = p;
		this.largeur = l;
		this.hauteur = h;
		this.type = s;
		this.iv = iv;
		this.zone = new Rectangle(l, h);
		this.zone.setLayoutX(p.getX());
		this.zone.setLayoutY(p.getY());
	}
	
	
	/*****************METHODES*******************/
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public Point getHautGauche() {
		return hautGauche;
	}

	public void setHautGauche(Point hautGauche) {
		this.hautGauche = hautGauche;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}


	public int getIdObjet() {
		return idObjet;
	}


	public void setIdObjet(int idObjet) {
		this.idObjet = idObjet;
	}


	public Rectangle getZone() {
		return zone;
	}


	public void setZone(Rectangle zone) {
		this.zone = zone;
	}
	
	

}
