<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	- 회원정보 수정하기 (View) -
	<form action='${cpath}/update.do' method='post'>
		<table border='1'>
			<input type='hidden' name='num' value='${vo.num}'/>
			<!-- 1) 태그로는 보이지 않지만(수정불가,보이지않음) 값은 묶어 보내는 방법 
				input말고꼬리표 달아서 j query로 보내는 방법도 됨
				 2) action= "~~~~?n u m=${vo.num}"
				-->
			<tr>
				<td>회원번호</td>
				<td>${vo.num}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${vo.name}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type='text' name='phone' value=${vo.phone} /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type='text' name='addr' value=${vo.addr} /></td>
			</tr>
			<tr>
				<td>위도</td>
				<td><input type='text' name='lat' value=${vo.lat} /></td>
			</tr>
			<tr>
				<td>경도</td>
				<td><input type='text' name='lng' value=${vo.lng} /></td>
			</tr>
			<tr>
				<td align='center' colspan='2'>
					<input type="submit" value='수정'/> 
					<input type='reset' value='취소' /> 
					 <a	href='${cpath}/lst.do'>[리스트]</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>