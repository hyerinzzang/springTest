<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Insert title here</title>
<style>
 .nav{
      width:780px;
      margin-left:auto;
      margin-right:auto;
   }
   .menu{
      display:inline-block;
      background:yellowgreen;
      text-align:center;
      line-height:50px;
      width:150px;
      height:50px;
      margin-left:20px;
      margin-right:20px;
      border-radius:20px;
   }
   .menu:hover{
      background:orangered;
      color:white;
      cursor:pointer;
   }
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>
	
	<h1 align="center">Finally Last Subject Spring!!</h1>
	<br>
	
	<div class="loginArea" align="right">
		<c:if test="${empty sessionScope.loginUser }">
			<form action="login" method="post" >
				<table id="loginTable" style="text-align:center">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id"></td>
						<td rowspan="2">
							<button id="loginBtn">로그인</button>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="3">
							<a href="enrollview">회원가입</a>
							<a href="#">아이디/비밀번호 찾기</a>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${!empty sessionScope.loginUser }">
			<h3 align="right">
				<c:out value="${loginUser.name }님 환영합니다."/>
				<c:url var="logout" value="logout"/>
				<c:url var="myinfo" value="myinfo"/>
				
				<button onclick="location.href='${myinfo }'">정보수정</button>
				<button onclick="location.href='${logout}'">로그아웃</button>
			</h3>
		</c:if>	
	</div>
	
	<c:url var="nlist" value="nlist"/>
	<c:url var="blist" value="blist"/>
	<div class="menubar">
		<div class="nav">
			<div class="menu"><a href="home">HOME</a></div>
			<div class="menu"><a href="${nlist }">공지사항</a></div>
			<div class="menu"><a href="${blist }">게시판</a></div>
			<div class="menu"><a href="#">etc</a></div>
		</div>
	</div>
	
</body>
</html>