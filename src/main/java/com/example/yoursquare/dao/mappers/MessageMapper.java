package com.example.yoursquare.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.yoursquare.model.*;

public class MessageMapper implements IMapResultSetIntoEntity<Message>{

	public Message map(ResultSet rs) throws SQLException {
		Message m = new Message();
		m.setId(rs.getInt("id"));
		m.setFromUser(rs.getString("fromUser"));
		m.setToUser(rs.getString("toUser"));
		m.setTitle(rs.getString("title"));
		m.setContent(rs.getString("content"));
		m.setSendDate(rs.getDate("date"));
		return m;
	}

}
