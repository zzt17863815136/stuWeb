<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	

  </head>
  
  <body>
    <table class='table  table-bordered table-striped table-hover' >
		<thead>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ stus}" var="stu">
			<tr>
				<td>
					${stu.id }
				</td>
				<td>
					${stu.name }
				</td>
				<td>
					${stu.sex }
				</td>
				<td>
					${stu.age }
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
