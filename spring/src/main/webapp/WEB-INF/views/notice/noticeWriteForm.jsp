<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<h1 align="center">공지글 등록 페이지</h1>
	<br>
	
	<form action="ninsert" method="post" enctype="Multipart/form-data">
		<table align="center" border="1" width="400">
			<tr>
				<td>제목</td>
				<td><input type="text" size="50" name="nTitle">
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="nWriter" readonly value="${loginUser.id }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="7" cols="50" name="nContent"></textarea>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="uploadFile"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="등록 하기"> &nbsp;
				<input type="reset" value="등록 취소">
			</tr>
		</table>
	</form>
	
	<p align="center">
		<a href="home">시작 페이지로 이동</a>
		<a href="nlist">목록 보기로 이동</a>
	</p>
</body>
</html>