<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
pageContext.getSession().setAttribute("baseUrl", basePath);
pageContext.getSession().setAttribute("uploadUrl", basePath+"/upload/");
%>

<script type="text/javascript">
	var baseUrl= "${baseUrl}";
	var uploadUrl= "${uploadUrl}";
</script>

