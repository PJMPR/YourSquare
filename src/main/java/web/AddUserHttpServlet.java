package web;

import model.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/addUser")
public class AddUserHttpServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String nameSession = request.getParameter("name");
		String surnameSession = request.getParameter("surname");
		String emailSession = request.getParameter("email");
		String passwordSession = request.getParameter("password");

		if (nameSession != null && !nameSession.equals("") && surnameSession != null && !surnameSession.equals("") && emailSession != null && !emailSession.equals("") && passwordSession != null && !passwordSession.equals("")) {


		User user = new User();
		user.setName(nameSession);
		user.setSurname(surnameSession);
		user.setEmail(emailSession);
		user.setPassword(passwordSession);
		session.setAttribute("user", user);
			response.sendRedirect("addAd.html");
	}
	else {
			response.sendRedirect("addUser.html");
		}



	}
	
}
