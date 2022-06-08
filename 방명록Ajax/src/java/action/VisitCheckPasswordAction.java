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
		//1. �������ڵ� ����
		request.setCharacterEncoding("utf-8");
		
		//2. �Ķ���� ����
		int    idx   = Integer.parseInt(request.getParameter("idx"));
		String c_pwd = request.getParameter("c_pwd");
		
		//3. idx�� �ش�Ǵ� �Խù� ������ ���;� �Ѵ�. 
		VisitVo vo = VisitDao.getInstance().selectOne(idx);
		
		//3-1. ��� ���� ���� üũ, ����ڰ� �Է��� ��й�ȣ(c_pwd)�� ���� ��й�ȣ(pwd)�� ��
		boolean bResult = vo.getPwd().equals(c_pwd);

		
		//4. json�� ����
		JSONObject json = new JSONObject();
		json.put("result", bResult);//{"result":true}
		String json_str = json.toJSONString();
		
		//5. ��û������ ���� ó���Ѵ�. 
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_str);
	}

}
