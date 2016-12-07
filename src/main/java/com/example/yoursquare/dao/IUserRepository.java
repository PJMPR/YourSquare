package com.example.yoursquare.dao;

import java.util.List;

import com.example.yoursquare.model.*;

public interface IUserRepository extends IRepository<User> {
	
	public List<User> withName(String name);
}
