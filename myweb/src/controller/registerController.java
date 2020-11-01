package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.UserDao;
import vo.User;

/**
 * Servlet implementation class registerController
 */
@WebServlet("/registerController")
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//需要得到7条信息
		String uname = request.getParameter("userName");
		System.out.println(uname);
		String upassword = request.getParameter("password");
		String trueName = request.getParameter("trueName");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String email= request.getParameter("email");
		String role="admain"; 
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		 UserDao U=new UserDao();
		 User selectuser = null;
		 try {
			selectuser=U.getUser(uname);//查看是否重复
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//
		//实例化一个user
		 
		   User u=new User(role,uname,upassword,trueName,province,city,email);
		   try {
			   //将数据加入数据库
			if(selectuser!=null){//有重复的，那么报错
				map.put("code", "1");
				map.put("info", "密码重复");
			}
			else{
				U.add(u);//添加进入数据库
				map.put("code", "0");
				map.put("info", "成功注册");
			}
			U.add(u);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// RequestDispatcher rs=request.getRequestDispatcher(forwordpath);
			PrintWriter out = response.getWriter();
			String jsonstr = new Gson().toJson(map);
			out.print(jsonstr);
			out.flush();
			out.close();
	}

}
