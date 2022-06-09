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
		
		//1. 파라미터 수신
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		//2. p_idx에 해당하는 vo 1건 읽어오기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		//3. 업로드한 파일이 존재하는 절대경로 
		String web_path="/upload/";
		String path    = request.getServletContext().getRealPath(web_path);
		
		//4. 절대경로 구해서 파일 삭제
		File f = new File(path, vo.getP_filename());
		f.delete();
		
		//5. DB delete
		int res = PhotoDao.getInstance().delete(p_idx);
		
		//6. 목록보기로 이동
		response.sendRedirect("list.do");

	}

}

