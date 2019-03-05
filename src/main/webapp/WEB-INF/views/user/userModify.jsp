<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<h1 class="page-header">사용자 정보수정</h1>
			 <form id="frm" action="${cp }/user/userModifyForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data"><!-- 디비값이 변경되면 post-->
			 
			 <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">사진</label>
                  <div class="col-sm-5">
                  	<img src="${cp}/user/profileImg?userId=${userVo1.userId}"/> 
                        <input type="file" class="form-control" id="profile" name="profile" placeholder="사진">
                  </div>
               </div>
			 
               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">사용자 아이디</label>
                  <div class="col-sm-5">
                        <input type="text" class="form-control" id="userId" name="userId" placeholder="사용자 아이디" value="${userVo.userId}" readonly="readonly">
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">사용자 이름</label>
                  <div class="col-sm-5">
                        <input type="text" class="form-control" id="userNm" name="userNm" placeholder="사용자 이름" value="${userVo.userNm}">
                  </div>
               </div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">별명</label>
                  <div class="col-sm-5">
                     <input type="text" class="form-control" id="alias"   name="alias" placeholder="별명" value="${userVo.alias}">
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">주소1</label>
                  <div class="col-sm-5">
                     <input type="text" readonly class="form-control" id="addr1"   name="addr1" placeholder="주소" value="${userVo.addr1}" >
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">주소2</label>
                  <div class="col-sm-5">
                     <input type="text" class="form-control" id="addr2"   name="addr2" placeholder="상세주소" value="${userVo.addr2}">
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">우편번호</label>
                  <div class="col-sm-4">
                     <input type="text" readonly class="form-control" id="zipcode"   name="zipcode" placeholder="우편번호" value="${userVo.zipcode}">
                  </div>
                  <div class="col-sm-3">
                     <button id="zipcodeBtn" type="button" class="btn btn-default">우편 검색</button>
                  </div>
               </div>
               
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-3 control-label">Password</label>
                  <div class="col-sm-5">
                     <input type="password" class="form-control" id="pass" name="pass">
                  </div>
               </div>
               
             

               <div class="form-group">
                  <div class="col-sm-offset-3 col-sm-9">
                     <button type="button" id="updBtn" class="btn btn-default">사용자 수정</button>
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
		
	<!--다음 api사용  -->
		<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		$(document).ready(function(){
			
			//javascript 에서 비교
			/* if("${msg}" != ""){
				alert("${msg}");
			} */
			
			
		/* 	//server side */
		<c:if test="${msg != null}">
	alert("${msg}");
	<%session.removeAttribute("msg");%> //세션객체 지워주기
	</c:if>
			

			//우편번호 검색 버튼 클릭 이벤트
			$("#zipcodeBtn").click(function(){
				new daum.Postcode({
	        		oncomplete: function(data) {
	            console.log(data);
	            
	            //새 우편번호 : data.zonecode
	            $("#zipcode").val(data.zonecode);
	            
	            //기본주소(도로주소) : data.roadAddress
	            $("#addr1").val(data.roadAddress);
	            
	            //상세주소 포커스
	            $("#addr2").focus();
	            
	       			 }
	    	}).open();
			});
			
			//사용자 수정 버튼 클릭 이벤트
			$("#updBtn").click(function(){
				//사용자 아이디
			
			
				//사용자 이름
				if($("#userNm").val().trim() ==""){
					alert("사용자 이름를 입력해주세요");
					$("#userNm").focus();
					return;
				}	
				
			
				//별명
				if($("#alias").val().trim() ==""){
					alert("별명를 입력해주세요");
					$("#alias").focus();
					return;
				}
				//주소1(우편번호)
				if($("#addr1").val().trim() ==""){
					alert("우편번호를 입력해주세요");
					$("#zipcodeBtn").trigger("click");
					return;
				}
				//주소2
				if($("#addr2").val().trim() ==""){
					alert("주소를 입력해주세요");
					$("#zipcode").trigger("click");
					return;
				}
				//우편번호 (생략)
				
			
				
				//정상적으로 validation이 완료 ---->form으로 전송
				$("#frm").submit();
					
			});
			
		});
    		
</script>
		

</body>
</html>

