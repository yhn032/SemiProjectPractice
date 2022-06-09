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
	//single-ton : ��ü 1���� �����ؼ� �������
	static MemberDao single = null;

	public static MemberDao getInstance() {

		//��ü�� �����Ǿ� ���� ������ ������.
		if (single == null) {
			single = new MemberDao();
		}
		//������ ����� ���� ��ü�� �״�� ��ȯ�Ѵ�.
		return single;
	}

	//�ܺο��� �������� ���ϵ��� ��������. ��ü�� getInstance�޼ҵ带 ���ؼ��� ��������.
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ü��ȸ 
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember order by m_idx";

		try {
			//1. connection ������
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
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

				//list�� �߰� 
				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//�ݵ�� �����ϴ� ����

			try {

				//����Ǿ� �ִ� ���¸� �����.(���� ��������)

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
	
	//m_idx�� �ش�Ǵ� ��ü 1�� ��ȸ 
	public MemberVo selectOne(int m_idx) {

		MemberVo  vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember where m_idx=?";

		try {
			//1. connection ������
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			
			//3. pstmt setting
			pstmt.setInt(1, m_idx);
			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			if (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
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
		} finally {//�ݵ�� �����ϴ� ����

			try {

				//����Ǿ� �ִ� ���¸� �����.(���� ��������)

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
	
	//m_id�� �ش�Ǵ� ��ü 1�� ��ȸ 
	public MemberVo selectOne(String m_id) {
		
		MemberVo  vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from memember where m_id = ?";
		
		try {
			//1. connection ������
			conn = DBService.getInstance().getConnection();
			
			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			
			//3. pstmt setting
			pstmt.setString(1, m_id);
			
			//3. ResultSet ������ 
			rs = pstmt.executeQuery();
			
			//4. ����
			if (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.
				
				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
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
		} finally {//�ݵ�� �����ϴ� ����
			
			try {
				
				//����Ǿ� �ִ� ���¸� �����.(���� ��������)
				
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
	public int insert(MemberVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into memember values(seq_member_m_idx.nextVal,?,?,?,?,?,?,?,sysdate)";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_grade());
			pstmt.setString(7, vo.getM_ip());
			//4. DML(insert/update/delete) : res�� ó���� ���� ���� ��ȯ�մϴ�. �ѹ��� ������ ������ 1�ٸ� ����ǹǷ� ������ �����ߴٸ� res�� �ݵ�� 1, ���࿡ res�� 0�̶�� ���Կ����� ����� ������� ���� ���̴�.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//�ݱ�(��������)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int delete(int m_idx) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from memember where m_idx = ?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setInt(1, m_idx);
			//4. DML(insert/update/delete) : res�� ó���� ���� ���� ��ȯ�մϴ�. �ѹ��� ������ ������ 1�ٸ� ����ǹǷ� ������ �����ߴٸ� res�� �ݵ�� 1, ���࿡ res�� 0�̶�� ���Կ����� ����� ������� ���� ���̴�.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//�ݱ�(��������)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int update(MemberVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		//										 1		 2		  3			   4		 5 			6		7								   8
		String sql = "update memember set m_name=?, m_id=?, m_pwd=?, m_zipcode=?, m_addr=?, m_grade=?, m_ip=?, m_regdate=sysdate where m_idx = ?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_zipcode());
			pstmt.setString(5, vo.getM_addr());
			pstmt.setString(6, vo.getM_grade());
			pstmt.setString(7, vo.getM_ip());
			pstmt.setInt(8, vo.getM_idx());
			//4. DML(insert/update/delete) : res�� ó���� ���� ���� ��ȯ�մϴ�. �ѹ��� ������ ������ 1�ٸ� ����ǹǷ� ������ �����ߴٸ� res�� �ݵ�� 1, ���࿡ res�� 0�̶�� ���Կ����� ����� ������� ���� ���̴�.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//�ݱ�(��������)
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
