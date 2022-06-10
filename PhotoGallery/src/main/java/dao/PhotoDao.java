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
	//single-ton : ��ü 1���� �����ؼ� �������
	static PhotoDao single = null;

	public static PhotoDao getInstance() {

		//��ü�� �����Ǿ� ���� ������ ������.
		if (single == null) {
			single = new PhotoDao();
		}
		//������ ����� ���� ��ü�� �״�� ��ȯ�Ѵ�.
		return single;
	}

	//�ܺο��� �������� ���ϵ��� ��������. ��ü�� getInstance�޼ҵ带 ���ؼ��� ��������.
	private PhotoDao() {
		// TODO Auto-generated constructor stub
	}
	
	//p_idx�� �ش�Ǵ� ��ü 1�� ��ȸ�ϱ�
	//idx�� �ش�Ǵ� ��ü 1�� ��ȸ 
	public PhotoVo selectOne(int p_idx) {

		PhotoVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from photo where p_idx = ?";

		try {
			//1. connection ������
			conn = DBService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			//3. pstmt setting
			pstmt.setInt(1, p_idx);
			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			if (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
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
	
	
	//��ü ��ȸ
	public List<PhotoVo> selectList() {

		List<PhotoVo> list = new ArrayList<PhotoVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from photo order by p_idx desc";

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
				PhotoVo vo = new PhotoVo();
				vo.setP_idx(rs.getInt("p_idx"));
				vo.setP_subject(rs.getString("p_subjet"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_filename(rs.getString("p_filename"));
				vo.setP_ip(rs.getString("p_ip"));
				vo.setP_regdate(rs.getString("p_regdate"));
				vo.setM_idx(rs.getInt("m_idx"));
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
	
	public int insert(PhotoVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into photo values(seq_photo_p_idx.nextVal, ?, ?, ?, ?, sysdate, ?)";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_filename());
			pstmt.setString(4, vo.getP_ip());
			pstmt.setInt(5, vo.getM_idx());
			
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
	
	public int delete(int p_idx) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from photo where p_idx=?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setInt(1, p_idx);
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
	
	public int update(PhotoVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update photo set p_subjet = ?, p_content=?, p_ip=?, p_regdate=sysdate where p_idx=?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getP_subject());
			pstmt.setString(2, vo.getP_content());
			pstmt.setString(3, vo.getP_ip());
			pstmt.setInt(4, vo.getP_idx());
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

	public int update_filename(PhotoVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update photo set p_filename=? where p_idx=?";
		try {
			//1. Connection������
			conn = DBService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setString(1, vo.getP_filename());
			pstmt.setInt(2, vo.getP_idx());
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
