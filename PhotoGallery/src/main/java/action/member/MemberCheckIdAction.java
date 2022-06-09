package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/member/check_id.do")
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /member/check_id.do?m_id=one
		
		//1. 수신 인코딩 설정 
		request.setCharacterEncoding("utf-8");
		
		//2. 파라미터 수신
		String m_id = request.getParameter("m_id");
		
		//3. m_id에 해당되는 회원정보 1건 가져오기 
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		//true -> 사용 가능, false -> 사용 불가능
		//boolean bResult = (vo==null); or
		
		//4. 클라이언트측에 m_id 사용 유무 전송
		boolean bResult = false;
		if(vo==null) bResult = true;
		
		//5. 결과 전송데이터 생성(JSON)
		JSONObject json = new JSONObject();
		json.put("result", bResult);
		
		String json_str = json.toJSONString(); //{"result" : true}
		
		//전송
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_str);
	}

}
