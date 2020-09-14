package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		//1. (클라이언트로부터 들어온 요청이) 어떤요청인지 파악하는 작업 -> * 중에 list,insert,delete.do 어떤 것인지 판단
		String reqUrl = request.getRequestURI();
		//System.out.println(reqUrl);
		String ctxPath = request.getContextPath();
		//System.out.println(ctxPath);
		// 클라이언트가 요청한 명령 - command 가 클라이언트가 명령한 요청이 무엇인지 알 수 있다 
		String command = reqUrl.substring(ctxPath.length());//컨텍스틍의 길이만큼
		System.out.println(command);
		//각 요청에 따라 처리 하기_분기작업 (mvc의 1차버전 -FrontController {else if 로 계속 길어짐} / 2차버전 -if문의 길이가 변형이 됨)
		
		Controller controller=null; //upcasting으로 한방에 묶어버리기
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		
		// HandlerMapping (객체지향-객체:클래스) - 연결만 시켜줌
		// /list.do -> MemberListController
		// /insert.do -> MemberInsertController
		// /insertForm.do -> MemberInsertFormController
		// /delete.do -> MemberDeleteController
		
		/*[★]HandlerMapping 으로 이동!
		if(command.equals("/list.do")) {
			//DB연동시켜 리스트 가져오기 1)직접코딩-logic이 길어져서 나중에 빼야함(어차피 알바에게 시킬것) 2)알바생에게 시킨다(view_jsp) 
			//1.직접코딩
//2)직접 코딩 이 부분은 pojo controller로 옮김
//			ArrayList<MemberVO> list = dao.memberAllList();
			//객체바인딩 시키고 포워딩  - member/memberList.jsp
//			request.setAttribute("list", list);
//			RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
//			rd.forward(request, response);
			
			//3)만들어놓은 포조 클래스의 객체 생성 -> pojo로부터 가져오기 => qystbayd
			
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response); //대신해줄
			//객체바인딩을 했을 경우 -> forwarding
			//중복되기 때문에 if문 아래로 옮김!
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);
		
		[★]HandlerMapping 으로 이동!	
		}else if(command.equals("/insert.do")) { 
			controller = new MemberInsertController();
			nextView = controller.requestHandle(request, response);
			//sendRedirect- 중복되기 때문에 if문 아래로 옮김!
			//response.sendRedirect(nextView);
			
//2)pojo 로 보내버림
//			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			String addr = request.getParameter("addr");
//			
//			//생성자로 묶는것이 아닌 객체를 만들고 setter로 묶는다.
//			MemberVO vo = new MemberVO();
//			vo.setName(name);
//			vo.setPhone(phone);
//			vo.setAddr(addr);
		
//			int cnt = dao.memberInsert(vo);
//			if(cnt>0) {
//				response.sendRedirect("/mvc1/list.do");
//			}else {
//				throw new ServletException("error");
//			}
		}
		[★]HandlerMapping 으로 이동!
		else if(command.equals("/insertForm.do")) {
			//response.sendRedirect("member/member.html");
			//-> sendredirect 안한 이유: html까지 통채로 옮겨버려 경로가 바뀌기 때문에 나중에 처리하는데 불편함 있음 
			// 그래서 중간에 실행으로의 길에서 멈추어 있는 포워딩을 사용
			
			
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response); //handler메소드 사용해서 나온 값이 리턴되어있음! 
			//중복되기 때문에 if문 아래로 옮김!
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);
			
		}
		[★]HandlerMapping 으로 이동!
		else if(command.equals("/delete.do")){
			
//2)pojo 로 보내버림
//			int num=Integer.parseInt(request.getParameter("num"));
//			int cnt=dao.memberDelete(num);
//			if(cnt>0) {
//				response.sendRedirect("/mvc1/list.do");
//			}else {
//				throw new ServletException("error");
//			}
			
			controller = new MemberDeleteController();
			nextView = controller.requestHandle(request, response);
			//response.sendRedirect(nextView);
		}		
*/		
		
		//--------------------------------------
		//<View 페이지로 연동하는 부분>
		//중복되기 때문에 if문 아래로 옮김!
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) {
			//Sting 안에 저 글자가 없을 경우 -1 이 반환되고 0이상의 수가 반환될 경우 존재한다는 말
			// 경로안에 redirect를 넣고 : 을 찍음 -> : 을 중심으로 자르려는 큰그림!
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]); // :O 
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
		
		
	}

}
