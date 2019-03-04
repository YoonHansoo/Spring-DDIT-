<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
view.jsp

<!-- spring 파일 업로드 설정 (server-side)
	1. commons- fileUpload 의존 추가
	2. multipartResolver 등록 (servlet-context.xml)
	
	
	client side
	<form method="post" entype="multipart/form-data"/>   파일전송할 떄 form은 post만 가능하다.
	
	


  -->
	<form action="/mvc/fileupload" method="post" enctype="multipart/form-data">
		<input type="text" name="userId" value="brown"/> <br/>
		<input type="file" name="profile"/> <br/>
		<input type="submit" value="전송"/>
		
		
	 </form>  
</body>
</html>