package web.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Ad;

@WebFilter(urlPatterns={"/overview.jsp"})
public class OverviewFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		
		HttpSession session = req.getSession();
	
		List<Ad> ads =(List<Ad>)session.getAttribute("ads") ;
		if (ads == null || ads.size() == 0){
			
			resp.sendRedirect("addAd.html");
			
		}
		
		else{
			chain.doFilter(req, resp);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}