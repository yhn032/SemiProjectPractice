package action.photo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyFormAction
 */
@WebServlet("/photo/modify_form.do")
public class PhotoModifyFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /photo/modify_form.do?p_idx=44
		
		//1. �Ķ���� ����
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//2. p_idx�� �ش��ϴ� ��ü ��ȸ
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//������ <br> -> \r\n���� �ٲ�� ��
		String content = vo.getP_content().replaceAll("<br>", "\r\n");
		vo.setP_content(content);
		
		//3. request binding
		request.setAttribute("list", vo);
		
		
		//forward
		String forward_page = "photo_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
