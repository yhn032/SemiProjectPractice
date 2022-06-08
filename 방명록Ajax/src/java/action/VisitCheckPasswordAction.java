package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.VisitDao;
import vo.VisitVo;

/**
 * Servlet implementation class VisitCheckPasswordAction
 */
@WebServlet("/visit/check_pwd.do")
public class VisitCheckPasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /visit/check_pwd.do?idx=25&c_pwd=1234
		//1. 수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//2. 파라미터 수신
		int    idx   = Integer.parseInt(request.getParameter("idx"));
		String c_pwd = request.getParameter("c_pwd");
		
		//3. idx에 해당되는 게시물 정보를 얻어와야 한다. 
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//3-1. 비번 동일 여부 체크, 사용자가 입력한 비밀번호(c_pwd)와 원래 비밀번호(pwd)를 비교
		boolean bResult = vo.getPwd().equals(c_pwd);

		
		//4. json에 포장
		JSONObject json = new JSONObject();
		json.put("result", bResult);//{"result":true}
		String json_str = json.toJSONString();
		
		//5. 요청사항을 응답 처리한다. 
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_str);
	}

}
