package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import entity.Student;

public class StudentDao extends BaseDao{

	public 	int searchCount() {
		int result=0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/170810", "root", "123456");
			Statement stat = (Statement) conn.createStatement();
			ResultSet rs = (ResultSet) stat
					.executeQuery("select count(id) as c from student");
			if (rs.next()) {
				result=rs.getInt("c");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}
	
	
	public List<Student> searchAll() {
		List<Student> list = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/170810", "root", "123456");
			Statement stat = (Statement) conn.createStatement();
			ResultSet rs = (ResultSet) stat
					.executeQuery("select * from student");
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List<Student> searchByBegin(int begin, int num) {
		List<Student> list = new ArrayList<Student>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/170810", "root", "123456");
			Statement stat = (Statement) conn.createStatement();
			ResultSet rs = (ResultSet) stat
					.executeQuery("select * from student limit " + begin + ","
							+ num);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}
	
	
	public boolean add(Student stu) {
		boolean flag=false;
		try {
		
			//生成SQL执行器
			 getStatement();
			// 5 执行sql语句
			String sql = "insert into student (name,sex,age) values('"
					+ stu.getName() + "','" + stu.getSex() + "',"
					+ stu.getAge() + ")";
			System.out.println(sql);
			int result = stat.executeUpdate(sql);
			
			// 6 处理结果
			
			if(result>0){
				flag=true;
			}
		

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 7 关闭连接
			closeAll();
		}
		return flag;
	}
	
	public boolean delete(int id) {
		boolean flag=false;
		getStatement();
		int result;
		try {
			result = stat.executeUpdate("delete from student where id="+id);
			if(result>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
	
		return flag;
	}
	
	public List<Student> searchByCondition(Student condition) {
		List<Student> list = new ArrayList<Student>();
		getStatement();
		String where="";
		if(!condition.getName().equals("")){
			where+=" and s.name like '%" + condition.getName() + "%'";
		} if(!condition.getSex().equals("")){
			where+=" and sex='" + condition.getSex() +"'";
		} if(condition.getAge()!=-1){
			where+=" and age=" + condition.getAge();
		}
		
		String sql="select s.* from student as s where 1=1 " + where;
		
		System.out.println(sql);
		
		
		try {
			rs = stat.executeQuery(sql);
			// 6 处理结果
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));				
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}					
		return list;
	}

}
