package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.EventType;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<EventType> getAllEventi(Nerc nerc) {
		
		String sql = "SELECT  date_event_began, date_event_finished,customers_affected, event_type_id FROM poweroutages WHERE nerc_id=?";
		List<EventType> eventi = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				eventi.add(new EventType(res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getInt("customers_affected"),res.getInt("event_type_id")));
			}

			conn.close();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return eventi;
	}

}
