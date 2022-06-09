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
 * Servlet implementation class PhotoModifyAction
 */
@WebServlet("/photo/modify.do")
public class PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. ���� ���ڵ�
		request.setCharacterEncoding("utf-8");
		
		//2. �Ķ���� ����
		// /photo/modify.do?p_idx=7&p_subject=������+��������&p_content=������+����+%0D%0A������+�Ұ�
		int p_idx       = Integer.parseInt(request.getParameter("p_idx"));
		String p_subject = request.getParameter("p_subject");
		String p_content = request.getParameter("p_content").replaceAll("\r\n", "<br>");
		
		//3. �����ϴ� ������� ip��� 
		String p_ip = request.getRemoteAddr();
		
		//4. �����ϱ� 
		PhotoVo vo = new PhotoVo();
		vo.setP_idx(p_idx);
		vo.setP_subject(p_subject);
		vo.setP_content(p_content);
		vo.setP_ip(p_ip);
		
		//5. DB Update
		int res = PhotoDao.getInstance().update(vo);
		
		//6. ��Ϻ��� �̵�
		response.sendRedirect("list.do");

	}

}

