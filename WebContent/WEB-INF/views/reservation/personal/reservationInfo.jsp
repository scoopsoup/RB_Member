<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="member.model.vo.Member"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인_예약정보 입력</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
$(function(){
	$("#datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		changeMonth: true,
		changeYear: true,
		yearSuffix: '년',
		minDate: "+0",
		maxDate: "+7",
		beforeShowDay: disableSomeDay
	});
	
	var disabledDays = [];
	
	function disableSomeDay(date){
		var month = date.getMonth();
		var dates = date.getDate();
		var year = date.getFullYear();
		
		for(i = 0; i < disabledDays.length; i++){
			if($.inArray(year + '-' + (month+1) + '-' + dates,disabledDays) != -1){
				return [false];
			}
		}
		
		var noWeekend = jQuery.datepicker.noWeekends(date);
			return noWeekend[0] ? [true] : noWeekend;
		}
	});
</script>


<script type="text/javascript">
   $(document).ready(
         
      // 시멘틱 구조 불러오기
      function() {

         // header.html 삽입 script
         $("#headers").load("/R.B_Test01/views/htmlTag/header.html");

         // nav.html 삽입 script
         $("#navs").load("/R.B_Test01/views/htmlTag/nav.html");

         // footer.html 삽입 script
         $("#footers").load("/R.B_Test01/views/htmlTag/footer.html");

      });
</script>

<style type="text/css">

	/* 시멘틱 구조영역 스타일 */
	#wrapping1 {
		width: auto;
		height: 1400px;
		margin: 0 auto;
		/*background-image: linear-gradient(to top, #e3eeff 0%, #e3eeff 20%, #f3e7e9 100%);*/
	}
	
	#wrapping2 {
		width: 1550px;
		height: 1400px;
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
		/* position: relative; */
		width: 1500px;
		height: 1000px;
		/* border: 1px solid black; */
		padding-top: 20px;
		margin: 0 auto;
		z-index: 1;
		display: block;
	}
	
	#section_left {
	   		 position:absolute;
	         display : block;
	         width : 300px;
	         height : 1000px;
	         border-right : 1px solid black;
	         box-sizing: border-box;
	         float:left;
		
	}
	
	#section_right {
	   		 position:absolute;
	         display : block;
	         width : 1200px;
	         height : 1000px;
	         /*border : 1px solid black;*/
	         margin-left:300px;
		
	}
	
	/*----------------------------------------------*/
	/*section-right꾸미기*/
	section>#section_right>#right_wrapping{
		width: 1100px;
		height: 620px;
		/*border: 1px solid black;*/
		margin-left: 50px;
	}
	section>#section_right>#right_wrapping>form>#content{
		border: 1px solid lightgray;
		width: 1100px;
		height: 500px;
		background-color: lightgray;
	}
	section>#section_right>#right_wrapping>form>#content>#content_sub{
		width: 1000px;
		height: 425px;
		margin-top: 25px;
		margin-left: 25px;
		background-color: white;
		padding-top: 20px;
		padding-left: 50px;
		text-align : center;
	}
	section>#section_right>#right_wrapping>form>#content_btn{
		width: 170px;
		height: 37px;
		float : right;
		margin-top: 10px;
	}
	#content_btn input {
		width: 170px;
		height: 37px;
		padding : 5px;
		border : 2px solid #ccc;
		-webkit-border-radius : 5px;
		border-radius : 5px;
	}
	section>#section_right>#right_wrapping>form>#content>#content_sub>ul{
		list-style: none;
		width: 900px;
		height: 350px;
		margin : 50px 0;
	}
	section>#section_right>#right_wrapping>form>#content>#content_sub li{
		font-size: 14pt;
		font-weight : 700;
	}
	#content_sub ul li input, select {
		width : 300px;
		height : 30px;
		padding : 5px;
		border : none;
		border-bottom : 2px solid #ccc;
		margin-left : 30px;
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
				<div id="section_left"></div>
				<div id="section_right">
					<div id="right_wrapping">
						<h1>예약 정보 입력</h1>
						<form method="post" action="/reservation/info">
							<input type="hidden" name="memberName" value="${memberName }">
							<input type="hidden" name="memberNum" value="${memberNum }">
							<input type="hidden" name="onLine" value="${onLine }">
							<div id="content">
								<div id="content_sub">
									<p style="font-weight:900; font-size:20px;"> ${memberName } 님</p>
									<div style="border:1px solid lightgray; background-color : lightgray; hegith:1px; width:950px;"> </div>
									<ul>
										<li>예약 날짜<input type="text" id="datepicker" name="date" required><br><br></li>
										<li>예약 시간<input type="time" name="time" min="09:00:00" max="18:00:00" required><br><br></li>
										<li>장소 선택<input type="text" name="place" placeholder="장소를 입력해주세요" required><br><br></li>
										<li>헌혈 종류
											<select name="bloodtype" required>
												<option selected>-- 선택 --</option>
												<option>전혈</option>
												<option>혈장</option>
											</select>
										</li>
									</ul>
								</div>
							</div>
							<div id="content_btn">
								<input type="reset" value="취소" style="width:70px; height:35px;">
								<input type="submit" value="예약하기" style="width:90px; height:35px;">
							</div>
							
						</form>
					</div>
				</div>

			</section>
			<br>
			<br>

			<footer id="footers"></footer>
		</div>
	</div>
</body>
</html>