
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

<script src="../../assets/js/ie-emulation-modes-warning.js"></script>



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
				<h1 class="page-header">제품 그룹  리스트</h1>
				<!--userList 정보를 화면에 출력하는 로직 -->
		<%-- 		<%
					List<LprodVo> lprodList = (List<LprodVo>) request.getAttribute("lprodList");
					int lastPage = (Integer)request.getAttribute("lastPage");
				%> --%>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>lprod_id</th>
								<th>lprod_gu</th>
								<th>lprod_nm</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${lprodList}" var="lprod">
							<tr class ='lprodTr' data-lprodgu="${lprod.lprod_gu}">
							<td>${lprod.lprod_id}</td>
							<td>${lprod.lprod_gu}</td>
							<td>${lprod.lprod_nm}</td>
							</tr>
							
							</c:forEach>
						</tbody>
					</table>


			<%-- 		<%
						int cpage = (Integer) request.getAttribute("page");
						String cp = request.getContextPath();
					%> --%>
				</div>
				<nav style="text-align: center;">
					<ul class="pagination">
					
					
					<c:choose>
						<c:when test="${page eq 1}">
						<li class="disabled"><a ria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="${cp}/lprod/lprodList" aria-label="Previous">
							 	<span aria-hidden="true">&laquo;</span> 
						</a></li>
						</c:otherwise>					
					
					</c:choose>
					
					
					<c:forEach begin="1" end="${lastPage}" var="i">
							<c:set var="active" value="" /> 
						<c:if test="${i eq page}">
							<c:set var="active" value="active"/>
						</c:if>			
							<li class ="${active}">
							<a href="${cp}/lprod/lprodList?page=${i}">${i}</a></li>
					</c:forEach>
					
					<c:choose>
						<c:when test="${page eq lastPage}">
						<li class="disabled">
							<a ria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
						</c:when>
						<c:otherwise>
								<li>
								<a href="${cp}/lprod/lprodList?page=${lastPage}"aria-label="Next"> 
								<span aria-hidden="true">&raquo;</span>
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
	$(document).ready(function(){
		console.log("document ready");
		
		//사용자 tr태그 클릭 시 이벤트 핸들러 
				
	/* 방법1 택1
	$(".userTr").on("click",function(){
			console.log("userTr click");
		});
		*/
		
		//방법2 택1
		$(".lprodTr").click(function(){
			console.log("lprodTr click");
			
			//클릭한 userTr태그의 userId값을 출력
			/*  var userId= $(this).children()[1].innerHTML;
			 console.log("userId:"+userId); */
			 
			 var lprodGu = $(this).data("lprodgu");
			 
			 //user
			 // 1.document
			 //document.location = "/user?userId=" + userId;				
			 
			 //2. form
			 $("#lprodGu").val(lprodGu);
			 // $("#frm").attr("action","/userAllList"); 속성값 수정하는 방법  
			 $("#frm").submit();

		});
		
	});
</script>

<form id="frm" action="${cp}/prod/prod" method="get">
	<input type="hidden" name="lprodGu" id="lprodGu" />
</form>
</body>
</html>

