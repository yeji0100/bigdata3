package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cpath = request.getContextPath();
		
		//1. ��û�� �� �޾ƿ���
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		//�����ڷ� ���°��� �ƴ� ��ü�� �����(�׸����ҷ���) setter�� ���´�.
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setAddr(addr);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		String page = null;
		if(cnt>0) {
			// ������, �����̷�Ʈ�� ��θ� �Ѱ��� => ����̵��� ����ѷ����� �Ұ���!
			//�߾� ����  �ý��ۿ��� Ȯ�� �� ��η� ������ 
			page="redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
		}
		
		return page;
	}

}
