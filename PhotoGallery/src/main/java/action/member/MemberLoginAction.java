package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberLoginAction
 */
@WebServlet("/member/login.do")
public class MemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. 수신 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 파라미터 수신 
		String m_id  = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");

		//3. m_id에 해당되는 회원정보 가져오기
		MemberVo user = MemberDao.getInstance().selectOne(m_id);
		
		if(user == null) {
			//Session Tracking
			response.sendRedirect("login_form.do?reason=fail_id");
			return;
		}
		
		//m_pwd 체크
		if(user.getM_pwd().equals(m_pwd)==false) {
			response.sendRedirect("login_form.do?reason=fail_pwd&m_id=" + m_id);
			return;
		}
		
		
		//로그인 정보 세션에 넣기 
		//사용자의 요청정보를 관리하는 request객체가 가지고 있는 세션id에 해당되는 공간 정보를 읽어온다.
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		
		//메인 페이지 이동(URL)
		//현재 경로 : 			 /member/login.do
		response.sendRedirect("../photo/list.do");
	}

}

