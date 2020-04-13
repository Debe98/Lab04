package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox <Corso> boxCorsi;

    @FXML
    private CheckBox cbTuttiCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCompletaMatricola;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscriviStudente;
    
    @FXML
    private Button btnVerificaIscrizione;

    @FXML
    private Button btnReset;

    @FXML
    void disableBoxCorsi(ActionEvent event) {
    	boxCorsi.setDisable(cbTuttiCorsi.isSelected());
    	btnIscriviStudente.setDisable(true);
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String stringMatricola = txtMatricola.getText();
    	if (stringMatricola.equals("")) {
    		txtResult.setText("Inserisci una matricola!");
			return;
    	}
    	int matricola;
    	try {
    		matricola = Integer.parseInt(stringMatricola);
    	} catch (Exception e) {
			txtResult.setText("\""+stringMatricola + "\" non e' un numero!");
			txtMatricola.clear();
			return;
		}
    	List <Studente> studenteCercato = modello.getStudente(matricola);
    	if (studenteCercato.isEmpty()) {
    		txtResult.setText("La matricola \""+matricola + "\" non esiste!");
			txtMatricola.clear();
			return;
    	}
    	else if (studenteCercato.size()!=1) {
    		doReset(event);
    		txtResult.setText("Errore sconosciuto!");
    		return;
    	}
    	Studente trovato = studenteCercato.get(0);
    	List <Corso> corsi = modello.getCorsiStudente(trovato);
    	if (corsi.size() == 0) {
    		txtResult.setText("Lo studente \n"+trovato.toString() +" \nNon e' iscritto a nessun corso!\n");
    		return;
    	}
    	else if (corsi.size() == 1)
    		txtResult.setText("Lo studente \n"+trovato.toString() +" \nE' iscritto a "+corsi.size()+" corso:\n");
    	else
    		txtResult.setText("Lo studente \n"+trovato.toString() +" \nE' iscritto a "+corsi.size()+" corsi:\n");
    	
    	for (Corso c : corsi)
    		txtResult.appendText(c.toString()+"\n");
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	List <Corso> corsi;
    	
    	if (cbTuttiCorsi.isSelected())
    		corsi = modello.getTuttiICorsi();
    	else if (boxCorsi.getValue() == null){
    		txtResult.setText("Seleziona un corso!");
    		return;
    	}
    	else {
    		corsi = new ArrayList <> ();
    		corsi.add(boxCorsi.getValue());
    	}
    	List <Studente> studenti = modello.getStudentiIscrittiAiCorsi(corsi);
    	
    	if (studenti.size()==0)
    		txtResult.setText("Nessuno studente risulta iscritto!");
    	else {
    		txtResult.setText("Trovati "+studenti.size()+" studenti:\n");
    		for (Studente s: studenti)
    			txtResult.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void doCompletaMatricola(ActionEvent event) {
    	String stringMatricola = txtMatricola.getText();
    	if (stringMatricola.equals("")) {
    		txtResult.setText("Inserisci una matricola!");
			return;
    	}
    	int matricola;
    	try {
    		matricola = Integer.parseInt(stringMatricola);
    	} catch (Exception e) {
			txtResult.setText("\""+stringMatricola + "\" non e' un numero!");
			txtMatricola.clear();
			return;
		}
    	List <Studente> studenteCercato = modello.getStudente(matricola);
    	if (studenteCercato.isEmpty()) {
    		txtResult.setText("La matricola \""+matricola + "\" non esiste!");
			txtMatricola.clear();
			return;
    	}
    	else if (studenteCercato.size()==1) {
    		Studente trovato = studenteCercato.get(0);
    		txtNome.setText(trovato.getNome());
    		txtCognome.setText(trovato.getCognome());
    		txtResult.clear();
    	}
    	else {
    		doReset(event);
    		txtResult.setText("Errore sconosciuto!");
    	}
    }
    
    @FXML
    void doStandard(KeyEvent event) {
    	btnIscriviStudente.setDisable(true);
    }
    
    @FXML
    void doStandard2(ActionEvent event) {
    	btnIscriviStudente.setDisable(true);
    }
    
    @FXML
    void doVerificaIscrizione(ActionEvent event) {
    	
    	//Ricerca Corso
    	Corso corsoTrovato = boxCorsi.getValue();
    	if (corsoTrovato == null || cbTuttiCorsi.isSelected()){
    		txtResult.setText("Seleziona un corso!");
    		return;
    	}
    	
    	//Ricerca dello Studente
    	String stringMatricola = txtMatricola.getText();
    	if (stringMatricola.equals("")) {
    		txtResult.setText("Inserisci una matricola!");
			return;
    	}
    	int matricola;
    	try {
    		matricola = Integer.parseInt(stringMatricola);
    	} catch (Exception e) {
			txtResult.setText("\""+stringMatricola + "\" non e' un numero!");
			txtMatricola.clear();
			return;
		}
    	List <Studente> studenteCercato = modello.getStudente(matricola);
    	if (studenteCercato.isEmpty()) {
    		txtResult.setText("La matricola \""+matricola + "\" non esiste!");
			txtMatricola.clear();
			return;
    	}
    	else if (studenteCercato.size()!=1) {
    		doReset(event);
    		txtResult.setText("Errore sconosciuto!");
    		return;
    	}
    	
    	Studente StudenteTrovato = studenteCercato.get(0);
    	
    	boolean esito = modello.verificaIscrizione(StudenteTrovato, corsoTrovato);
    	
    	if (esito) {
    		txtResult.setText("Lo studente: \n"+StudenteTrovato.toString()+"\nrisulta gia' iscritto al corso: \n"+corsoTrovato.toString()); 
    	}
    	else {
    		txtResult.setText("Lo studente: \n"+StudenteTrovato.toString()+"\nNon e' iscritto al corso: \n"
    						+corsoTrovato.toString()+"\nSe vuoi iscriverlo premi: \"Iscrivi\" ");
    		btnIscriviStudente.setDisable(false);
    	}
    }

    @FXML
    void doIscriviStudente(ActionEvent event) {

    	//Ricerca Corso
    	Corso corsoTrovato = boxCorsi.getValue();
    	if (corsoTrovato == null || cbTuttiCorsi.isSelected()){
    		txtResult.setText("Seleziona un corso!");
    		btnIscriviStudente.setDisable(true);
    		return;
    	}
    	
    	//Ricerca dello Studente
    	String stringMatricola = txtMatricola.getText();
    	if (stringMatricola.equals("")) {
    		txtResult.setText("Inserisci una matricola!");
    		btnIscriviStudente.setDisable(true);
			return;
    	}
    	int matricola;
    	try {
    		matricola = Integer.parseInt(stringMatricola);
    	} catch (Exception e) {
			txtResult.setText("\""+stringMatricola + "\" non e' un numero!");
			txtMatricola.clear();
			btnIscriviStudente.setDisable(true);
			return;
		}
    	List <Studente> studenteCercato = modello.getStudente(matricola);
    	if (studenteCercato.isEmpty()) {
    		txtResult.setText("La matricola \""+matricola + "\" non esiste!");
			txtMatricola.clear();
			btnIscriviStudente.setDisable(true);
			return;
    	}
    	else if (studenteCercato.size()!=1) {
    		doReset(event);
    		txtResult.setText("Errore sconosciuto!");
    		btnIscriviStudente.setDisable(true);
    		return;
    	}
    	
    	Studente StudenteTrovato = studenteCercato.get(0);
    	
    	boolean esito = modello.iscriviStudenteCorso(StudenteTrovato, corsoTrovato);
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtResult.clear();
    	boxCorsi.setValue(null);
    	btnIscriviStudente.setDisable(true);
    	
    	if (cbTuttiCorsi.isSelected()) {
    		cbTuttiCorsi.setSelected(false);
    		disableBoxCorsi(event);
    	}	
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbTuttiCorsi != null : "fx:id=\"cbTuttiCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletaMatricola != null : "fx:id=\"btnCompletaMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscriviStudente != null : "fx:id=\"btnIscriviStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    Model modello;
    public void setModel(Model m) {
    	modello = m;
    	boxCorsi.getItems().addAll(modello.getTuttiICorsi());
    }
}
