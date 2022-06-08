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
 * Servlet implementation class VisitModifyAction
 */
@WebServlet("/visit/modify.do")
public class VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//���� ���ڵ�
		response.setCharacterEncoding("utf-8");
		
		//�Ķ���� ����
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		//String������ ����� ���ο� String�� �����Ǳ� ������ �̷������� ���� �־���� �Ѵ�. �ȱ׷� ���� ������
		content = content.replaceAll("\r\n", "<br>");
		
		VisitVo vo = new VisitVo(idx, name, content, pwd);
		
		vo.setIp(ip);
		
		
		int res = VisitDao.getInstance().update(vo);
		
		//forward
		response.sendRedirect("list.do");

	}

}
