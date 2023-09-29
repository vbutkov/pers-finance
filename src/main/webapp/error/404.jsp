<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
<button onclick="history.back()">Back to Previous Page</button>
<br />
<p><b>Error code:</b> ${pageContext.errorData.statusCode} - Page not found </p>
<br />
</body>
</html>