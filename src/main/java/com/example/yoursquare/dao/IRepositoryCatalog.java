package com.example.yoursquare.dao;


public interface IRepositoryCatalog {

	public IUserRepository users();
	public IAdRepository ads();
	public IMessageRepository messages();
	
	public void saveAndClose();
	
}