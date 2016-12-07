package com.example.yoursquare.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.yoursquare.dao.mappers.*;
import com.example.yoursquare.dao.uow.IUnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog {

	IUnitOfWork uow;
	Connection connection;
	
	
	public RepositoryCatalog(IUnitOfWork uow, Connection connection) {
		super();
		this.uow = uow;
		this.connection = connection;
	}

	
	public IUserRepository users() {
		return new UserRepository(connection, new UserMapper(), uow);
	}

	public IAdRepository ads() {
		return new AdRepository(connection, new AdMapper(), ads(), uow);
	}

	public IMessageRepository messages() {
		return new MessageRepository(connection, new MessageMapper(), uow);
	}

	public void saveAndClose() {
		try{
		uow.commit();
		connection.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}

}
