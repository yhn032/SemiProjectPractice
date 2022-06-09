package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/insert.do")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. ���� ���ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2. �Ķ���� �ޱ�
		String m_name    = request.getParameter("m_name");
		String m_id      = request.getParameter("m_id");
		String m_pwd     = request.getParameter("m_pwd");
		String m_zipcode = request.getParameter("m_zipcode");
		String m_addr    = request.getParameter("m_addr");
		
		
		//3. ip���ϱ� 
		String m_ip      = request.getRemoteAddr();
		
		//4. MemberVo����
		MemberVo vo = new MemberVo(m_name, m_id, m_pwd, m_zipcode, m_addr, "�Ϲ�", m_ip);
		
		//5. DB Insert
		MemberDao.getInstance().insert(vo);
		
		//6. ��� ����
		response.sendRedirect("list.do");

	}

}
