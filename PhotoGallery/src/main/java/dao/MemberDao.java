package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.MemberVo;

public class MemberDao {
	//single-ton : 객체 1개만 생성해서 사용하자
	static MemberDao single = null;

	public static MemberDao getInstance() {

		//객체가 생성되어 있지 않으면 만들어라.
		if (single == null) {
			single = new MemberDao();
		}
		//이전에 만들어 놨던 객체를 그대로 반환한다.
		return single;
	}

	//외부에서 생성하지 못하도록 접근제한. 객체는 getInstance메소드를 통해서만 생성가능.
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	//전체조회 
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember order by m_idx";

		try {
			//1. connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				MemberVo vo = new MemberVo();
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pwd(rs.getString("m_pwd"));
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_grade(rs.getString("m_grade"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));

				//list에 추가 
				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문

			try {

				//연결되어 있는 상태면 끊어라.(생성 역순으로)

				if (rs != null)
					rs.close(); //3
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close();//1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	//m_idx에 해당되는 객체 1건 조회 
	public MemberVo selectOne(int m_idx) {

		MemberVo  vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember where m_idx=?";

		try {
			//1. connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//3. pstmt setting
			pstmt.setInt(1, m_idx);
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				vo = new MemberVo ();
				
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pwd(rs.getString("m_pwd"));
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_grade(rs.getString("m_grade"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문

			try {

				//연결되어 있는 상태면 끊어라.(생성 역순으로)

				if (rs != null)
					rs.close(); //3
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close();//1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	//m_id에 해당되는 객체 1건 조회 
	public MemberVo selectOne(String m_id) {
		
		MemberVo  vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember where m_id = ?";
		
		try {
			//1. connection 얻어오기
			conn = DBService.getInstance().getConnection();
			
			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//3. pstmt setting
			pstmt.setString(1, m_id);
			
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();
			
			//4. 포장
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.
				
				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				vo = new MemberVo ();
				
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pwd(rs.getString("m_pwd"));
				vo.setM_zipcode(rs.getString("m_zipcode"));
				vo.setM_addr(rs.getString("m_addr"));
				vo.setM_grade(rs.getString("m_grade"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문
			
			try {
				
				//연결되어 있는 상태면 끊어라.(생성 역순으로)
				
				if (rs != null)
					rs.close(); //3
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close();//1
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	//insert 
	public int insert(MemberVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into memember values(seq_member_m_idx.nextVal,?,?,?,?,?,?,?,sysdate)";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_grade());
			pstmt.setString(7, vo.getM_ip());
			//4. DML(insert/update/delete) : res는 처리된 행의 수를 반환합니다. 한번의 삽입은 무조건 1줄만 수행되므로 삽입이 성공했다면 res는 반드시 1, 만약에 res가 0이라면 삽입연산이 제대로 수행되지 않은 것이다.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//닫기(열린역순)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int delete(int m_idx) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from memember where m_idx = ?";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setInt(1, m_idx);
			//4. DML(insert/update/delete) : res는 처리된 행의 수를 반환합니다. 한번의 삽입은 무조건 1줄만 수행되므로 삽입이 성공했다면 res는 반드시 1, 만약에 res가 0이라면 삽입연산이 제대로 수행되지 않은 것이다.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//닫기(열린역순)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int update(MemberVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		//										 1		 2		  3			   4		 5 			6		7								   8
		String sql = "update memember set m_name=?, m_id=?, m_pwd=?, m_zipcode=?, m_addr=?, m_grade=?, m_ip=?, m_regdate=sysdate where m_idx = ?";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_grade());
			pstmt.setString(7, vo.getM_ip());
			pstmt.setInt(8, vo.getM_idx());
			//4. DML(insert/update/delete) : res는 처리된 행의 수를 반환합니다. 한번의 삽입은 무조건 1줄만 수행되므로 삽입이 성공했다면 res는 반드시 1, 만약에 res가 0이라면 삽입연산이 제대로 수행되지 않은 것이다.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//닫기(열린역순)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
}
