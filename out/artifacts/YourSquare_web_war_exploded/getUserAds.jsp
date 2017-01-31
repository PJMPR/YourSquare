<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

id: <input type="text" id="personid"/>
<button id="search">get ads</button>
<div id="content"></div>

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
	var input = $("#userid");
	var btn = $("#search");
	var content = $("#content")
	
	btn.click(function(){
		var id = input.val();
		
		$.ajax({
			  url: "rest/users/"+id+"/ads",
			  method:'GET',
			  success: function( result ) {
			    content.html('<p>'+result[0].title+'</p>'+'<p>'+result[0].content+'</p>'+'<p>'+result[0].fee+'</p>')
			  }
			});
	})
	
	
	
</script>

</body>
</html>