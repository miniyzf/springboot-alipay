<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>common</title>
</head>
<body>

<script type="text/javascript" src="<%=basePath%>static/js/jquery.min-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/base64ed.js"></script>
</body>
</html>