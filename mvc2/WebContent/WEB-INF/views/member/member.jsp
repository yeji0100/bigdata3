<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body {
	font-size: 12px;
}
</style>
</head>
<!-- submit을 눌렀을 때 실행되는 행동 = insert창으로 넘어가기
insert 창에서 작성 후 성공 시 list 창으로 넘어가기
실패하면 실패 화면 띄우기 
use 'kakao developer' -> 지도사용 access 위한 키값 받아오기 -> 내어플리케이션 안 앱키
-->
<body>
-회원가입-
	<form action="${cpath}/insert.do" method="post">
		<table border="0">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="addr" size="50" /> <input
					type="button" value="위도경도구하기" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="가입" />
					<input type="reset" value="취소" />
				</td>
	
			</tr>
		</table>
	</form>
</body>
</html>