package com.example.yoursquare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.yoursquare.model.Message;

public class MessageRepository {

private Connection connection;

	private String createTableSql = "CREATE TABLE message("
					+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
					+ "idMessage INT,"
					+ "fromUser VARCHAR(20),"
					+ "toUser VARCHAR(20),"
					+ "title VARCHAR(20),"
					+ "content VARCHAR(20),"
					+ "sendDate DATE,"
					+ ")";
	private Statement createTable;


	private String selectByIdSql = "SELECT * FROM message WHERE idMessage=?";
	private String selectAllSql = "SELECT * FROM message";

	private PreparedStatement selectById;
	private PreparedStatement selectAll;

	public MessageRepository(Connection connection) {
		this.connection = connection;

		try {
			createTable = connection.createStatement();

			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("message")){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql);
			selectById = connection.prepareStatement(selectByIdSql);
			selectAll = connection.prepareStatement(selectAllSql);


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Message get(int message){
		try{

			selectById.setInt(1, message);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				Message result = new Message();
				result.setMessage(rs.getInt("message"));
				result.setFromUser(rs.getString("fromUser"));
				result.setToUser(rs.getString("ToUser"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				//result.setDate(rs.getDate("sendDate"));
				return result;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<Message> getAll(){
		try{
			List<Message> result = new ArrayList<Message>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				Message m = new Message();
				m.setMessage(rs.getInt("message"));
				m.setFromUser(rs.getString("fromUser"));
				m.setToUser(rs.getString("ToUser"));
				m.setTitle(rs.getString("title"));
				m.setContent(rs.getString("content"));
				//m.setDate(rs.getDate("sendDate"));
				result.add(m);
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

}