package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

public interface IMapResultSetIntoEntity<TEntity extends IHaveId> {

	public TEntity map(ResultSet rs) throws SQLException;
	
}