package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.mappers.*;
import dao.uow.*;

public class RepositoryCatalog implements IRepositoryCatalog {

	IUnitOfWork uow;
	Connection connection;

	public RepositoryCatalog(String url) throws SQLException, ClassNotFoundException {
		super();

		Class.forName("org.hsqldb.jdbcDriver");
		this.connection = DriverManager.getConnection(url);
		this.uow = new UnitOfWork(this.connection);
	}

	public RepositoryCatalog(IUnitOfWork uow, Connection connection) {
		super();
		this.uow = uow;
		this.connection = connection;
	}

	
	public IUserRepository users() {
		return new UserRepository(connection, new UserMapper(), uow);
	}

	public IAdRepository ads() {
		return new AdRepository(connection, new AdMapper(), uow);
	}

	public void saveAndClose() {
		try{
		uow.commit();
		connection.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}

	public void save() {
		try {
			this.uow.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
