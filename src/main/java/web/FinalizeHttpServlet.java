package web;

import model.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IRepositoryCatalog;
import dao.RepositoryCatalog;

@WebServlet(urlPatterns="/finalize")
public class FinalizeHttpServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();

		IRepositoryCatalog catalog;
		try {
			catalog = new RepositoryCatalog("jdbc:hsqldb:hsql://localhost/workdb");
			User user = (User)session.getAttribute("user");
			List<Ad> ads = (List<Ad>)session.getAttribute("ads");

			catalog.users().add(user);
			catalog.save();
			int id = catalog.users().getMaxId();
			for(Ad ad : ads) {
					catalog.ads().add(ad);
					catalog.save();
					ad.setId(catalog.ads().getMaxId());
					catalog.save();
					System.out.println("Zapisano !!");
				}
			catalog.saveAndClose();
			session.removeAttribute("user");
			session.removeAttribute("ads");
			response.sendRedirect("index.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	
	
}
