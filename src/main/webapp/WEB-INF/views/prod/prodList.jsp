<%@page import="kr.or.ddit.prod.model.ProdVo"%>
<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<h1 class="page-header">제품 리스트</h1>
				<%
				List<ProdVo> prodList  = (List<ProdVo>)request.getAttribute("prodList");
				%>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>prod_id</th>
								<th>prod_name</th>
								<th>prod_buyer</th>
								<th>prod_cost</th>
							</tr>
						</thead>
						<tbody>
							<%
    		 for(int i =0; i < prodList.size(); i++){
    		 	out.print("<tr class ='lprodTr'>");
    		 	out.print("<td>"+prodList.get(i).getProd_id() +"</td>");
    		 	out.print("<td>"+prodList.get(i).getProd_name() +"</td>");
    		 	out.print("<td>"+prodList.get(i).getProd_buyer()+"</td>");
    		 	out.print("<td>"+prodList.get(i).getProd_cost()+"</td>");
    		 	out.print("</tr>");
    		 }
    		 %>


						</tbody>
					</table>
				</div>


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
</body>
</html>

