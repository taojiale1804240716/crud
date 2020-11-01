package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.dao.UserDao;
import tools.JdbcUtil;
import vo.User;

/**
 * Servlet implementation class ajaxUpdate
 */
@WebServlet("/ajaxUpdate")
public class ajaxUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//page.setSortOrder((String)mapPage.get("sortOrder"));
		String queryParams=request.getParameter("queryParams");
		//System.out.println(queryParams);
		Gson gson=new GsonBuilder().serializeNulls().create();
		HashMap<String,Object> mapuser=gson.fromJson(queryParams, HashMap.class);
		System.out.println(mapuser);
		System.out.println("数值");
		
		//JSONObject jsonObj = new JSONObject(queryParams);
		HashMap<String,Object> map=new 	HashMap<String,Object> ();
		
		String userName=(String)mapuser.get("userName");
		String chrname=(String)mapuser.get("trueName");
		String password=(String)mapuser.get("password");
		String email=(String)mapuser.get("email");
		String province=(String)mapuser.get("province");
		String city=(String)mapuser.get("city");
	//	user.setUserName(userName);
		//public User(String userName, String password, String chrName,String provincial,String city,String email) 
//       user.setChrName((String)mapuser.get("trueName"));
//       user.setPassword((String)mapuser.get("password"));
//       user.setemial((String)mapuser.get("email"));
//       user.setprovincial((String)mapuser.get("province"));
//       user.setcity((String)mapuser.get("city"));
        //System.out.println(user.toString());
		User user=new User(userName,password,chrname,province,city,email); 
		UserDao dao=new UserDao();
		try {
			dao.updateuser(user);//更新
			map.put("code", 0);
			map.put("info", "更新成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			map.put("code", 1);
			map.put("info", "更新失败");
			e.printStackTrace();
		}
		
		System.out.println(map);
		PrintWriter out = response.getWriter();
		String jsonstr = new Gson().toJson(map);
		out.print(jsonstr);
       
		out.flush();
		out.close();
		
	}

}
