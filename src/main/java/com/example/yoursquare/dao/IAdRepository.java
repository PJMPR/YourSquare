package com.example.yoursquare.dao;

import java.util.List;

import com.example.yoursquare.model.*;

public interface IAdRepository extends IRepository<Ad> {
	
	public List<Ad> byUser(User user);
}