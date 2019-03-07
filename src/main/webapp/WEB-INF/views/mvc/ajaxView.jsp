<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

td{
	border: 1px solid black;
}

</style>
<script src="${cp}/js/jquery-3.3.1.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		console.log("ajax.jsp");

		$("#jsonRegBtn").on("click", function() {
			//responseBody();
			requestBody();
		});
	});
	
	
	function responseBody(){
		$.ajax({
			url : "${cp}/ajax/responseBody",
			method :"post",
			dataType :"json", //server에게 희망하는 응답의 리턴타입
			success : function(data) {
				console.log("data:" + data);
				var html = ""; 
				for(var i =0 ; i<data.length; i++){
					html += "<tr><td>"+data[i]+"</td></tr>";
				}
				$("#jsonRecvTbody").html(html);
			}
		});
	}
	
	function requestBody(){
		var data = {userId:"brown",userNm:"브라운"};
		
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method :"post",
			//data : $("#frm").serialize(),//"userId=brown&userNm=브라운"대신 form태그에 보내야 할 값이 많을 때 쓰는 함수
			data : JSON.stringify(data) , //js객체를 문자열로 만들어준다.
			dataType :"json", //server에게 희망하는 응답의 리턴타입
			contentType : "application/json; charset=UTF-8", // client가 전송하는 데이터 타입
			success : function(data) {
				console.log("data:" + data);
				$("#jsonRecvTbody").html("<tr><td>"+data.userId+"</td></tr>");
			/* 	var html = ""; 
				for(var i =0 ; i<data.length; i++){
					html += "<tr><td>"+data[i]+"</td></tr>";
				}
				$("#jsonRecvTbody").html(html); */
			}
		});
	}
</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="userId" value="brown"/>
		<input type="text" name="userNm" value="브라운 "/>
		</form>


	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonRegBtn">jsonData요청</button>
		<div id=jsonRecv>
			<table>
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id ="jsonRecvTbody">
				<!-- 	<tr>
						<td>brown</td>
					</tr>
					<tr>
						<td>cony</td>
					</tr> -->
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>