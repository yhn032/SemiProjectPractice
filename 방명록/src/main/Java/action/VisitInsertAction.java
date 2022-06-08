package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitInsertAction
 */
@WebServlet("/visit/insert.do")
public class VisitInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		//자바에서 enter는 \r\n인데 이 값을 입력하면 그대로 db에 저장되지만 
		//출력은 html로 되지 않는가? html에서 enter는 <br>이므로 enter기능을 처리하려면 이를 바꾸어 주어야 한다.
		//content에 \r\n -> <br>로 바꿔라 
		content = content.replaceAll("\r\n", "<br>");
		
		String ip  = request.getRemoteAddr();
		
		VisitVo vo = new VisitVo(name, content, pwd);
		vo.setIp(ip);
		vo.setContent(content);
		
		int res = VisitDao.getInstance().insert(vo);
		
		response.sendRedirect("list.do");

	}

}

