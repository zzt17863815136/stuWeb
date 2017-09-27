<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
   <%Cookie[] cookies=request.getCookies();
   boolean flag=false;
   if(cookies!=null){
   for(int i=0;i<cookies.length;i++){
   if(cookies[i].getName().equals("userName")){
   flag=true;
   break;
   }
   }
   }
  User user= new User();
   if(flag){
   session.setAttribute("user", user);
   
   }
    %>
  
  
  
  
  
  <%if(session.getAttribute("user")==null){
  
  response.sendRedirect("user");
  
  } %>
   管理页面 <br>
  </body>
</html>
