package com.example.yoursquare.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.yoursquare.dao.mappers.*;
import com.example.yoursquare.dao.uow.IUnitOfWork;
import com.example.yoursquare.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UserRepository extends RepositoryBase<User> implements IUserRepository {
	
	
	public UserRepository(Connection connection,
			IMapResultSetIntoEntity<User> mapper,
			IUnitOfWork uow) {
		super(connection, mapper, uow);
	}

	@Override
	protected String tableName() {
		return "user";
	}
	
	protected String createTableSql() {
			return "CREATE TABLE user ("
					+ "id` INT NOT NULL,"
					+ "name VARCHAR(45) NULL,"
					+ "surname VARCHAR(45) NULL,"
					+ "adress VARCHAR(45) NULL,"
					+ "zipcode VARCHAR(45) NULL,"
					+ "city VARCHAR(45) NULL,"
					+ "region VARCHAR(45) NULL,"
					+ "country VARCHAR(45) NULL,"
					+ "phone VARCHAR(45) NULL,"
					+ "email VARCHAR(45) NULL,"
					+ "password VARCHAR(45) NOT NULL,"
					+ "PRIMARY KEY (idclient))";
	}
	
	@Override
	protected String insertSql() {
		return "INSERT INTO user(name,surname,adress,zipcode,city,region,country,phone,email,password) VALUES(?,?,?,?,?,?,?,?,?,?)";
	}

	@Override
	protected String updateSql() {
		return "UPDATE user set name=?, surname=?, adress=?, zipcode=?, city=?, region=?, country=?, phone=?, email=?, password=? WHERE id=?";
	}


	@Override
	protected void setupInsert(User entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setString(2, entity.getSurname());
		insert.setString(3, entity.getAdress());
		insert.setString(4, entity.getZipcode());
		insert.setString(5, entity.getCity());
		insert.setString(6, entity.getRegion());
		insert.setString(7, entity.getCountry());
		insert.setString(8, entity.getPhone());
		insert.setString(9, entity.getEmail());
		insert.setString(10, entity.getPassword());
		
	}

	@Override
	protected void setupUpdate(User entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setString(2, entity.getSurname());
		update.setString(3, entity.getAdress());
		update.setString(4, entity.getZipcode());
		update.setString(5, entity.getCity());
		update.setString(6, entity.getRegion());
		update.setString(7, entity.getCountry());
		update.setString(8, entity.getPhone());
		update.setString(9, entity.getEmail());
		update.setString(10, entity.getPassword());
		update.setInt(11, entity.getId());
		
}

	public List<User> withName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}