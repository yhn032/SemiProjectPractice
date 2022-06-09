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
		// /photo/insert.do?p_subject=����&p_content=����&p_photo=a.jpg
		
		//�α��� ���� �о����
		MemberVo user = (MemberVo)request.getSession().getAttribute("user");
		
		if(user == null) { //������ ����� ���
			response.sendRedirect("../member/login_form.do?reason=session_timeout");
			return;
		}
		
		
		
		
		//1. ������ġ ����
		//ServletContext app = request.getServletContext();
		//String path = app.getRealPath(web_path);
		
		String web_path = "/upload/"; //�� ���
		String path = request.getServletContext().getRealPath(web_path); //������(�޼��� ü�̴� ���)
		
		//2. maxsize
		int max_size = 1024*1024*100;
		
		//3. ���� ���ε� ó�� ��ü 					      ������ġ ����ũ��  ���ڵ�   �������ϸ� -> ���� ����
		MultipartRequest mr = new MultipartRequest(request, path, max_size, "utf-8", new DefaultFileRenamePolicy());
		
		//4. ���ε�� ���ϸ� ���ϱ� 
		String p_filename = "no_file";
		File f           = mr.getFile("p_photo");
		if(f != null) {
			p_filename = f.getName();//���ε�� �����̸� ���Ѵ�.
		}
		
		//5. �Ķ���� ����
		String p_subject = mr.getParameter("p_subject");
		String p_content = mr.getParameter("p_content").replaceAll("\r\n", "<br>"); //json���� �����ϴ� �������� Ư�����ڰ� �����ϸ� ������ �߻��ϹǷ�, 
		
		
		//6. ip���� ����
		String p_ip = request.getRemoteAddr();
		
		//6-1. �α����� ������� m_idx ���ϱ� 
		int m_idx   = user.getM_idx();
		
		//7. Vo�� ����
		PhotoVo vo = new PhotoVo(p_subject, p_content, p_filename, p_ip, m_idx);
		
		//8. DB insert
		int res = PhotoDao.getInstance().insert(vo);
		
		//9. ���� ������ ȣ��
		response.sendRedirect("list.do");
	}

}
