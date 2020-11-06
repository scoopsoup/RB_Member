<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본정보 입력</title>
<style>
	#wrapping{
		width: 1100px;
		height: 620px;
		/*border: 1px solid black;*/
		margin-left: auto;
		margin-right: auto;
		margin-top:100px;
	}
	form>#content{
		border: 1px solid lightgray;
		width: 1100px;
		height: 500px;
		background-color: lightgray;
	}
	form>#content>#content_sub{
		width: 900px;
		height: 350px;
		margin-top: 25px;
		margin-left: 25px;
		background-color: white;
		padding-top: 100px;
		padding-left: 150px;
	}
	form>#content_radio{
		width: 400px;
		height: 37px;
		float:right;
		margin-top: 10px;
		font-size: 18pt;
		/*border: 1px solid black;*/
	}
	
	form>#content>#content_sub>ul{
		list-style: none;
		width: 600px;
		height: 300px;
		
	}
	form>#content>#content_sub li{
		font-size: 22pt;
		float: right;
	}
	li input {
		width : 300px;
		height : 50px;
		padding : 5px;
		border : 2px solid #ccc;
		-webkit-border-radius : 5px;
		border-radius : 5px;
		margin-right : 10px;
	}
	.userName{
		width: 350px;
		height: 45px;
		font-size: 16pt;
	}
	.userBirth{
		width: 150px;
		height: 45px;
		font-size: 16pt;
	}
	#content_btn input {
			width : 100px;
			height : 50px;
			padding : 5px;
			margin-top : 10px;
			border : 2px solid #ccc;
			-webkit-border-radius : 5px;
			border-radius : 5px;
			font-size : 20px;
			float : right;
		}
</style>
</head>
<body>
	<div id="wrapping">
		<h1 style="font-size:30pt; text-align : center;" >기본정보 입력</h1>
			<form method="post" action="/auto/paper/weight" name="infoagree">
				<input type="hidden" name="onLine" value="${onLine }">
				<input type="hidden" name="reservation" value="${reservation }">
				<div id="content">
					<div id="content_sub">
						<ul>
							<li>이름  <input type="text" placeholder="이름을 입력하세요" class="userName" name="name" required><br><br><br></li>
							<li>주민등록번호  <input type="text" placeholder="생년월일" class="userBirth" name="numberfront" required> - <input type="password" placeholder="뒷자리 7자리" class="userBirth" name="numberback" required></li>		
						</ul>
					</div>
				</div>
				<div id="content_btn">
					<input type="submit" value="next >" >
				</div>
			</form>
	</div>
</body>
</html>