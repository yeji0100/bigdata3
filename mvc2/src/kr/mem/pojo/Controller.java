package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//FC�� �ؾ��� ���� POJO���� ������ִ� �޼ҵ�
	//f_c: Ŭ���̾�Ʈ�� ���� ��û�� ó������, pOjo: client�� ���� ��û�� "���" ���ִ� �ֵ� 
	//���� �޼ҵ�� �Ȱ��� ���� ���� �������� ���Ͻ�Ų�� 
	//�������̽��� �ٵ����!
	public String requestHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
