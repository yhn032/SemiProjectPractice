package vo;

public class PhotoVo {
	int p_idx;
	String p_subject;
	String p_content;
	String p_filename;
	String p_ip;
	String p_regdate;
	int m_idx;
	
	public PhotoVo() {
		super();
	}

	
	//등록시 사용할 생성자
	public PhotoVo(String p_subject, String p_content, String p_filename, String p_ip, int m_idx) {
		super();
		this.p_subject = p_subject;
		this.p_content = p_content;
		this.p_filename = p_filename;
		this.p_ip = p_ip;
		this.m_idx = m_idx;
	}


	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public String getP_subject() {
		return p_subject;
	}

	public void setP_subject(String p_subject) {
		this.p_subject = p_subject;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_filename() {
		return p_filename;
	}

	public void setP_filename(String p_filename) {
		this.p_filename = p_filename;
	}

	public String getP_ip() {
		return p_ip;
	}

	public void setP_ip(String p_ip) {
		this.p_ip = p_ip;
	}

	public String getP_regdate() {
		return p_regdate;
	}

	public void setP_regdate(String p_regdate) {
		this.p_regdate = p_regdate;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	
	
	
	
}
