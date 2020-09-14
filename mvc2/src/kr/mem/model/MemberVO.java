package kr.mem.model;

public class MemberVO { // VO�� ����
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
	}//������ -> Ʋ�� �������ƾ�! DAO���� ��ɿ��� ����Ҽ�����! ����Ʋ�� ���ױ� ����!

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
	}// ����ִ� �����Ͱ� �� ����ִ��� Ȯ���Ϸ��� get���� Ȯ���ؾ��ϴµ� toString�� ����ϸ� �ѹ��� ���ڿ� �������� Ȯ�� �����ϴ� = debugging �ϱ� ����
	
}
