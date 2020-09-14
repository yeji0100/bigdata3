package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;

public class MemberDeleteController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cpath = request.getContextPath();
		int num = Integer.parseInt(request.getParameter("num"));

		// DB(Database Access) 연결하는 이유 = db를 연결해서 db를 사용한 기능(메소드)를 dao 안에 만들어놓았기 때문. ->
		// 기능활용!
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberDelete(num);
		String page = null;

		if (cnt > 0) {
			page = "redirect:"+cpath+"/list.do";
		} else {
			throw new ServletException("error");
		}
		return page;
	}

}
