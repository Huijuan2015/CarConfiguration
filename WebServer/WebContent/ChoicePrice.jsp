<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Information</title>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
	<h1>Bill Information</h1>

<%@ page import = "java.util.Map" %>
<%


out.print("<table>");
out.print("<tr><th>Name</th><th>Choice</th><th>price</th></tr>");
for(int i=0;i<50;i++) {
	if(request.getAttribute("row1" + i)==null)
		break;
	out.print("<tr><td>" + request.getAttribute("row1" + i) + "</td>");
	out.print("<td>" + request.getAttribute("row2" + i) + "</td>");
	out.print("<td>" + request.getAttribute("row3" + i) + "</tr></td>");
}

out.print("</table>");
%>


</body>
</html>