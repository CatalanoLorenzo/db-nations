package org.java.dbnations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		final String url = "jdbc:mysql://localhost:3306/db_nations";
		final String user = "root";
		final String password = "root";
		try (Connection con = DriverManager.getConnection(url, user, password)) {

			readAllPassagerTable(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static final void readAllPassagerTable(Connection con) {

		final String sql = " SELECT c.name, c.country_id, r.name, c2.name " + " FROM countries c " + " JOIN regions r "
				+ " ON c.region_id = r.region_id " + " JOIN continents c2 " + " ON r.continent_id = c2.continent_id "
				+ " ORDER BY c.name; ";

		try {
			Scanner input = new Scanner(System.in);
			System.out.println("scegli un filtro");
			String filtro = input.nextLine();

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String nomeNazione = rs.getString(1);
				int idNazione = rs.getInt(2);
				String nomeRegione = rs.getString(3);
				String nomeNazione2 = rs.getString(4);
				
				
				 String riga = nomeNazione + "-" + idNazione + "-" + nomeRegione + "-" + nomeNazione2;
				
				
				if(riga.toLowerCase().contains(filtro.toLowerCase())) {
					System.out.println(riga);
					System.out.println("\n------------------------------\n");
				}
			}
			input.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}
}
