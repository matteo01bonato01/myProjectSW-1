package it.isa.progetto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreaTabellaEvento {
	static String url = "jdbc:db2://localhost:25000/SAMPLE:retrieveMessagesFromServerOnGetMessage=true;";
	static Connection con;
	static Statement stmt, stmtcheck;
	
	
	public void esegui() {
		// CONNESSIONE A DB2
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: "); 
			System.err.println(e.getMessage());
		}
		
		
		/********** TABELLA: ARTISTA ************/
		try {
			con = DriverManager.getConnection(url,"matteo","NuovaOpelCorsa");
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("create table EVENTO " +
        			"(ID integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), " + /******* ATTENZIONE ALLA PARENTESII!!!! ****/
					"NOME_EVENTO char(32) NOT NULL, " +
					"NOME_LOCATION char(32), " +
					"NOME_ARTISTA char(33), " +
					"DATA_EVENTO date NOT NULL, " +
					"foreign key(NOME_ARTISTA) references ARTISTA(NOME_ARTISTA) on DELETE SET NULL on UPDATE NO ACTION, " +
					"foreign key(NOME_LOCATION) references LOCATION(NOME_LOCATION) on DELETE SET NULL on UPDATE NO ACTION, " +
					"PRIMARY KEY(ID));");
        	
		} catch(SQLException ex) {
			System.err.println("ERRORE su CREATE EVENTO: "+ex.getMessage());
		}
		
		/********** POPOLO UTENTIREGISTRATI ************/
		try {			
			
			stmt.executeUpdate("insert into EVENTO(NOME_EVENTO, NOME_LOCATION, NOME_ARTISTA, DATA_EVENTO) " + 
			     "values('Zucchero in Festa', 'Castello Carrarese', 'Zucchero Fornaciari', '2022-06-05');");

			stmt.executeUpdate("insert into EVENTO(NOME_EVENTO, NOME_LOCATION, NOME_ARTISTA, DATA_EVENTO) " + 
				     "values('Pinguini Indoor', 'Unipol Arena', 'Pinguini Tattici Nucleari', '2024-04-27');");

			stmt.executeUpdate("insert into EVENTO(NOME_EVENTO, NOME_LOCATION, NOME_ARTISTA, DATA_EVENTO) " + 
				     "values('Trench Tour', 'Unipol Arena', 'Twenty One Pilots', '2019-02-06');");

			stmt.executeUpdate("insert into EVENTO(NOME_EVENTO, NOME_LOCATION, NOME_ARTISTA, DATA_EVENTO) " + 
				     "values('Pucci Show', 'Castello Carrarese', 'Andrea Pucci', '2022-06-08');");
			
		} catch(SQLException ex) {
		System.err.println("ERRORE sugli INSERT into EVENTO: "+ex.getMessage());
		}
		
			
	}//esegui
}
