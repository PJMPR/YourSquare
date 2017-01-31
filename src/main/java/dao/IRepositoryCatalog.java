package dao;


public interface IRepositoryCatalog {

	public IUserRepository users();
	public IAdRepository ads();
	public void save();
	public void saveAndClose();
	
}