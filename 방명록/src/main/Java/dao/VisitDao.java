package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.VisitVo;

public class VisitDao {
	//single-ton : ��ü 1���� �����ؼ� �������
	static VisitDao single = null;

	public static VisitDao getInstance() {

		//��ü�� �����Ǿ� ���� ������ ������.
		if (single == null) {
			single = new VisitDao();
		}
		//������ ����� ���� ��ü�� �״�� ��ȯ�Ѵ�.
		return single;
	}

	//�ܺο��� �������� ���ϵ��� ��������. ��ü�� getInstance�޼ҵ带 ���ؼ��� ��������.
	private VisitDao() {
		// TODO Auto-generated constructor stub
	}
	
	//���� ��ȸ
	public List<VisitVo> selectList() {

		List<VisitVo> list = new ArrayList<VisitVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit order by idx desc";

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
				VisitVo vo = new VisitVo();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				
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
	
	public int insert(VisitVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into visit values(seq_visit_idx.nextVal, ?, ?, ?, ?, sysdate)";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			
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
	
	public int delete(int idx) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from visit where idx = ?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setInt(1, idx);
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
	
	//idx�� ���� ��ü 1�� ���ϱ�
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit where idx=?";

		try {
			//1. connection ������
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);

			//3. ResultSet ������ 
			rs = pstmt.executeQuery();
			//4. ����
			if (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
				vo = new VisitVo(rs.getInt("idx"), rs.getString("name"), rs.getString("content"), rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				

				//list�� �߰� 

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
	
	public int update(VisitVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update visit set name = ?, content = ?, pwd = ? , ip = ?, regdate = sysdate where idx=?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getIdx());
			
			
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
