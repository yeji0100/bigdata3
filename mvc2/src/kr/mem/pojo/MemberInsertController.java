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
		
		//1. 요청된 짐 받아오기
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		//생성자로 묶는것이 아닌 객체를 만들고(그릇을불러와) setter로 묶는다.
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setAddr(addr);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		String page = null;
		if(cnt>0) {
			// 성공시, 리다이렉트할 경로만 넘겨줌 => 경로이동은 컨드롤러에서 할것임!
			//중앙 관리  시스템에서 확인 후 경로로 보내줌 
			page="redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
		}
		
		return page;
	}

}
