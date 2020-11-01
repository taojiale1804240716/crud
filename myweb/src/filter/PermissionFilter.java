package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = { "*.jsp" })
public class PermissionFilter implements Filter {
	private String notCheckPath;// 不用过滤的请求地址，从web.Xml中获取

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;// 转化类型
		String path = request.getServletPath();// 获取url-pattern的请求地址
		System.out.println(path);//
		if (notCheckPath.indexOf(path) == -1) {// 当请求地址在不需要过滤的里面
			HttpSession session = request.getSession();// 得到会话对象
			if (session.getAttribute("currentUser") == null) {// 当没有登录的时候
				request.setAttribute("info", "没有访问权限");
				request.getRequestDispatcher("/error.jsp").forward(request, arg1);
			} else {// 已经登录就放行
				arg2.doFilter(request, arg1);
			}
		} else {// 请求地址不需要过滤直接放行
			arg2.doFilter(request, arg1);
		}
		// doFilter(arg0,arg1,arg2);//
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		 notCheckPath = arg0.getInitParameter("notCheckPath");
		 System.out.println(notCheckPath);
		 System.out.println("显示");
	}

}
