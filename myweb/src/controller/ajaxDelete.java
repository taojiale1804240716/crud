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

import model.dao.UserDao;

/**
 * Servlet implementation class ajaxDelete
 */
@WebServlet("/ajaxDelete")
public class ajaxDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxDelete() {
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
		String a=request.getParameter("ids");//得到userName的列表
		System.out.println("显示的数据");
		System.out.println(a);
		String userNamelist[]=a.split(",|，");
		UserDao dao=new UserDao();
		HashMap<String,Object> map=new 	HashMap<String,Object> ();
		
		for(String userName:userNamelist){
			//将数组里的数值循环删除
			try {
				dao.delete(userName);//删除userName
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				map.put("code", 1);
				map.put("info", "删除失败");
				e.printStackTrace();
			}
		}
		map.put("code", 0);//
		map.put("info", "删除成功");
		//将字符串分割
		System.out.println(map);
		PrintWriter out = response.getWriter();
		String jsonstr = new Gson().toJson(map);
		out.print(jsonstr);
       
		out.flush();
		out.close();
		
	}

}
