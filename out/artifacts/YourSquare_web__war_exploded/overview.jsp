<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Ad"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user") ;
		List<Ad> adList = (List<Ad>) session.getAttribute("ads");
	%>
	<h2><%=user.getName()%> <%=user.getSurname()%></h2>
	
	<ol>
		<%for(Ad ad: adList){ %>
		<li><%=ad.getTitle() %> <%=ad.getContent()%> <%=ad.getFee()%></li>
		<%}%>
	</ol>
	<a href="finalize">Finalizuj</a>
</body>
</html>