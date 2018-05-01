<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="SearchIndexedDocs" method="GET">
    <p>Enter your query.        
    <input type="text" id="query" name="query" /></p>
<p>Submit button.
    <input type="submit" name="Submit" value="Submit" /></p>
</form>

<%=(String)request.getAttribute("result")%>

</body>
</html>