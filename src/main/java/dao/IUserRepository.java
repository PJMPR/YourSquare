package dao;

import java.util.List;

import model.*;

public interface IUserRepository extends IRepository<User> {
	
	public List<User> withName(String name);
}
