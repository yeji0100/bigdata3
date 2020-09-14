package kr.mem.model;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO { // DAO에 기능을 모두 저장, 연장 준비! (메소드 모음)

	// db연동
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// 1.동적로딩 (왜 동적? compile할때 db를 연결하는것이 아닌 실행할때 연결 -유지보수 유리)
	// 초기화블럭 (객체 생성할 때 맨먼저 무조건 한번 실행이 됨)
	static {
		try { 				// Memory에 들어가면 DriverManager(드라이버를 관리하는 클래스)랑 연결이 됨- DM를 통해 연결됨
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 실행시점 드라이버 로딩.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 연결객체 만드는 작업
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; // protocol. 절대 바꾸면 안됨
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
		// 1.interface를 구현해놓기 2.메소드 만들어 불러 사용
		conn = getConnect();
		// MyBatis framework: 나중에 String SQL을 밖으로 빼서 사용
		String SQL = "insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		int cnt = -1; // -1(은 실패/없다의 의미)
		try {
			ps = conn.prepareStatement(SQL); // pre-> sql을 먼저 날려서 컴파일을 실행시켜준 다음 다른것들 진행
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt = ps.executeUpdate(); // 성공한 수의 갯수를 전해줌

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public ArrayList<MemberVO> memberAllList() { // db table 안의 모든것을 가져옴 =조회 => 배열의 형태로 저장되어 있으나 vo객체자들이 저장이 되어있음, 크기 상관
													// 없이 저장할 수 있는 API
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL = "select * from tblMem order by num desc";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery(); // rs=커서 -> 뒤로 한칸씩 이동하면서 정보를 가져옴
			while (rs.next()) { // 1개의 레코드를 1)db의 내용을 "묶어주고", 2)array에 "담고"
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");

				// 묶어주고
				MemberVO vo = new MemberVO(num, name, phone, addr, lat, lng);
				// 담아주고
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

	//상세페이지 기능 -> vo(뼈대)로 담아 돌려준다: 전체 내용이니까
	public MemberVO memberContent(int num) { 
		MemberVO vo = null; //뼈대 선언
		conn = getConnect();
		String SQL = "select * from tblMem where num=?";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num); //들어온 num 숫자에 해당하는 자료를 뼈대에 set/담기 시킨다
			rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				vo = new MemberVO(num, name, phone, addr, lat, lng); //만들어놓은 뼈대(생성자)를 사용하여 그릇에 담기
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
