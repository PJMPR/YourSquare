package dao;

import java.util.List;

import model.*;

public interface IAdRepository extends IRepository<Ad> {
	
	public List<Ad> byUser(User user);
}