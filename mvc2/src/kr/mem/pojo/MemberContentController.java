package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
public class MemberContentController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num); // ��� 1�� ���� �� = ������ vo
		// memberContent.jsp
		request.setAttribute("vo", vo); //���� fc���� ����
		return "member/memberContent.jsp"; 
	}

}
