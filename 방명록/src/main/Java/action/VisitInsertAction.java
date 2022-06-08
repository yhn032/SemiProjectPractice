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
		
		//�ڹٿ��� enter�� \r\n�ε� �� ���� �Է��ϸ� �״�� db�� ��������� 
		//����� html�� ���� �ʴ°�? html���� enter�� <br>�̹Ƿ� enter����� ó���Ϸ��� �̸� �ٲپ� �־�� �Ѵ�.
		//content�� \r\n -> <br>�� �ٲ�� 
		content = content.replaceAll("\r\n", "<br>");
		
		String ip  = request.getRemoteAddr();
		
		VisitVo vo = new VisitVo(name, content, pwd);
		vo.setIp(ip);
		vo.setContent(content);
		
		int res = VisitDao.getInstance().insert(vo);
		
		response.sendRedirect("list.do");

	}

}

