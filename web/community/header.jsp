<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String baseHref = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("baseHref",baseHref);
%>
<base href="${baseHref}">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
