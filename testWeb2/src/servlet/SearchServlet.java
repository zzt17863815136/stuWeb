package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import entity.Student;

public class SearchServlet extends HttpServlet{
public void doGet(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
	 request.setCharacterEncoding("utf-8");
	StudentDao stuDao =new StudentDao();	
	String name=request.getParameter("searchName");
	 String sex=request.getParameter("searchSex");
	 int age=-1;
	 if(!request.getParameter("searchAge").equals("")){
	 age = Integer.parseInt(request.getParameter("searchAge"));}
	 Student stu=new Student();
	 stu.setName(name);
	 stu.setSex(sex);
	 stu.setAge(age);
	 List<Student> list =stuDao.searchByCondition(stu);
	 request.setAttribute("stus", list);
	 try {
		request.getRequestDispatcher("searchStudent.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void doPost(HttpServletRequest request,HttpServletResponse response){
	
	try {
		doGet(request,response);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
