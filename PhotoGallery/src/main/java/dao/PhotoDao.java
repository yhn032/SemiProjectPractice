package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.PhotoVo;

public class PhotoDao {
	//single-ton : 객체 1개만 생성해서 사용하자
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		//객체가 생성되어 있지 않으면 만들어라.
		if (single == null) {
			single = new PhotoDao();
		}
		//이전에 만들어 놨던 객체를 그대로 반환한다.
		return single;
	}

	//외부에서 생성하지 못하도록 접근제한. 객체는 getInstance메소드를 통해서만 생성가능.
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//p_idx에 해당되는 객체 1건 조회하기
	//idx에 해당되는 객체 1건 조회 
	public PhotoVo selectOne(int p_idx) {

		PhotoVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from photo where p_idx = ?";

		try {
			//1. connection 얻어오기
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3. pstmt setting
			pstmt.setInt(1, p_idx);
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				vo = new PhotoVo();
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subjet"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setM_idx(rs.getInt("m_idx"));

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
	
	
	//전체 조회
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = new ArrayList<PhotoVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from photo order by p_idx desc";

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
				PhotoVo vo = new PhotoVo();
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subjet"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setM_idx(rs.getInt("m_idx"));
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
	
	public int insert(PhotoVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into photo values(seq_photo_p_idx.nextVal, ?, ?, ?, ?, sysdate, ?)";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_filename());
			pstmt.setString(4, vo.getP_ip());
			pstmt.setInt(5, vo.getM_idx());
			
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
	
	public int delete(int p_idx) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from photo where p_idx=?";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setInt(1, p_idx);
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
	
	public int update(PhotoVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update photo set p_subjet = ?, p_content=?, p_ip=?, p_regdate=sysdate where p_idx=?";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_ip());
			pstmt.setInt(4, vo.getP_idx());
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

	public int update_filename(PhotoVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update photo set p_filename=? where p_idx=?";
		try {
			//1. Connection얻어오기
			conn = DBService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setString(1, vo.getP_filename());
			pstmt.setInt(2, vo.getP_idx());
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
