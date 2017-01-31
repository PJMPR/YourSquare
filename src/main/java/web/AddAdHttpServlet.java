package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

@WebServlet(urlPatterns = "/addAd")
public class AddAdHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String fee = request.getParameter("fee");
		// sprawdzic parametry url
		if (title == null  || title.equals("") && content==null || content.equals("") && fee==null || fee.equals("")) {
			response.sendRedirect("addAd.html");
		} 
			User user = (User) session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("addUser.html");
		}
		
		Ad ad = new Ad();
		ad.setTitle(title);
		ad.setContent(content);
		ad.setFee(Integer.parseInt(fee));
		List<Ad> ads = new ArrayList<Ad>();
		if(session.getAttribute("ads")!=null)
			ads =(List<Ad>) session.getAttribute("ads");
		ads.add(ad);
		session.setAttribute("ads", ads);
		response.sendRedirect("addAd.html");

	}

}
