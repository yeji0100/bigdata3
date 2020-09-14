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
		//1. (Ŭ���̾�Ʈ�κ��� ���� ��û��) ���û���� �ľ��ϴ� �۾� -> * �߿� list,insert,delete.do � ������ �Ǵ�
		String reqUrl = request.getRequestURI();
		//System.out.println(reqUrl);
		String ctxPath = request.getContextPath();
		//System.out.println(ctxPath);
		// Ŭ���̾�Ʈ�� ��û�� ��� - command �� Ŭ���̾�Ʈ�� ����� ��û�� �������� �� �� �ִ� 
		String command = reqUrl.substring(ctxPath.length());//���ؽ��v�� ���̸�ŭ
		System.out.println(command);
		//�� ��û�� ���� ó�� �ϱ�_�б��۾� (mvc�� 1������ -FrontController {else if �� ��� �����} / 2������ -if���� ���̰� ������ ��)
		
		Controller controller=null; //upcasting���� �ѹ濡 ���������
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		
		// HandlerMapping (��ü����-��ü:Ŭ����) - ���Ḹ ������
		// /list.do -> MemberListController
		// /insert.do -> MemberInsertController
		// /insertForm.do -> MemberInsertFormController
		// /delete.do -> MemberDeleteController
		
		/*[��]HandlerMapping ���� �̵�!
		if(command.equals("/list.do")) {
			//DB�������� ����Ʈ �������� 1)�����ڵ�-logic�� ������� ���߿� ������(������ �˹ٿ��� ��ų��) 2)�˹ٻ����� ��Ų��(view_jsp) 
			//1.�����ڵ�
//2)���� �ڵ� �� �κ��� pojo controller�� �ű�
//			ArrayList<MemberVO> list = dao.memberAllList();
			//��ü���ε� ��Ű�� ������  - member/memberList.jsp
//			request.setAttribute("list", list);
//			RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
//			rd.forward(request, response);
			
			//3)�������� ���� Ŭ������ ��ü ���� -> pojo�κ��� �������� => qystbayd
			
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response); //�������
			//��ü���ε��� ���� ��� -> forwarding
			//�ߺ��Ǳ� ������ if�� �Ʒ��� �ű�!
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);
		
		[��]HandlerMapping ���� �̵�!	
		}else if(command.equals("/insert.do")) { 
			controller = new MemberInsertController();
			nextView = controller.requestHandle(request, response);
			//sendRedirect- �ߺ��Ǳ� ������ if�� �Ʒ��� �ű�!
			//response.sendRedirect(nextView);
			
//2)pojo �� ��������
//			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			String addr = request.getParameter("addr");
//			
//			//�����ڷ� ���°��� �ƴ� ��ü�� ����� setter�� ���´�.
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
		[��]HandlerMapping ���� �̵�!
		else if(command.equals("/insertForm.do")) {
			//response.sendRedirect("member/member.html");
			//-> sendredirect ���� ����: html���� ��ä�� �Űܹ��� ��ΰ� �ٲ�� ������ ���߿� ó���ϴµ� ������ ���� 
			// �׷��� �߰��� ���������� �濡�� ���߾� �ִ� �������� ���
			
			
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response); //handler�޼ҵ� ����ؼ� ���� ���� ���ϵǾ�����! 
			//�ߺ��Ǳ� ������ if�� �Ʒ��� �ű�!
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);
			
		}
		[��]HandlerMapping ���� �̵�!
		else if(command.equals("/delete.do")){
			
//2)pojo �� ��������
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
		//<View �������� �����ϴ� �κ�>
		//�ߺ��Ǳ� ������ if�� �Ʒ��� �ű�!
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) {
			//Sting �ȿ� �� ���ڰ� ���� ��� -1 �� ��ȯ�ǰ� 0�̻��� ���� ��ȯ�� ��� �����Ѵٴ� ��
			// ��ξȿ� redirect�� �ְ� : �� ���� -> : �� �߽����� �ڸ����� ū�׸�!
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]); // :O 
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
		
		
	}

}
