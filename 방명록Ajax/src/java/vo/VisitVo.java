package vo;

public class VisitVo {
	int idx;
	String name;
	String content;
	String pwd;
	String ip; 
	String regdate;
	
	
	
	//new VisitVo()
	public VisitVo() {
		super();
	}

	//직접 전달되는 데이터 
	//new VisitVo("일길동", "내용", "1234");
	public VisitVo(String name, String content, String pwd) {
		super();
		this.name = name;
		this.content = content;
		this.pwd = pwd;
	}
	
	//수정용 포장 데이터
	//new VisitVo(1, ㅋ"일길동", "내용", "1234");
	public VisitVo(int idx, String name, String content, String pwd) {
		super();
		this.idx = idx;
		this.name = name;
		this.content = content;
		this.pwd = pwd;
	}
	
	//수정용 포장 데이터
	public VisitVo(int idx, String name, String content, String pwd, String ip) {
		super();
		this.idx = idx;
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
