
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h1 class="page-header">전체 사용자 리스트</h1>

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>별명</th>
								<th>등록일시</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="0" end="${userList.size()-1}" step="1" var="i">
								<tr class='userTr' data-userid="${userList.get(i).userId}">
									<td></td>
									<td>${userList.get(i).userId}</td>
									<td>${userList.get(i).userNm}</td>
									<td></td>
									<td>${userList.get(i).reg_dt_fmt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<form action="${cp}/userForm" method="get">
					   <button type="submit" class="btn btn-default">사용자 등록</button>
					</form>
				</div>


				<c:set var="lastPage1"
					value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }">
					<%--서블릿에 서 계산하는 방법 말고 이렇게 속성을 줘도 된다.
					Integer은  New 객체를 새로 만드는 것 
					int lastPage = (userCnt+(pageSize-1))/pageSize;
					--%>
				</c:set>
			<%-- 	<c:set var="tt" value="<%=new RangerVo("\hansoo"\,"\hansoo"\,26) %>"> set속성을 활용한 객체생성
				</c:set> 	
				<c:set var="tt2" value= "${RangerVo('hansoo1','hansoo1',26}"> set속성을 활용한 객체생성
				</c:set> 	 --%>
				<nav style="text-align: center;">
					<ul class="pagination">
						<c:choose>
							<c:when test="${page eq 1}">
								<li class="disabled"><a ria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${cp}/user/userPagingList"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${lastPage}" var="i">
							<c:set var="active" value="" />
							<%--초기화 과정 값이 계속 남기때문에 --%>
							<c:if test="${i eq page}">
								<c:set var="active" value="active" />
								<%--현재 페이지일 때 활성화를 위해서--%>
							</c:if>
							<li class="${active}"><a
								href="${cp}/user/userPagingList?page=${i}">${i}</a>
							</li>
						</c:forEach>

						<c:choose>
							<c:when test="${lastPage eq page}">
								<li class="disabled"><a ria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${cp}/user/userPagingList?page=${lastPage}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>


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
		//문서 로딩이 완료된 이후 이벤트 등록 
		$(document).ready(function() {
			console.log("document ready");

		/* 	//msg속성이 존재하면 alert 존재하지 않으면 넘어가기 */
			<c:if test="${msg != null}">
			alert("${msg}");
			<%session.removeAttribute("msg");%> //세션객체 지워주기
			</c:if>
			
			
			//사용자 tr태그 클릭 시 이벤트 핸들러 

			/* 방법1 택1
			$(".userTr").on("click",function(){
					console.log("userTr click");
				});
			 */

			//방법2 택1
			$(".userTr").click(function() {
				console.log("userTr click");

				//클릭한 userTr태그의 userId값을 출력
				/*  var userId= $(this).children()[1].innerHTML;
				 console.log("userId:"+userId); */

				var userId = $(this).data("userid");

				//user
				// 1.document
				//document.location = "/user?userId=" + userId;				

				//2. form
				$("#userId").val(userId);
				// $("#frm").attr("action","/userAllList"); 속성값 수정하는 방법  
				$("#frm").submit();

			});

		});
	</script>
	<form id="frm" action="${cp}/user/user"
		method="get">
		<input type="hidden" name="userId" id="userId" />
	</form>

</body>
</html>

