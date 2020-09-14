package kr.mem.model;

public class MemberVO { // VO는 뼈대
	private int num;
	private String name;
	private String phone;
	private String addr;
	private double lat;
	private double lng;
	
	public MemberVO() {	}
	
	public MemberVO(String name, String phone, String addr) {
		super();
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}
	
	public MemberVO(int num, String name, String phone, String addr, double lat, double lng) {
		super();
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
	}//생성자 -> 틀을 만들어놓아야! DAO에서 기능연동 사용할수있음! 얼음틀에 물붓기 느낌!

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", name=" + name + ", phone=" + phone + ", addr=" + addr + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}// 들어있는 데이터가 잘 들어있는지 확인하려면 get으로 확인해야하는데 toString을 사용하면 한번에 문자열 형식으로 확인 가능하다 = debugging 하기 쉬움
	
}
