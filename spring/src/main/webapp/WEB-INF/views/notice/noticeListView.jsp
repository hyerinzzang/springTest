<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">공지글 목록 보기</h1>
	
	<br><br>
	<c:if test="${!empty loginUser }">
		<div align="center">
			<button onclick="location.href='nWriterView'">글쓰기</button>
		</div>
	</c:if>
	<br>
	
	<table align="center" width="600" border="1" id="td">
		<tr color="#99ccff">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>올린날짜</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach var="n" items="${list }">
			<tr>
				<td align="center">${n.nId }</td>
			<td>
				<c:if test="${!empty loginUser }">
					<c:url var="ndetail" value="ndetail">
						<c:param name="nId" value="${n.nId }"/>
					</c:url>
					<a href="${ndetail }" >${n.nTitle }</a>
				</c:if>
				<c:if test="${empty loginUser }">
					${n.nTitle }
				</c:if>
			</td>
			<td align="center">${n.nWriter }</td>
			<td align="center">${n.nCreateDate }</td>
			
			<c:if test="${!empty n.filePath }">
				<td align="center"> * </td>
			</c:if>
			<c:if test="${empty n.filePath }">
				<td alitn="center"> </td>
			</c:if>
			</tr>						
		</c:forEach>
	</table>
	
	<p align="center">
		<c:url var="home" value="home"/>
			<a href="${home }">시작 페이지로 이동</a> &nbsp;
		<c:url var="nlist" value="nlist"/>
			<a href="${nlist }">목록 전체 보기</a>	
		
	</p>
</body>
</html>