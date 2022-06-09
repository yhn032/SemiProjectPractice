package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteAction
 */
@WebServlet("/photo/delete.do")
public class PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// /photo/delete.do?p_idx=1
		
		//1. �Ķ���� ����
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//2. p_idx�� �ش��ϴ� vo 1�� �о����
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//3. ���ε��� ������ �����ϴ� ������ 
		String web_path="/upload/";
		String path    = request.getServletContext().getRealPath(web_path);
		
		//4. ������ ���ؼ� ���� ����
		File f = new File(path, vo.getP_filename());
		f.delete();
		
		//5. DB delete
		int res = PhotoDao.getInstance().delete(p_idx);
		
		//6. ��Ϻ���� �̵�
		response.sendRedirect("list.do");

	}

}

