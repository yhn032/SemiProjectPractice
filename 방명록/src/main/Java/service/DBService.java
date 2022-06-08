package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBService {
	
	DataSource ds;
	
	
	//single-ton : 객체 1개만 생성해서 사용하자
	static DBService single = null;

	public static DBService getInstance() {

		//객체가 생성되어 있지 않으면 만들어라.
		if (single == null) {
			single = new DBService();
		}
		//이전에 만들어 놨던 객체를 그대로 반환한다.
		return single;
	}

	//외부에서 생성하지 못하도록 접근제한. 객체는 getInstance메소드를 통해서만 생성가능.
	private DBService() {
		// TODO Auto-generated constructor stub
		//JNDI(Java Naming Directory(검색기구) Interface) : 이름으로 검색해서 interface(DataSource)를 얻어내는 기술
		
		//DBService라는 객체가 만들어질 때(생성자의 영역에서), 톰캣이 미리 생성한 자원 정보를 Naming을 통해서 이미 생성된 커넥션 객체들을 가져온다.
		//DataSource 인터페이스를 구현상속한 BasicDataSource를 사용하는 방법이 명시된 DataSource의 객체 ds를 가져오는 과정이다. 
		
		
		try {
			//1. InitialContext 생성 
			InitialContext ic = new InitialContext();
			
			//2. Resource의 저장소 Context정보 구하기 (서버 내부에서 접근할 수 있는 상수 => java:comp/env)
			//Context context = (Context)ic.lookup("java:comp/env");//lookup함수의 반환값이 Object이므로 강제로 형변환 해줘야 한다.
			
			//3. Context내의 Resource 정보를 획득한다.
			//ds = (DataSource)context.lookup("jdbc/oracle_test");//(강제 형변환)
			
			//2+3 한번에
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/oracle_test");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//DBCP에 저장된 커넥션 얻어오기 
	public Connection getConnection() throws SQLException {
		
		
		//DataSource인터페이스로부터 커넥션 객체를 얻어오는 과정
		//DBService를 사용하는 사용자 측에서 에러를 직접 처리하도록 예외를 위임해야 한다.
		return ds.getConnection();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
