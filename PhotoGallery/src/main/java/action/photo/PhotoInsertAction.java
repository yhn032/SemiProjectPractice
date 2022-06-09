package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import vo.MemberVo;
import vo.PhotoVo;

/**
 * Servlet implementation class PhotoInsertAction
 */
@WebServlet("/photo/insert.do")
public class PhotoInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /photo/insert.do?p_subject=제목&p_content=내용&p_photo=a.jpg
		
		//로그인 정보 읽어오기
		MemberVo user = (MemberVo)request.getSession().getAttribute("user");
		
		if(user == null) { //세션이 만료된 경우
			response.sendRedirect("../member/login_form.do?reason=session_timeout");
			return;
		}
		
		
		
		
		//1. 저장위치 설정
		//ServletContext app = request.getServletContext();
		//String path = app.getRealPath(web_path);
		
		String web_path = "/upload/"; //웹 경로
		String path = request.getServletContext().getRealPath(web_path); //절대경로(메서드 체이닝 방식)
		
		//2. maxsize
		int max_size = 1024*1024*100;
		
		//3. 파일 업로드 처리 객체 					      저장위치 저장크기  인코딩   동일파일명 -> 변경 저장
		MultipartRequest mr = new MultipartRequest(request, path, max_size, "utf-8", new DefaultFileRenamePolicy());
		
		//4. 업로드된 파일명 구하기 
		String p_filename = "no_file";
		File f           = mr.getFile("p_photo");
		if(f != null) {
			p_filename = f.getName();//업로드된 파일이름 구한다.
		}
		
		//5. 파라미터 수신
		String p_subject = mr.getParameter("p_subject");
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>"); //json으로 포장하는 과정에서 특수문자가 존재하면 에러가 발생하므로, 
		
		
		//6. ip정보 수신
		String p_ip = request.getRemoteAddr();
		
		//6-1. 로그인한 사용자의 m_idx 구하기 
		int m_idx   = user.getM_idx();
		
		//7. Vo로 포장
		PhotoVo vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
		
		//8. DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//9. 메인 페이지 호출
		response.sendRedirect("list.do");
	}

}
