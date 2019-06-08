package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.poweroutages.model.EventType;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PowerOutagesController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> ChoiceBott;

    @FXML
    private HBox ChoiceNERC;

    @FXML
    private TextField MaxYearsBott;

    @FXML
    private TextField MaxHoursBott;

    @FXML
    private Button CalcolaBott;

    @FXML
    private TextArea TxtResult;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	Nerc nerc = ChoiceBott.getValue();
    	int anni = Integer.parseInt(MaxYearsBott.getText());
    	int ore = Integer.parseInt(MaxHoursBott.getText());
    	
    	if(ore<0 || anni <=0)
    		TxtResult.appendText("Scegli ore e anni correttamente");
    	
    	if(nerc==null) {
    		TxtResult.appendText("Scegli un nerc");
    	}else {
  
    			
    			List<EventType> worst = model.calcolaSequenza(nerc,anni,ore) ;
    			TxtResult.clear();
    			TxtResult.appendText("Tot persone coinvolte : "+ model.getMaxPersone());
    			TxtResult.appendText("\n");
    			TxtResult.appendText("Tot ore di blackout :" +model.getTotOre());
    			for(EventType e : worst) {
    				TxtResult.appendText(String.format("%d %d %s %d %d",e.getYear(),e.getDateInizio(),e.getDateFine(),e.getDurata(),e.getPersone()));
    				TxtResult.appendText("\n");
    			}
    		}
  

    }

    @FXML
    void initialize() {
        assert ChoiceBott != null : "fx:id=\"ChoiceBott\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert ChoiceNERC != null : "fx:id=\"ChoiceNERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert MaxYearsBott != null : "fx:id=\"MaxYearsBott\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert MaxHoursBott != null : "fx:id=\"MaxHoursBott\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert CalcolaBott != null : "fx:id=\"CalcolaBott\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert TxtResult != null : "fx:id=\"TxtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		ChoiceBott.getItems().addAll(model.getNercList());
	}
}
