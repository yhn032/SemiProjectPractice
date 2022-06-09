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
		
		//1. ���� ���ڵ� ���� 
		request.setCharacterEncoding("utf-8");
		
		//2. �Ķ���� ����
		String m_id = request.getParameter("m_id");
		
		//3. m_id�� �ش�Ǵ� ȸ������ 1�� �������� 
		MemberVo vo = MemberDao.getInstance().selectOne(m_id);
		
		//true -> ��� ����, false -> ��� �Ұ���
		//boolean bResult = (vo==null); or
		
		//4. Ŭ���̾�Ʈ���� m_id ��� ���� ����
		boolean bResult = false;
		if(vo==null) bResult = true;
		
		//5. ��� ���۵����� ����(JSON)
		JSONObject json = new JSONObject();
		json.put("result", bResult);
		
		String json_str = json.toJSONString(); //{"result" : true}
		
		//����
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_str);
	}

}
