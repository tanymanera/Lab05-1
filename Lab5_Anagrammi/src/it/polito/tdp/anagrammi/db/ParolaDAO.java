package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParolaDAO {

	public List<List<String>> isCorrect(Set<String> listAnagrammi) {

		String sql = "select id, nome from parola where nome = ?;";

		List<String> errati = new ArrayList<String>();
		List<String> corretti = new ArrayList<String>();
		List<List<String>> result = new ArrayList<List<String>>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			for (String anagramma : listAnagrammi) {
				st.setString(1, anagramma);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					corretti.add(anagramma);
				} else {
					errati.add(anagramma);
				}

			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		result.add(errati);
		result.add(corretti);
		
		return result;
	}

}
