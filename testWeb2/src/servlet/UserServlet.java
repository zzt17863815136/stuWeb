package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null || type.equals("showLogin")) {
				showLogin(request, response);

			} else if (type.equals("doLogin")) {
				doLogin(request, response);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ×ª·¢
 catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		 String name="";
		  
		  Cookie[] cookies=request.getCookies(); 
		  if(cookies!=null){
		  for(int i=0;i<cookies.length;i++){
		  if(cookies[i].getName().equals("userName")){
		  name=cookies[i].getValue();
		  break;
		  
		  }
		  
		  }
		  }
		
		
		request.setAttribute("name", name);
		request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		int time = Integer.parseInt(request.getParameter("time"));

		User searchUser = new User();
		searchUser.setUsername(username);
		searchUser.setPassword(password);
		UserDao ud = new UserDao();
		User user = ud.searchByUserNameAndPassword(searchUser);
		if (user != null) {

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			Cookie cookie = new Cookie("userName", user.getUsername());
			if (time == 1) {
				cookie.setMaxAge(0);
			} else if (time == 2) {
				cookie.setMaxAge(30);
			} else if (time == 3) {
				cookie.setMaxAge(60);
			}

			response.addCookie(cookie);

			request.getRequestDispatcher("WEB-INF/success.jsp").forward(request,
					response);

		} else {

			request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
