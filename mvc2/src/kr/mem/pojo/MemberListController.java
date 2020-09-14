package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller{
	@Override //������
	public String requestHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. ��ü����Ʈ�� ��������
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		// 2. ��ü���ε�
		request.setAttribute("list", list);
		// view page -> member/memberList.jsp
//		return "member/memberList.jsp"; //member/
		
//		return "/WEB-INF/views/member/memberList.jsp";
		return "member/memberList.jsp";
	}

}
