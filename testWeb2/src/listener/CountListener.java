package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import entity.Student;

public class CountListener implements ServletContextListener,
		HttpSessionListener, ServletRequestListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		// if(application.getAttribute("set")==null){
		// Set<String> set=new HashSet<String>();
		// application.setAttribute("set", set);
		// }

		int num = 0;
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		// String ip= request.getRemoteAddr();
		// System.out.println("ip:"+ip);
		// Set<String> set=(Set<String>)application.getAttribute("set");
		// boolean flag=set.add(ip);
		// if(se.getSession().isNew()){
		num++;
		// }
		application.setAttribute("num", num);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("请求");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		int num = (Integer) sce.getServletContext().getAttribute("num");
		System.out.println("application销毁");
		List<Student> list = new ArrayList<Student>();
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "123456");
			Statement stat = (Statement) conn.createStatement();
			int rs = stat.executeUpdate("update count set num=" + num + " where id=1");
			if (rs>0) {
				System.out.println("保存成功");
			}else{
				System.out.println("保存失败");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		int num =0;
		List<Student> list = new ArrayList<Student>();
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "123456");
			Statement stat = (Statement) conn.createStatement();
			ResultSet rs = stat.executeQuery("select num from count where id=1");
			if (rs.next()) {
				num=rs.getInt("num");
			}
			 sce.getServletContext().setAttribute("num",num);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
