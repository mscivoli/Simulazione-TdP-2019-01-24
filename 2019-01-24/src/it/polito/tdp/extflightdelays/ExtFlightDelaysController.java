/**
 * Sample Skeleton for 'ExtFlightDelays.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExtFlightDelaysController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxStati"
    private ComboBox<String> cmbBoxStati; // Value injected by FXMLLoader

    @FXML // fx:id="btnVisualizzaVelivoli"
    private Button btnVisualizzaVelivoli; // Value injected by FXMLLoader

    @FXML // fx:id="txtT"
    private TextField txtT; // Value injected by FXMLLoader

    @FXML // fx:id="txtG"
    private TextField txtG; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.appendText("Creazione grafo...");
    	
    	model.creaGrafo();
    	
    	
    	txtResult.appendText("Grafo creato!");
    	cmbBoxStati.getItems().clear();
    	cmbBoxStati.getItems().addAll(model.getGrafo().vertexSet());

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	Simulatore sim = new Simulatore();
    	
    	try {
    		int T = Integer.parseInt(txtT.getText());
    		int G = Integer.parseInt(txtG.getText());
    		sim.init(T, G, "PA");
    		sim.simula();
    	}catch(NumberFormatException e) {
    		txtResult.appendText("inserisci un numero valido");
    	}
    	
    	

    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	
    	txtResult.clear();
    	String stato = cmbBoxStati.getValue();
    	
    	List<String> successori = new LinkedList<String>(Graphs.successorListOf(model.getGrafo(), stato));
    	
    	List<VoliEPeso> voli = new LinkedList<VoliEPeso>();
    	
    	for(String s : successori) {
    		voli.add(new VoliEPeso(s, model.getGrafo().getEdgeWeight(model.getGrafo().getEdge(stato, s))));
    	}
    	
    	Collections.sort(voli);
    	
    	for(VoliEPeso v : voli) {
    		txtResult.appendText(v.getStato()+" "+v.getPeso()+"\n");
    	}

    }
    
    public void setModel(Model model) {
		this.model = model;	
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }
}