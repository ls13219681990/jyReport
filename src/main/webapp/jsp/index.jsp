<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <style>
        body {
            margin: 0 0 0 0;
        }
    </style>
</head>

<body>
<IMG width="100%" height="99%" src="ext/resources/icons/bg4.JPG"></IMG>
</body>
</html>