package it.polito.tdp.libretto;


import it.polito.tdp.libretto.model.Libretto;
import it.polito.tdp.libretto.model.Voto;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
	
	// deve avere un riferimento alla classe libretto, affinché possa chiamare i suoi metodiù
	
	private Libretto model;
	//il controller non deve mai fare la new del modello, deve avere un riferimento
	// la new la fa il main
	/*
	 * il controller non salva nulla, tutto lo stato dell'applicazione 
	 * non viene memorizzato nel controller, non viene salvata alcuna variabile
	 * reagisce solo alle azione prese dall'utente, non memorizza nulla
	 * chiede al modello di salvarli
	 */
	
	
	
	
	public void setModel(Libretto model) {
		this.model = model;
    	txtRisultato.setText(this.model.toString());

	}
	
	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<Integer> cmbVoti;

	    @FXML
	    private DatePicker selData;

	    @FXML
	    private TextField txtCorso;

	    @FXML
	    private TextArea txtRisultato;

	    @FXML
	    void doInserisci(ActionEvent event) {
	    	String corso = txtCorso.getText();
	    	Integer punti = cmbVoti.getValue();
	    	LocalDate date = selData.getValue();
	    	
	    	Voto v = new Voto( corso, punti, date);
	    	this.model.add(v);
	    	txtRisultato.setText(this.model.toString());
	    	
	    	
	    }

	    @FXML
	    void initialize() {
	        assert cmbVoti != null : "fx:id=\"cmbVoti\" was not injected: check your FXML file 'main.fxml'.";
	        assert selData != null : "fx:id=\"selData\" was not injected: check your FXML file 'main.fxml'.";
	        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'main.fxml'.";
	        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'main.fxml'.";
	        
	        for( int p = 18; p <= 30; p++) {
	        	cmbVoti.getItems().add(p);
	        }
	        
	        
	        
	    }

	}

	

	



