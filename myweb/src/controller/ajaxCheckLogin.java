package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.UserDao;
import vo.User;

/**
 * Servlet implementation class ajaxCheckLogin
 */
@WebServlet("/ajaxCheckLogin.do")
public class ajaxCheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajaxCheckLogin() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		// 1.取数据
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uname = request.getParameter("userName");
		String upassword = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		// 用哈希图的数据结构进行存储
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 2.处理数据
		HttpSession session = request.getSession();

		String saveCode = (String) session.getAttribute("verityCode");// 得到正确的验证码
		String forwordpath = "";

		// 应该在这里设置一周免登录

		

		if (!vcode.equalsIgnoreCase(saveCode)) {// 验证码不正确
			map.put("code", 1);
			map.put("info", "验证码不正确");
		}
		// request.setAttribute("errorInfo", "验证码错误");
		// forwordpath="/error.jsp";

		else {
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
				map.put("code", 2);
				map.put("info", "用户名不存在");
				// request.setAttribute("errorInfo", "用户名不存在");//将数据放入会话里面

				// forwordpath="/error.jsp";
			} else {// 用户名存在
					// 验证密码
				if (!user.getPassword().equals(upassword)) {// 密码错误
					// forwordpath="/error.jsp";
					map.put("code", 3);
					map.put("info", "密码错误");
				} else {// 密码正确
					session.setAttribute("currentUser", user);
					map.put("code", 0);

					map.put("info", "登录成功");

				}
			}

		}
		// RequestDispatcher rs=request.getRequestDispatcher(forwordpath);
		System.out.println(map);
		PrintWriter out = response.getWriter();
		String jsonstr = new Gson().toJson(map);
		out.print(jsonstr);
       
		out.flush();
		out.close();
		// rs.forward(request, response);不用转发

	}

}
