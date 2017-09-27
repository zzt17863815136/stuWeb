<%@ page language="java" import="java.util.*,entity.*,dao.*"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'students.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">

<style>
#contianer {
	width: 600px;
	margin: 50px auto;
}
.fenye{
margin:0;
padding:0;
margin-left:150px;
}
.fenye li{
list-style:none;
float:left;
font-size:14px;
line-height:140%;
border:1px solid #ddd;
text-align:center;
color:#337ab7;
padding:6px 12px;
margin-left:-1px;
}
.fenye li:hover{
background:#eee;
cursor:pointer;
}
.fenye li:first-child{
border-top-left-radius:4px;
border-bottom-left-radius:4px;
}
.fenye li:last-child{
border-top-right-radius:4px;
border-bottom-right-radius:4px;
}
.fenye .active{
color:#fff;
background-color:#337ab7;
border-color:#337ab7;
}
.fenye .active:hover{
color:#fff;
background-color:#337ab7;
border-color:#337ab7;
}
#t1 .xuanzhong{
background-color:yellow;
}
#u1 li{
list-style: none;
float: left;
margin-top: 10px;
width: 180px;
}
#u1 li input{
width: 80px;
height: 40px
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
 var ye=${ye};
 var maxYe=${maxYe};
 $(".fenye li:first").click(function(){
 if(ye>1){
 location.href="stu?ye="+(ye-1);
 }
 })
 
 $(".fenye li:first").mouseover(function(){
 if(ye<=1){
 		$(this).css("cursor","not-allowed");
 }
 })
 
 
 
  $(".fenye li:last").click(function(){
  if(ye<maxYe){
location.href="stu?ye="+(ye+1);
 }
 
 })
 
  $(".fenye li:last").mouseover(function(){
 if(ye>=maxYe){
 		$(this).css("cursor","not-allowed");
 }
 })
 
 $("[name=numpage]").click(function(){
 ye=$(this).html();
 location.href="stu?ye="+ye;
  
 })
 
 //$("[name=numpage]").removeClass("active");
// $("[name=numpage]").eq(0).addClass("active");
 
 $("tr").click(function(){
 $("tr").removeClass("xuanzhong");
 $(this).addClass("xuanzhong");
 
 })
 
 $(".del").click(function(){
 var id=$(".xuanzhong").children().html();
 location.href="del?id="+id;
 
 })
 
 $(".modify").click(function(){
 var id=$(".xuanzhong").children().html();
 var name=$(".xuanzhong").children().next().html();
 var sex=$(".xuanzhong").children().next().next().html();
 var age=$(".xuanzhong").children().next().next().next().html();
 location.href="modify?id="+id+"&name="+name+"&sex="+sex+"&age="+age;
 })
 
 
 
 })
</script>
</head>

<body>


<form action="search" method="post">	
<div id="contianer">
   <div>
   <a>姓名</a>
   <input type="text" style="width:80px" name="searchName"/>
   <a style="margin-left: 5px">性别</a>
   <input type="text" style="width:80px" name="searchSex"/>
   <a style="margin-left: 5px">年龄</a>
   <input type="text" style="width:80px" name="searchAge"/>
    <a style="margin-left: 5px">班级</a>
    <select style="width: 80px;height: 26px">
      <option>java一班</option>
      <option>综合班</option>
      <option>UI一班</option>
      <option>H5一班</option>
  </select>
  <input type="submit" value="查询" style="width: 80px;margin-left: 10px"/>
   </div>
   </form>	
   <br/>

	<table class='table  table-bordered table-striped table-hover' id="t1">
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
<ul class="fenye">
		<li>上一页</li>
		<%
		int ye=(Integer)request.getAttribute("ye");
		int begin=ye;
		int end=begin+4;
		int maxYe=(Integer)request.getAttribute("maxYe");
		
		if(end>=maxYe){
		end=maxYe;
		begin=end-4;
		}
		
		for(int i=begin;i<=end;i++){ 
		
		%>
		
		<li name="numpage" <%if(ye==i) {out.print("class='active'");}%> ><%=i%></li>
		<%} %>
		<li>下一页</li>	
	</ul>
	<br/><br/>
<ul id="u1">
<li>
<a href="addStudent.jsp">
<input type="button" value="新增"/>
</a>
</li>
<li>

<input type="button" value="修改" class="modify"/>

</li>
<li>
<input type="button" value="删除" class="del"/>	
</li>
</ul>

</div>

</body>
</html>
