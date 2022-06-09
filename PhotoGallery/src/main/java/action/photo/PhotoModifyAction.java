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
		
		//1. 수신 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 파라미터 수신
		// /photo/modify.do?p_idx=7&p_subject=수정을+시작하지&p_content=삭제는+성공+%0D%0A수정은+불가
		int p_idx       = Integer.parseInt(request.getParameter("p_idx"));
		String p_subject = request.getParameter("p_subject");
		String p_content = request.getParameter("p_content").replaceAll("\r\n", "<br>");
		
		//3. 수정하는 사용자의 ip얻기 
		String p_ip = request.getRemoteAddr();
		
		//4. 포장하기 
		PhotoVo vo = new PhotoVo();
		vo.setP_idx(p_idx);
		vo.setP_subject(p_subject);
		vo.setP_content(p_content);
		vo.setP_ip(p_ip);
		
		//5. DB Update
		int res = PhotoDao.getInstance().update(vo);
		
		//6. 목록보기 이동
		response.sendRedirect("list.do");

	}

}

