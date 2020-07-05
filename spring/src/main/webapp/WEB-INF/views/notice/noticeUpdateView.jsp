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
	
	<h1 align="center">공지글 수정 페이지</h1>
	<br>
	<form action="nupdate" method="post" enctype="Multipart/form-data">
			<input type="hidden" name="nId" value="${notice.nId }">
			<input type="hidden" name="filepath" value="${notice.filePath }">
			<table align="center" border="1" width="400">
				<tr>
					<td>제목</td>
					<td><input type="text" size="50" name="nTitle" value="${notice.nTitle }">
				</tr> 
				<tr>
					<td>작성자</td>
					<td><input type="text" name="nWriter" readonly value="${loginUser.id }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="7" cols="50" name="nContent">${notice.nContent }</textarea>
					</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<c:if test="${!empty notice.filePath }">
					<td>
					<input type="file" name="reuploadFile">
						<br>
						현재 업로드 한 파일:
						<img src="${contextPath }/resources/nuploadFiles/${notice.filePath}">
						<a href="${contextPath }/resources/nuploadFiles/${notice.filePath}" download>${notice.filePath }</a>
					</td>
					</c:if>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="수정하기"> &nbsp;
				</tr>
			</table>
		</form>
		
		<p align="center">
			<a href="home">시작 페이지 이동</a>
			<a href="nlist">목록 보기 이동</a>
			<a href="javascript:history.go(01);">이전페이지로 이동</a>
		</p>
</body>
</html>