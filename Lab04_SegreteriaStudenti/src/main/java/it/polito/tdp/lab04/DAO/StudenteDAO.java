package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	public List <Studente> getStudentiIscrittiCorsi() {

		final String sql = "SELECT studente.matricola, cognome, nome, cds\r\n" + 
				"FROM studente, iscrizione\r\n" + 
				"WHERE studente.matricola = iscrizione.matricola\r\n" + 
				"GROUP BY studente.matricola, cognome, nome, cds;";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cds = rs.getString("cds");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				int matricola = rs.getInt("matricola");

				studenti.add(new Studente(matricola, cognome, nome, cds));
				
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List <Studente> getStudentiIscrittiCorso(Corso c) {

		final String sql = "SELECT studente.matricola, cognome, nome, cds\r\n" + 
				"FROM studente, iscrizione\r\n" + 
				"WHERE studente.matricola = iscrizione.matricola AND codins = ?;";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cds = rs.getString("cds");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				int matricola = rs.getInt("matricola");

				studenti.add(new Studente(matricola, cognome, nome, cds));
				
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	
	public List <Studente> getStudente(int matr) {

		final String sql = "SELECT *\r\n" + 
				"FROM studente\r\n" + 
				"WHERE studente.matricola = ?;";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cds = rs.getString("cds");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				int matricola = rs.getInt("matricola");

				studenti.add(new Studente(matricola, cognome, nome, cds));
				
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public boolean verificaIscrizione(Studente studente, Corso corso) {

		final String sql = "SELECT *\r\n" + 
				"FROM iscrizione\r\n" + 
				"WHERE matricola = ? AND codins = ?;";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			ResultSet rs = st.executeQuery();

			conn.close();
			
			if (rs.next()) return true;
			return false;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public boolean iscriviStudenteCorso(Studente studente, Corso corso) {

		final String sql = "INSERT ()";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			int numRighe = st.executeUpdate();

			conn.close();
			
			if (numRighe == 1) return true;
			else return false;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
