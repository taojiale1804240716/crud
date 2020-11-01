package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.dao.UserDao;
import vo.Page;
import vo.User;

/**
 * Servlet implementation class ajaxQuery
 */
@WebServlet("/ajaxQuery")
public class ajaxQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxQuery() {
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
		//首先拿到客户端的参数
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String queryParams=request.getParameter("queryParams");
		String pageParams=request.getParameter("pageParams");
		Gson gson=new GsonBuilder().serializeNulls().create();
		
		User user=new User();
		Page page=new Page();
		
		if(queryParams!=null){
			user=gson.fromJson(queryParams, User.class);//实例化用户
		}
		HashMap<String,Object> mapuser=gson.fromJson(queryParams, HashMap.class);
		String province=(String)mapuser.get("provinceName");
		user.setprovincial(province);
		System.out.println("**************");
		System.out.println(user.toString());
		
		//实力化页面刷新信息
		HashMap<String,Object> mapPage=gson.fromJson(pageParams, HashMap.class);
		page.setPageSize(Integer.parseInt((String) mapPage.get("pageSize")));
		page.setSort((String) mapPage.get("sort"));
		page.setSortOrder((String)mapPage.get("sortOrder"));		
		page.setPageNumber(Integer.parseInt((String) mapPage.get("pageNumber")));	
		
		//String s=request.getParameter("s");
		UserDao userdao=new UserDao();
		ArrayList<User> rows=userdao.query(user, page);//所有的使用者的数量 
		int total=userdao.count(user, page);//记录总条数
//		System.out.println("总共有："+userdao.count(user, page));
//		
//		System.out.println(rows.toString());
//		System.out.println(rows.size());
		//System.out.println("查询参数"+queryParams);
		//System.out.println("分页参数"+pageParams);
		//System.out.println(s);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("rows", rows);
		result.put("total", total);
		//将haspmap转化成Json
		String jsonstr=gson.toJson(result);
		//发送Json字符串到前端
		PrintWriter out=response.getWriter();//得到输出流
		System.out.println(jsonstr);
		out.print(jsonstr);
		out.flush();
		out.close();
		
		
	}

}
