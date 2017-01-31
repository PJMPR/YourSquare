import dao.IRepositoryCatalog;
import dao.RepositoryCatalog;
import dao.uow.UnitOfWork;
import model.Ad;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class main {

    @SuppressWarnings("deprecation")
    public static void main( String[] args )
    {

        String url = "jdbc:hsqldb:hsql://localhost/workdb";
        try {

            Connection connection = DriverManager.getConnection(url);
            IRepositoryCatalog catalog = new RepositoryCatalog(new UnitOfWork(connection), connection);


            User klient1 = new User();
            klient1.setName("John");
            klient1.setSurname("Smith");
            klient1.setEmail("jsmith@gmail.com");
            klient1.setPassword("DonaldTrump1234");

            catalog.users().add(klient1);

            System.out.println( "Dodaje pierwszego klienta" );

            Ad ad1 = new Ad();
            ad1.setTitle("Room for sale");
            ad1.setFee(100);
            ad1.setContent("I have a dream");


            catalog.ads().add(ad1);

            List<Ad> ads = catalog.ads().byUser(klient1);

            System.out.println( "Klient1 dodaje ogłoszenie" );

            catalog.saveAndClose();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println( "Koniec" );

    }
}