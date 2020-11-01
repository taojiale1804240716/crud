package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DownloadDao;
import vo.Download;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet.do")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //D:\project\myweb\WebContent\files
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//1.获取要下载文件的路径
		
		String paString=null;
		//2.获取要下载文件的文件名
		String fileName="";
		//通过数据库的数据来获得这些数值
		int id=Integer.parseInt(request.getParameter("id"));
		DownloadDao dla=new DownloadDao();
		ArrayList<Download> list=null;
		try {
			list = dla.getDownloadList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Download d:list){
			if(id==d.getId()){
				paString=d.getPath();
			}
		}			
		 String path=request.getServletContext().getRealPath(paString);
		
		response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		
		//4.获取下载文件的输入流
		InputStream in=new FileInputStream(path);
		int len=0;
		
		byte[] buffer=new byte[1024];
		//7.使用FILEinputsteam将缓冲区的数据输出到客户端的浏览器
		ServletOutputStream out=response.getOutputStream();
		while((len=in.read(buffer))!=-1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
