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
		
		//인덱스에 해당하는 레코드 전체를 vo로 포장해서 읽어와서 바인딩한다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		
		//insert할 때 \r\n -> <br>로 바꾸어 놨으니 다시 출력할 때는 반대로 바꾸어서 보여주고, 다시 update할 때는 또 바꾸어서 저장해야 한다.
		//content => <br> -> \r\n
		String content = vo.getContent().replaceAll("<br>", "\r\n");
		
		//변경된 내용을 다시 세팅한다.
		vo.setContent(content);
		
		request.setAttribute("vo", vo);
		
		
		//forward
		String forward_page = "visit_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
