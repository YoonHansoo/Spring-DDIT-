<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css"
	rel="stylesheet">


</head>


<body>
	<!--  헤더  삽입 -->
	<%@ include file="/WEB-INF/views/module/header.jsp"%>
	<!--  내부에서 처리하기 떄문에 getContextPath를 사용할 필요가 없음. -->


	<div class="container-fluid">
		<div class="row">
			<!--사이드 삽입 -->
			<%@ include file="/WEB-INF/views/module/left.jsp"%>                                        

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">사용자 정보조회</h1>
				
				<form id="frm" class="form-horizontal" role="form" method="get"
					action="${cp}/user/userModifyForm">

					<input type="hidden" name="userId" value="${userVo.userId}">
					
					<div class="form-group">
						<label for="userId" class="col-sm-3 control-label">사진</label>
						<div class="col-sm-9">
						
						<img src="${cp}/user/profileImg?userId=${userVo.userId}"/>
						<%-- <c:choose>
							<c:when test="${userVo.filename eq null}">
							<img src="${cp}/upload/noimg.png" />
							</c:when>
						<c:otherwise>
							<img src="${cp}/upload/${userVo.filename}" />
						</c:otherwise>
						</c:choose> --%>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="userId" class="col-sm-3 control-label">사용자 아이디</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.userId}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">사용자 이름</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.userNm}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">별명</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.alias}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">주소1</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-3 control-label">${userVo.addr1}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">주소2</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.addr2}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">우편번호</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.zipcode}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-3 control-label">Password</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">${userVo.pass}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-3 control-label">등록일자</label>
						<div class="col-sm-9">
							<label for="userNm" class="col-sm-2 control-label">
								<fmt:formatDate value="${userVo.reg_dt}" pattern="yyyy-MM-dd"/>
							</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<script>

$(document).ready(function() {
	console.log("document ready");

	/* //msg속성이 존재하면 alert 존재하지 않으면 넘어가기 */
	<c:if test="${msg != null}">
	alert("${msg}");
	<%session.removeAttribute("msg");%> //세션객체 지워주기
	</c:if>
});

</script>

</body>
</html>

