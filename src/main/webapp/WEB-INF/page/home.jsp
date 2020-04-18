<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/page/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>home</title>
</head>
<body>
    <h3 id="alipay">Alipay</h3>
</body>
<script>
    $('#alipay').on('click',function (e) {
        location.href = '<%=basePath%>index';
    });
</script>
</html>