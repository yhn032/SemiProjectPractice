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
 * Servlet implementation class VisitModifyFormAction
 */
@WebServlet("/visit/modify_form.do")
public class VisitModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//�ε����� �ش��ϴ� ���ڵ� ��ü�� vo�� �����ؼ� �о�ͼ� ���ε��Ѵ�.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		
		//insert�� �� \r\n -> <br>�� �ٲپ� ������ �ٽ� ����� ���� �ݴ�� �ٲپ �����ְ�, �ٽ� update�� ���� �� �ٲپ �����ؾ� �Ѵ�.
		//content => <br> -> \r\n
		String content = vo.getContent().replaceAll("<br>", "\r\n");
		
		//����� ������ �ٽ� �����Ѵ�.
		vo.setContent(content);
		
		request.setAttribute("vo", vo);
		
		
		//forward
		String forward_page = "visit_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
