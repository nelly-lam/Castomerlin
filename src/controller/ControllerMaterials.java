package controller;

import java.util.ArrayList;

import code.Materials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControllerMaterials implements EventHandler<MouseEvent>{
	private Pane pane;
	private ComboBox<String> materialsSinkComboBox;
	private ComboBox<String> materialsCounterComboBox;
	private ComboBox<String> materialsOvenComboBox;
	
	private ArrayList<Materials>sinkMaterials = new ArrayList<Materials>() {{
		add(new Materials("gris", "photos/evier_gris.png"));
		add(new Materials("inox", "photos/evier_inox.png"));
		add(new Materials("marbre", "photos/evier_marbre.png"));
		add(new Materials("sable", "photos/evier_sable.png"));
	}};
	private ArrayList<Materials>counterMaterials = new ArrayList<Materials>(){{
		add(new Materials("blanc", "photos/counter_marbreBlanc.png"));
		add(new Materials("noir", "photos/counter_marbreNoir.png"));
	}};;
	private ArrayList<Materials>ovenMaterials = new ArrayList<Materials>() {{
		add(new Materials("gris", "photos/four_gris.png"));
		add(new Materials("inox", "photos/four_inox.png"));
		add(new Materials("marbre", "photos/four_marbre.png"));
		add(new Materials("sable", "photos/four_sable.png"));
	}};;

	
	public ControllerMaterials(Pane p, ComboBox<String> materiauEvier, 
			ComboBox<String> materiauTable, ComboBox<String> materiauFour) {
		materialsSinkComboBox = materiauEvier;
		materialsCounterComboBox = materiauTable;
		materialsOvenComboBox = materiauFour;
		this.setPane(p);
	}
	
	
	@Override
	public void handle(MouseEvent mouseEvent) {
		if(((ComboBox<?>)(mouseEvent.getSource())).equals(this.materialsSinkComboBox)) {
			//System.out.print("ici");
			this.materialsSinkComboBox.setItems(getNameSinkMaterials());
			this.materialsSinkComboBox.setOnAction(e -> addImageSink(mouseEvent));
		}else if(((ComboBox<?>)(mouseEvent.getSource())).equals(this.materialsCounterComboBox)) {
			this.materialsCounterComboBox.setItems(getNameCounterMaterials());
		}else if(((ComboBox<?>)(mouseEvent.getSource())).equals(this.materialsOvenComboBox)) {
			this.materialsOvenComboBox.setItems(getNameOvenMaterials());
		}

	}
	
	
	public void addImageSink(MouseEvent mouseEvent) {
		for(int i = 0; i < sinkMaterials.size(); i++) {
			if(this.materialsSinkComboBox.getValue() == "gris") {
				
			}
		}
	}
	

	
	
	public ComboBox getMaterialsSinkComboBox() {return materialsSinkComboBox;}
	public void setMaterialsSinkComboBox(ComboBox materials) {this.materialsSinkComboBox = materials;}

	public ArrayList<Materials> getSinkMaterials() {return sinkMaterials;}
	public void setSinkMaterials(ArrayList<Materials> sinkMaterials) {this.sinkMaterials = sinkMaterials;}

	public ObservableList<String> getNameSinkMaterials() {
		String nameSinkMaterials[] = { "gris", "inox", "marbre", "sable" };
		ObservableList<String> list
        = FXCollections.observableArrayList(nameSinkMaterials);
		return list;
	}
	
	public ObservableList<String> getNameCounterMaterials() {
		String nameCounterMaterials[] = { "blanc", "noir" };
		ObservableList<String> list
        = FXCollections.observableArrayList(nameCounterMaterials);
		return list;
	}
	
	public ObservableList<String> getNameOvenMaterials() {
		String nameOvenMaterials[] = { "gris", "inox", "marbre", "sable" };
		ObservableList<String> list
        = FXCollections.observableArrayList(nameOvenMaterials);
		return list;
	}
	
	/*
	public ArrayList<String> getNameSinkMaterials(){
		ObservableList<String> list //
        = FXCollections.observableArrayList(mercury, venus, earth);
		
		ArrayList<String> name = new ArrayList<String>();
		for(int i = 0; i < getSinkMaterials().size(); i++){
			name.add(getSinkMaterials().get(i).getName());
		}
		return name;
	}*/
	
	public ArrayList<Materials> getCounterMaterials() {return counterMaterials;}
	public void setCounterMaterials(ArrayList<Materials> counterMaterials) {this.counterMaterials = counterMaterials;}

	public ArrayList<Materials> getOvenMaterials() {return ovenMaterials;}
	public void setOvenMaterials(ArrayList<Materials> ovenMaterials) {this.ovenMaterials = ovenMaterials;}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}


	public ComboBox<String> getMateriauCounterComboBox() {
		return materialsCounterComboBox;
	}


	public void setMateriauCounterComboBox(ComboBox<String> materiauCounterComboBox) {
		this.materialsCounterComboBox = materiauCounterComboBox;
	}


	public ComboBox<String> getMateriauOvenComboBox() {
		return materialsOvenComboBox;
	}


	public void setMateriauOvenComboBox(ComboBox<String> materiauOvenComboBox) {
		this.materialsOvenComboBox = materiauOvenComboBox;
	}
	

}

