package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	CorsoDAO cdao = new CorsoDAO();
	StudenteDAO sdao = new StudenteDAO();
	
	public List <Corso> getTuttiICorsi () {
		return cdao.getTuttiICorsi();
	}

	public List <Studente> getStudentiIscrittiAiCorsi(List <Corso> corsi) {
		if (corsi.size() == 1)
			return getStudentiIscrittiSingoloCorso(corsi.get(0));
		else 
			return getStudentiIscrittiCorsi();
		
	}
	
	public List <Studente> getStudentiIscrittiCorsi() {
		return sdao.getStudentiIscrittiCorsi();
	}
	
	public List <Studente> getStudentiIscrittiSingoloCorso(Corso c) {
		return sdao.getStudentiIscrittiCorso(c);
	}
	
	public List <Studente> getStudente(int matr) {
		return sdao.getStudente(matr);
	}
	
	public List <Corso> getCorsiStudente (Studente s) {
		return cdao.getCorsiStudente(s);
	}

	public boolean verificaIscrizione(Studente studente, Corso corso) {
		return sdao.verificaIscrizione(studente, corso);
	}

	public boolean iscriviStudenteCorso(Studente studente, Corso corso) {
		return sdao.iscriviStudenteCorso(studente, corso);
	}
}
