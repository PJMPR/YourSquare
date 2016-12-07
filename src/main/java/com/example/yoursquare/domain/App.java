package com.example.yoursquare.domain;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.example.yoursquare.model.*;


import com.example.yoursquare.dao.*;
import com.example.yoursquare.dao.uow.*;

public class App {
	public static void main( String[] args )
	{
	String url = "jdbc:hsqldb:hsql://localhost/workdb";
	try {
		Connection connection = DriverManager.getConnection(url);
		IRepositoryCatalog catalog = new RepositoryCatalog(new UnitOfWork(connection), connection);
		
		User klient1 = new User();
		klient1.setName("John");
		klient1.setSurname("Smith");
		klient1.setAdress("Wall Street");
		klient1.setZipcode("11-100");
		klient1.setCity("New York");
		klient1.setRegion("Manhattan");
		klient1.setCountry("USA");
		klient1.setPhone("456432236");
		klient1.setEmail("jsmith@gmail.com");
		klient1.setPassword("DonaldTrump1234");

		catalog.users().add(klient1);
		
		System.out.println( "Dodaje pierwszego klienta" );
		
		User klient2 = new User();
		klient2.setName("Hillary");
		klient2.setSurname("Clinton");
		klient2.setAdress("Door Street");
		klient2.setZipcode("11-100");
		klient2.setCity("New York");
		klient2.setRegion("Manhattan");
		klient2.setCountry("USA");
		klient2.setPhone("6576543");
		klient2.setEmail("hillary_clinton@gov.com");
		klient2.setPassword("LOL4321");

		catalog.users().add(klient2);
		
		System.out.println( "Dodaje drugiego klienta" );

		
		Ad ad1 = new Ad();
		ad1.setTitle("Room for sale");
		ad1.setFee(100);
		ad1.setAdress("Wall Street");
		ad1.setCity("New York");
		ad1.setZipcode("11-100");
		ad1.setSpace(20);
		ad1.setFurnished(true);
		ad1.setActive(true);
		//ad1.setAddDate("12/2/05");
		//ad1.setEndDate("Room for sale");
		//ad1.setRoom("Room for sale");
		ad1.setGallery("http://imgur.com/AgHjsu8");
		ad1.setContent("I have [...]");
		

		catalog.ads().add(ad1);
		
		List<Ad> ads = catalog.ads().byUser(klient1);
		
        System.out.println( "Klient1 dodaje ogłoszenie" );
		 
		 Message msg1 = new Message();
			msg1.setFromUser("1");
			msg1.setToUser("2");
			msg1.setTitle("Wybory Prezydenckie");
			msg1.setContent("Wybory w USA zostały tak naprawde prezydentem miał być Rudy. ");
			
			catalog.messages().add(msg1);
			
			List<Message> message1 = catalog.messages().byUser(klient1);
			
	        System.out.println( "Klient1 wysyła wiadomość" );
	        
	        Message msg2 = new Message();
  			msg2.setFromUser("1");
  			msg2.setToUser("2");
  			msg2.setTitle("RE: Wybory Prezydenckie");
  			msg2.setContent("A tam marian gadasz. ");
  			
  			catalog.messages().add(msg2);
			
			List<Message> message2 = catalog.messages().byUser(klient2);
			
	        System.out.println( "Klient2 wysyła wiadomość" );
			
  			catalog.saveAndClose();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
System.out.println( "Koniec" );
}
}
