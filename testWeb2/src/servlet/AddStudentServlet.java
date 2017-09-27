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

public class AddStudentServlet extends HttpServlet{
 public void doGet(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
	 request.setCharacterEncoding("utf-8");
	 StudentDao stuDao =new StudentDao();
	 String name=request.getParameter("name");
	 String sex=request.getParameter("sex");
	 int age = Integer.parseInt(request.getParameter("age"));
	 Student stu=new Student();
	 stu.setName(name);
	 stu.setSex(sex);
	 stu.setAge(age);
	 stuDao.add(stu);
	 int nums=5;//一页多少条
		
		int total=stuDao.searchCount();
		int maxYe=0;
//		if(total%nums==0){
//			maxYe=total/nums;
//		}else{
//			maxYe=total/nums+1;
//		}
		int ye=1;
		maxYe=total%nums==0?total/nums:total/nums+1;
		
		
		
		
		if(null != request.getParameter("ye")){
			ye=Integer.parseInt(request.getParameter("ye"));
		}
		
		
		
		if(ye<1){
			ye=1;
		}
		if(ye>maxYe){
			ye=maxYe;
		}
		int begin=(ye-1)*5;
		
		
		List<Student> list = stuDao.searchByBegin(begin, nums);
		request.setAttribute("stus", list);
		request.setAttribute("ye", ye);
		request.setAttribute("maxYe", maxYe);
		try {
			request.getRequestDispatcher("WEB-INF/students.jsp").forward(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
