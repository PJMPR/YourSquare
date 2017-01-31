package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ad;

public class AdMapper implements IMapResultSetIntoEntity<Ad>{

	public Ad map(ResultSet rs) throws SQLException {
		Ad a = new Ad();
		a.setId(rs.getInt("id"));
		a.setTitle(rs.getString("title"));
		a.setTitle(rs.getString("content"));
		a.setFee(rs.getInt("fee"));
		return a;
	}

}
