package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//servlet이 아니지만 getparameter하는 이유 = FC에서 getparameter를 받아서 보내기 때문에 여기도 같이 받아줌
		int num = Integer.parseInt(request.getParameter("num"));
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
	
		String cpath = request.getContextPath();
		
		//가져왔으니 담기
		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setPhone(phone);
		vo.setAddr(addr);
		
		//객체지향 -객체를 만들고(생성하고) 써먹기
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(vo);
		String page=null;
		if(cnt>0) {
			page="redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
			}
				
		return page;
	}

}
