package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDao;
import vo.User;

/**
 * Servlet implementation class cookielogin
 */
@WebServlet("/cookielogin")
public class cookielogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public cookielogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forwordpath = "";
		String uname=null ;
		String upassword =null;		
		// 利用cookie得到数据
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Cookie[] cookies = null;
		cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				String name = c.getName();// 获取Cookie名称
				if ("userName".equals(name)) {
				
					uname = c.getValue();// 获取Cookie的值
				}
				if ("password".equals(name)) {
					upassword = c.getValue();
				}
			}
		}

		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		// 2.处理数据
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-1);// session永远不过期
		String saveCode = (String) session.getAttribute("verityCode");

		// 不用验证码直接登录
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.getUser(uname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null) {
			// 用户名不存在
			request.setAttribute("errorInfo", "用户名不存在");// 将数据放入会话里面

			forwordpath = "/error.jsp";
		} else {// 用户名存在
				// 验证密码
			if (!user.getPassword().equals(upassword)) {// 密码错误
				forwordpath = "/error.jsp";
			} else {// 密码正确
				session.setAttribute("currentUser", user);
				forwordpath = "/main.jsp";
			}
		}
		RequestDispatcher rs=request.getRequestDispatcher(forwordpath);		
		rs.forward(request, response);
	}
	

}
