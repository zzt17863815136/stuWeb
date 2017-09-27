<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addStudent.jsp' starting page</title>
    
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
  <form action="modify" method="post">
    <div style="width:200px;margin: 50px auto;">
    	<a>姓名</a>
    	<input type="text" style="height: 30px"  value="${param.name}"/><br/><br/>
    	<a>性别</a>
    	<input type="text" style="height: 30px"  value="${param.sex}"/><br/><br/>
    	<a>年龄</a>
    	<input type="text" style="height: 30px"  value="${param.age}"/><br/><br/>
    	
    	 
    <input type="submit" value="确定" style="width: 80px;height: 40px;margin-top: 20px;margin-left: 60px"/>	
    
    
    </div>
    </form>
  </body>
</html>
