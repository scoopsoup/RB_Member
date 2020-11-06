<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>정보수정 및 탈퇴</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%
	Member member = (Member)session.getAttribute("member");
%>
<script>
	function checkz(){
			var BD = RegExp(/^(19|20)[0-9]{2}(0[1-9]|1[1-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
			var phone = RegExp(/^01[01][0-9]{7,8}$/);
	if(!BD.test($("#BD").val())){
		alert("생년월일을 다시 입력해주세요")
		$("#BD").focus();
		return false;
	}
	if(!phone.test($("#phone").val())){
		alert("폰번호를 다시 입력해주세요")
		$("#phone").focus();
		return false;
	}
	}
	
</script>
<script>
	function question(){
	return confirm("정말 탈퇴하시겠습니까?");
	}
</script>


<script type="text/javascript">
   $(document).ready(
         
      // 시멘틱 구조 불러오기
      function() {

         // header.html 삽입 script
         $("#headers").load("/common/htmlTag/header.jsp");

         // nav.html 삽입 script
         $("#navs").load("/common/htmlTag/adminNav.jsp");

         // footer.html 삽입 script
         $("#footers").load("/common/htmlTag/footer.html");

      });
</script>
 <!-- <link href="/common/memberSearch/MemberList.css" rel="stylesheet" type="text/css"> -->
<style>
#wrapping1 {
	width: auto;
	height: 1410px;
	margin: 0 auto;
	background-image: linear-gradient(to top, #e3eeff 0%, #e3eeff 20%, #f3e7e9 100%);
}

#wrapping2 {
	width: 1550px;
	height: 1410px;
	margin: 0 auto;
	background-color: white;
}

header {
	position: static;
	width: 1500px;
	margin: 0 auto;
}

nav {
	position: relative;
	width: 1500px;
	z-index: 3;
	margin: 0 auto;
}

section {
	width: 1500px;
	height: 1050px;
	padding-top: 20px;
	margin: 0 auto;
	z-index: 1;
	display: block;
}

#section_left {
	position: absolute;
	display: block;
	width: 300px;
	height: 1000px;
	/*border : 1px solid black;*/
	border-right: 1px solid black;
	box-sizing: border-box;
	float: left;
}

#left_mainNav {
	position: relative;
	background-color: #f3e7e9;
	display: block;
	width: 275px;
	height: 80px;
	margin-top: 0;
}

.left_subNav {
	position: relative;
	border-bottom: 2.7px solid #cfe2ff;
	display: block;
	width: 275px;
	height: 70px;
}

.subA:hover {
	background-color: #e3eeff;
}

.subA {
	text-decoration: none;
	color: black;
	font-size: 17px;
	font-weight: bold;
	display: block;
	padding: 25px;
}

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1000px;
	/*border : 1px solid black;*/
	margin-left: 300px;
}

/*----------------------------------------------*/
/*section-right꾸미기*/
.table {
	border-collapse: collapse;
	border-top: 3px solid #168;
	margin-left: 10%;
	margin-top: 30px;
	position: absolute;
}

.table th {
	color: #168;
	/*background: #f0f6f9;*/
	background: #dcdcdc;
}

.table th, .table td {
	padding: 15px;
	border: 1px solid #ddd;
	width: 155px;
	height: 10px;
}

.table th:first-child, .table td:first-child {
	border-left: none;
	border-right: none;
}

.table th:last-child, .table td:last-child {
border-left: none;
	border-right: none;
	width: 400px;
}

.td_label{
	text-align: left;
}

.table tr td:first-child {
}

.table tbody tr:hover {
	background-color: #eeeeee;
	color: #168;
}


.inputBox{
	height: 20px;
	width: 300px;
}

#btn2{
	height: 30px;
	margin-left: 10px;
}

.searchBox {
	float: left;
	position: absolute;
	margin-left: 840px;
	display: block;
	margin-top: 14px;
	height: 30px;
	width: 300px;
}

.searchBtn {
	position: absolute;
	float: left;
	height: 37px;
	width: 50px;
	margin-top: 14px;
	margin-left: 1140px;
	font-size: 15px;
	font-weight: bolder;
}

.searchTag {
	position: absolute;
	float: left;
	display: block;
	height: 37px;
	font-size: 20px;
	font-weight: bold;
	margin-left: 650px;
	margin-top: 17px;
}
</style>
</head>
<body>
<c:if test="${sessionScope.member ne null }">
	<div id="wrapping1">
		<div id="wrapping2">
			<header id="headers"></header>
			<br>
			<nav id="navs"></nav>
			<br>

			<section>
				<div id="section_left">
					<div id="left_mainNav"><h1 style="margin-left:20px; position: absolute;">마이페이지</h1></div>
					<div class="left_subNav"><a href="#" class="subA">헌혈기록 및 검사결과 조회</a></div>
					<div class="left_subNav"><a href="/common/login/userModify.jsp" class="subA">정보수정 및 탈퇴</a></div>
				</div>
				<div id="section_right">


					<div id="right_whole">
						<div id="right_head">
							<h1 style="font-weight: 900;">&nbsp;&nbsp;정보수정 및 탈퇴</h1>
						</div>
						<div id="right_middle">
							<br>
							<br>
							<form onsubmit="return checkz()" action="/member/update" method="post">
							<table class="table">
								<tbody>
								<tr>
									<td class="td_label">아이디 </td>
									<td class="td_input"> <input type="text" class="inputBox" placeholder="아이디를 입력하세요"  name="userId" value=<%=member.getUserId()%>  readonly>
									</td>
								</tr>
								<tr>
									<td class="td_label">비밀번호</td>
									<td class="td_input"> <input type="password" class="inputBox" name="userPwd" placeholder="비밀번호를 입력하세요" ></td>
								</tr>
								<tr>
									<td class="td_label">비밀번호 확인</td>
									<td class="td_input"><input type="password" class="inputBox" placeholder="비밀번호를 다시입력해주세요" name="userPwd"  id="pwd2" minlength="8" required></td>
								<tr>
								<tr>
									<td class="td_label">이름 </td>
									<td class="td_input"> <input type="text" class="inputBox" placeholder="이름을 입력하세요"  name="userName"value=<%=member.getUserName() %>></td>
								</tr>
								<tr>
									<td class="td_label">생년월일 </td>
									<td class="td_input"> <input type="text" class="inputBox" placeholder="생년월일8자리를 입력해주세요"  name="BD"  maxlength="8"  id="BD" value=<%=member.getUserBD() %> readonly >
									</td>
								</tr>
								<tr>
									<td class="td_label">이메일</td>
									<td class="td_input"> <input type="email" class="inputBox" name="email"placeholder="이메일을 입력하세요"></td>
								</tr>
								<tr>
									<td class="td_label">폰번호 </td>
									<td class="td_input"> <input type="tel" class="inputBox" placeholder="폰번호를 - 빼고 입력하세요" name="phone"   maxlength="11" id="phone" value=<%=member.getUserPhone() %> ></td>
								</tr>
								<tr>
									<td class="td_label">주소 </td>
									<td class="td_input"><input type="text" class="inputBox"  name="address"placeholder="우편번호 를 입력하세요" value=<%=member.getUserAddr() %>><br>
									<input type="text" class="inputBox" placeholder="상세주소를 입력하세요"></td>
								</tr>
								<tr>
									<td class="td_label">성별 </td>
									<td class="td_input"> 
									    <input type="radio" id="Male" value="Male" name="gender" >
									    <label for="Male">남</label>
										<input type="radio" id="FEMALE" value="FEMALE" name="gender" >
									    <label for="FEMALE">여</label>
									</td>
								</tr>
								<tr>
									<td class="td_label">혈액형</td>
									<td class="td_input">
									    <input type="radio" id="A" value="A" name="abo">
										<label for="A">A형</label>
										<input type="radio" id="B" value="B" name="abo">
									    <label for="B">B형</label>
									    <input type="radio" id="O" value="O" name="abo">
										<label for="O">O형</label>
										<input type="radio" id="AB" value="AB" name="abo">
									    <label for="AB">AB형</label>
									</td>
								</tr>
								<tr>
								<td colspan="2" align="center">
								<input type="submit" value="수정하기" id="btn2" onclick="javascript :form.action ='/member/update';"/>
								<input type="reset" value="취소하기" id="btn2">
								<form action="/member/delete" method="post">	
									<input type="submit" id="btn2"  value="탈퇴하기" onclick="return question();"/>
								</form>
								</td>
								</tr>
							</tbody>
							</table>
							</form>	
						</div>
					</div>
				</div>

			</section>
			<br> <br>

			<footer id="footers"></footer>
		</div>
	</div>
</c:if>
<c:if test="${sessionScope.member eq null }">
	<jsp:forward page="/login/admin"></jsp:forward>
</c:if>
</body>
</html>


