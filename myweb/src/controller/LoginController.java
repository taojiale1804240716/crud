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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
      
		
		//1.取数据
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uname=request.getParameter("userName");
		String upassword=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		String check=request.getParameter("tjlogin");		
	    System.out.println(check);
	    String forwordpath="";
		if(check.equals("on"))
		{
			// 为名字和姓氏创建 Cookie      
	        Cookie  userName= new Cookie("userName",request.getParameter("userName"));// 中文转码
	        Cookie password = new Cookie("password",request.getParameter("password"));
	        
	        // 为两个 Cookie 设置过期日期为 24 小时后
	        userName.setMaxAge(60*60*24*7); 
	        password.setMaxAge(60*60*24*7); 
	        
	        // 在响应头中添加两个 Cookie
	        response.addCookie(userName);
	        response.addCookie(password);
	        System.out.println("已经创建响应头");	        	      	        
		}
		
		  // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
		//2.处理数据
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(-1);//session永远不过期
		String saveCode=(String)session.getAttribute("verityCode");
		
//		
//		//利用cookie得到数据
//		Cookie[] cookies = null;
//		cookies=request.getCookies();
//		if(cookies!=null) {
//		for (Cookie c : cookies) {
//			String name = c.getName();// 获取Cookie名称
//			if ("userName".equals(name)) {
//				uname = c.getValue();// 获取Cookie的值
//			}
//			if ("password".equals(name)) {
//				upassword = c.getValue();
//			}
//		}
//		}
		
		if(!vcode.equalsIgnoreCase(saveCode)){//验证码不正确
			request.setAttribute("errorInfo", "验证码错误");
			forwordpath="/error.jsp";
		}
		else{
			UserDao dao=new UserDao();
			User user = null;
			try {
				user = dao.getUser(uname);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(user==null){
		    	//用户名不存在
		    	request.setAttribute("errorInfo", "用户名不存在");//将数据放入会话里面
		    	
		    	forwordpath="/error.jsp";
		    }
		    else{//用户名存在
		    	//验证密码
		    	if(!user.getPassword().equals(upassword)){//密码错误
		    		request.setAttribute("errorInfo", "密码错误");//将数据放入会话里面
		    		forwordpath="/error.jsp";
		    	}
		    	else{//密码正确		    		
		    		session.setAttribute("currentUser", user);
		    		forwordpath="/main.jsp";
		    	}
		    }
		}
		
		RequestDispatcher rs=request.getRequestDispatcher(forwordpath);		
		rs.forward(request, response);
		
	}

}
