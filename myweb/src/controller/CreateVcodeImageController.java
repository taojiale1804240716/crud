package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.CreateVerificationCodeImage;

/**
 * Servlet implementation class CreateVcodeImageController
 */
@WebServlet("/CreateVcodeImageController.do")
public class CreateVcodeImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()s
     */
    public CreateVcodeImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
	   CreateVerificationCodeImage CreateCodeImage=new CreateVerificationCodeImage();
	   
	   String vCode=CreateCodeImage.createCode();
	   System.out.println(vCode);
	   
	   HttpSession session=request.getSession();
	   
	   session.setAttribute("verityCode", vCode);
	  
	   response.setContentType("img/jpeg");
	   //����������Ҫˢ��һ��
	   response.setHeader("Pragma", "NO-cache");
	   response.setHeader("Cache-Control", "NO-cache");
       //������֤��ʧЧ��ʱ��	    
	   response.setDateHeader("EXpires", 0);
	   //��ͼƬ��Ϣ���ֽ�������ʽ���͹�ȥ
	   BufferedImage image=CreateCodeImage.CreateImage(vCode);
	   ServletOutputStream out=response.getOutputStream();//�õ��ֽ�������
	   ImageIO.write(image, "JPEG", out);
	   out.flush();
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
