package kr.mem.model;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO { // DAO�� ����� ��� ����, ���� �غ�! (�޼ҵ� ����)

	// db����
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// 1.�����ε� (�� ����? compile�Ҷ� db�� �����ϴ°��� �ƴ� �����Ҷ� ���� -�������� ����)
	// �ʱ�ȭ�� (��ü ������ �� �Ǹ��� ������ �ѹ� ������ ��)
	static {
		try { 				// Memory�� ���� DriverManager(����̹��� �����ϴ� Ŭ����)�� ������ ��- DM�� ���� �����
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ������� ����̹� �ε�.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ���ᰴü ����� �۾�
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; // protocol. ���� �ٲٸ� �ȵ�
		String user = "hr";
		String password = "hr";

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int memberInsert(MemberVO vo) {
		// 1.interface�� �����س��� 2.�޼ҵ� ����� �ҷ� ���
		conn = getConnect();
		// MyBatis framework: ���߿� String SQL�� ������ ���� ���
		String SQL = "insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		int cnt = -1; // -1(�� ����/������ �ǹ�)
		try {
			ps = conn.prepareStatement(SQL); // pre-> sql�� ���� ������ �������� ��������� ���� �ٸ��͵� ����
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt = ps.executeUpdate(); // ������ ���� ������ ������

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public ArrayList<MemberVO> memberAllList() { // db table ���� ������ ������ =��ȸ => �迭�� ���·� ����Ǿ� ������ vo��ü�ڵ��� ������ �Ǿ�����, ũ�� ���
													// ���� ������ �� �ִ� API
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL = "select * from tblMem order by num desc";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery(); // rs=Ŀ�� -> �ڷ� ��ĭ�� �̵��ϸ鼭 ������ ������
			while (rs.next()) { // 1���� ���ڵ带 1)db�� ������ "�����ְ�", 2)array�� "���"
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");

				// �����ְ�
				MemberVO vo = new MemberVO(num, name, phone, addr, lat, lng);
				// ����ְ�
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	public int memberDelete(int num) {
		conn = getConnect();
		String SQL = "delete from tblMem where num=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate(); // 1
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//�������� ��� -> vo(����)�� ��� �����ش�: ��ü �����̴ϱ�
	public MemberVO memberContent(int num) { 
		MemberVO vo = null; //���� ����
		conn = getConnect();
		String SQL = "select * from tblMem where num=?";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num); //���� num ���ڿ� �ش��ϴ� �ڷḦ ���뿡 set/��� ��Ų��
			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				vo = new MemberVO(num, name, phone, addr, lat, lng); //�������� ����(������)�� ����Ͽ� �׸��� ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		conn=getConnect();
		String SQL="update tblMem set phone=?, addr=? where num=?";
		int cnt = -1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setString(1, vo.getPhone());
			ps.setString(2, vo.getAddr());
			ps.setInt(3, vo.getNum());
			cnt=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return cnt;
		
	}
	
	
	

}
