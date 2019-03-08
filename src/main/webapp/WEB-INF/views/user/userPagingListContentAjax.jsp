<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				<h1 class="page-header">전체 사용자 리스트(ajax tiles)</h1>

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
						<tbody id="userListTbody">
							
						</tbody>
					</table>
					
					<form action="${cp}/user/userForm" method="get">
					   <button type="submit" class="btn btn-default">사용자 등록</button>
					</form>
				</div>


				<c:set var="lastPage1"
					value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }">
				
				</c:set>
			
				<nav style="text-align: center;">
					<ul id="pagination" class="pagination">
						
					</ul>
				</nav>
	<script>
	
	//사용자 배열을 이용하여 사용자 리스트 HTML을 생성
	function makeUserList(userList){
		var html = "";
		
		for(var i = 0; i<userList.length; i++){
			var user = userList[i];
			html+="<tr class='userTr' data-userid='" +user.userId + "'>";
			html+=    "<td></td>";
			html+=    "<td>" + user.userId + "</td>";
			html+=    "<td>" + user.userNm + "</td>";
			html+=    "<td></td>";
			html+=    "<td>" + user.regDt_fmt + "</td>";
			html+="</tr>";
		}
		
		$("#userListTbody").html(html);
	}
	
	function makePagination(UserCnt,pageSize,page,lastPage){
		console.log("lastPage:"+ lastPage);
		var html ="";
		
		if(page==1){
			html += "<li class='disabled'>";
			html += "	<a ria-label='Previous'>";
			html += "		<span aria-hidden='true'>&laquo;</span>";
			html += "	</a>"; 
			html += "</li>"; 
		}
		else{
		html +=	"<li>";
		html +=	"	<a href='javascript:getUserPageList(1);' aria-label='Previous'>";
		html +=	"<span aria-hidden='true'>&laquo;</span>";
		html +=		"</a>";
		html +=	"</li>";
			
		}
		
		for(var i = 1; i<= lastPage; i++){
			var active ="";
			if(i==page){
				active = "active";
			}
				html+= "	<li class='"+active+"'>";
				//html+=" 			<a href='${cp}/user/userPagingList?page="+i+ "'>"+i+"</a>";
				html+=" 			<a href='javascript:getUserPageList(" + i + ");'>"+i+"</a>";
				html+= "</li>";
			}
		
		
		if(lastPage == page){
			html += "	<li class='disabled'><a ria-label='Next'>";
			html += "			<span aria-hidden='true'>&raquo;</span>";
			html += "						</a>";	
			html += "   </li>";	
		}
		else{
		    html += "<li><a href='javascript:getUserPageList(" + lastPage + ");' aria-label='Next'>";
		    html += "<span aria-hidden='true'>&raquo;</span>";
		    html += "						</a>";	
			html += "</li>";	
		}
		
		console.log("html"+ html);
		$("#pagination").html(html);
		
	<%-- 
	<c:choose>
		<c:when test="${lastPage eq page}">
			<li class="disabled"><a ria-label="Next"> 
			<span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${cp}/user/userPagingList?page=${lastPage}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:otherwise>
	</c:choose> 
	--%>
		
		
		
	}
	
	
	function getUserPageList(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjax",
			data : "page="+ page,
			success : function(data){
				console.log(data);
				makeUserList(data.userList);
				makePagination(data.userCnt, data.pageSize,data.page,data.lastPage);
			}
			
		});
		
	}
	
	function getUserPageListHtml(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjaxHtml",
			data : "page="+ page,
			success : function(data){
				console.log(data);
				/* 
				data
				
				
				사용자 리스트 html...
				==========================seperator===========================
				페이지네이션 html...
				
				*/
				
				var htmlArr = data.split("==========================seperator===========================");
				
				$("#userListTbody").html(htmlArr[0]);
				$("#pagination").html(htmlArr[1]);
				
			}
			
		});
		
	}
		
	
	/* 	//문서 로딩이 완료된 이후 이벤트 등록  */
		$(document).ready(function() {
			console.log("document ready");

			//getUserPageList(1);
			getUserPageListHtml(1);
			
			
			
			
			
			
			
			
			
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

			 /*-------------------------------------  */
			//ajax를 통한 html 생성 시 이벤트 핸들러 등록 방법
		      //1. html이 ajax 호출에 의해 정상적으로 생성된 이후
		      //   클릭이벤트 핸들러를 등록
		      //   (success -> 사용자 html이 생성된 이후에 등록)
		     //2. 이벤트 핸들러 대상을 변경(.userTr -> #userListTbody)
		     //   동적으로 생성된 html을 감싸는 영역에 이벤트를 등록
		     //   단 on 옵셥에서 감싸는 영역 안에 처리되어야 할 selector를 명시
		     //   $(".userTr").on("click", function(){
		     //   ->
		      //   $("#userListTbody").on("click", ".userTr", function(){
		      /*-------------------------------------  */
		      // 사용자 tr 태그 클릭시 이벤트 핸들러
		      $("#userListTbody").on("click", ".userTr", function(){
			 
			//방법2 택1
		//	$(".userTr").click(function() {
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


