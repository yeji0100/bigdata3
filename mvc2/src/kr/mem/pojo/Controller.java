package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//FC가 해야할 일을 POJO들이 대신해주는 메소드
	//f_c: 클라이언트가 오면 요청을 처리해줌, pOjo: client가 오면 요청을 "대신" 해주는 애들 
	//서비스 메소드와 똑같은 일을 위해 도구들을 통일시킨다 
	//인터페이스는 바디없음!
	public String requestHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
