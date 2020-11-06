<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원가입</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function checkz(){
			var BD = RegExp(/^(19|20)[0-9]{2}(0[1-9]|1[1-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
			var phone = RegExp(/^01[01][0-9]{7,8}$/);
			var userName= RegExp(/^[가-힣]{3}$/); 
			
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
	if(!userName.test($("#userName").val())){
		alert("이름을 다시 입력해주세요")
		$("#userName").focus();
		return false;
	}
 	if(!($("#pwd").val() == $("#pwd2").val())){
 		alert("비밀번호와 비밀번호 확인이 다릅니다 다시 확인해주세요");
 		$("#pwd").focus();
 		return false;
	}
}	
</script>
<script>
	function check(){
	   var userId = $('#userId').val();
	   
	   $.ajax({
	      url: '<%=request.getContextPath()%>/member/idCheck',
	      data:{id:userId},
	      success:function(data){
	         if(data>0){
	            alert('이미 사용중인 아이디입니다.')
	         }else{
	            alert('사용 가능한 아이디입니다.')
	         }
	      }
	   })
	}
</script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {

            var roadAddr = data.roadAddress; 
            var extraRoadAddr = ''; 

            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            /* if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            } */

            var guideTextBox = document.getElementById("guide");
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
</script>

<script type="text/javascript">
	$(document).ready(

	// 시멘틱 구조 불러오기
	function() {

		// header.html 삽입 script
		$("#headers").load("/common/htmlTag/header.jsp");

		// nav.html 삽입 script
		$("#navs").load("/common/htmlTag/nav.jsp");

		// footer.html 삽입 script
		$("#footers").load("/common/htmlTag/footer.html");

	});
</script>
<!--  <link href="/common/memberSearch/MemberList.css" rel="stylesheet" type="text/css"> -->
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
	margin-left: 25%;
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

/* .table tbody tr:hover {
	background-color: #eeeeee;
	color: #168;
}
 */
.address{
	height: 20px;
	width: 150px;
	margin-left: 44px;
}

.address1{
	margin-top: 3px;
	height: 20px;
	width: 300px;
}

.inputBox{
	padding: 5px;
	height: 20px;
	width: 300px;
}

#new, #new1{
	height: 30px;
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
	height: 35px;
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
	<div id="wrapping1">
		<div id="wrapping2">
			<header id="headers"></header>
			<br>
			<nav id="navs"></nav>
			<br>

			<section>
				<div id="section_left">
					<div id="left_mainNav">
						<h1 style="margin-left: 20px; position: absolute;">회원가입</h1>
					</div>
					
				</div>
				<br>
				<div id="section_right">


					<div id="right_whole">
						<div id="right_head">
							<h1 style="font-size: 35px; font-weight: 900; text-align: center;'">회원가입</h1>
						</div>
						<br>
						<div id="right_middle">
							<form onsubmit="return checkz()" action="/member/enroll"
								method="post">
								<table class="table">
									<tbody>
										<tr>
											<td class="td_label">아이디</td>
											<td class="td_input"><input type="text"
												placeholder=" 아이디를 입력하세요" name="userId" class="inputBox" id="userId" required>
												<button type="button" class="searchBtn" onclick="check();">중복확인</button></td>
										</tr>
										<tr>
											<td class="td_label">비밀번호</td>
											<td class="td_input"><input type="password"
												placeholder=" 비밀번호를 8자이상 입력하세요" name="userPwd" id="pwd"
												minlength="8" class="inputBox" required></td>
										</tr>
										<tr>
											<td class="td_label">비밀번호 확인</td>
											<td class="td_input"><input type="password"
												placeholder=" 비밀번호를 다시입력하세요" name="userPwd" id="pwd2"
												minlength="8" class="inputBox" required></td>
										<tr>
											<td class="td_label">이름</td>
											<td class="td_input"><input type="text"
												placeholder=" 이름을 입력하세요" name="userName" id="userName"
												 class="inputBox" required></td>
										</tr>
										<tr>
											<td class="td_label">생년월일</td>
											<td class="td_input"><input type="text"
												placeholder=" ex) 19940506 형식의 8글자로 입력하세요" name="BD" maxlength="8"
												id="BD" class="inputBox" required></td>
										</tr>
										<tr>
											<td class="td_label">이메일</td>
											<td class="td_input"><input type="email"
												placeholder=" 이메일을 입력하세요" name="email" class="inputBox" required></td>
										</tr>
										<tr>
											<td class="td_label">폰번호</td>
											<td class="td_input"><input type="tel"
												placeholder=" ( - )을 빼고 입력하세요" name="phone" maxlength="11"
												id="phone" class="inputBox" required></td>
										</tr>
										<tr>
											<td class="td_label">주소</td>
											<td class="td_input"><input type="button"
												onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
												<input type="text" class="address" id="sample4_postcode" placeholder=" 우편번호 "
												name="addr"> <input type="text"
												id="sample4_roadAddress" class="address1" placeholder=" 도로명주소" name="addr">
												<input type="text" class="address1" id="sample4_jibunAddress"
												placeholder=" 지번주소" name="addr"> <span id="guide"
												style="color: #999; display: none" name="addr"></span> <input
												type="text" class="address1" id="sample4_detailAddress" placeholder=" 상세주소"
												name="addr" required>
										</tr>
										<tr>
											<td class="td_label">성별</td>
											<td class="td_input">
												<input type="radio" id="Male" value="M" name="gender"> 
												<label for="Male">남</label> 
												<input type="radio" id="FEMALE"	value="F" name="gender">
												<label for="FEMALE">여</label>
											</td>
										</tr>
										<tr>
											<td class="td_label">혈액형</td>
											<td class="td_input"><input type="radio" id="A"
												value="A" name="abo"> <label for="A">A형</label> <input
												type="radio" id="B" value="B" name="abo"> <label
												for="B">B형</label> <input type="radio" id="O" value="O"
												name="abo"> <label for="O">O형</label> <input
												type="radio" id="AB" value="AB" name="abo"> <label
												for="AB">AB형</label></td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input type="submit" value="가입하기" id="new">
												<input type="reset" value="취소" id="new1">
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
</body>
</html>