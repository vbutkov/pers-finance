<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Exception page</title>
    <meta charset="utf-8">
</head>
<body>
<button onclick="history.back()">Back to Previous Page</button>
<br />
<p><b>Exception code:</b> ${pageContext.errorData.statusCode} - ${pageContext.errorData.throwable}</p>
<br />
</body>
</html>