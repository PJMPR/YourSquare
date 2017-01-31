package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

public class UserMapper implements IMapResultSetIntoEntity<User>{

	public User map(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setSurname(rs.getString("surname"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("password"));
		return u;
	}

}
