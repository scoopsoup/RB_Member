<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="member.model.vo.Member"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>단체_예약정보 입력</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=302qxax7hb&submodules=geocoder"></script>

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
		minDate: "+7",
		maxDate: "+6M",
		beforeShowDay: disableSomeDay
	});
	
	var disabledDays = [${skipDate}];
	
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


<style>
	#right_wrapping{
		width: 1100px;
		height: 620px;
		/*border: 1px solid black;*/
		margin-left: 50px;
	}
	#right_wrapping>form>#content{
		border: 1px solid lightgray;
		width: 1100px;
		height: 570px;
		background-color: lightgray;
	}
	#right_wrapping>form>#content>#content_sub{
		width: 1000px;
		height: 500px;
		margin-top: 25px;
		margin-left: 25px;
		background-color: white;
		padding-top: 20px;
		padding-left: 50px;
		text-align: center;
	}
	#right_wrapping>form>#content_btn{
		width: 170px;
		height: 37px;
		float : right;
		margin-top: 10px;
		margin-right : 10px;
	}
	#content_btn input {
		width: 170px;
		height: 37px;
		padding : 5px;
		border : 2px solid #ccc;
		-webkit-border-radius : 5px;
		border-radius : 5px;
	}
	#right_wrapping>form>#content>#content_sub>ul{
		list-style: none;
		width: 900px;
		height: 350px;
		margin-top : 50px;

	}
	#right_wrapping>form>#content>#content_sub li{
		font-size: 14pt;
		font-weight : 700;
	}
	#content_sub ul li input {
		width : 300px;
		height : 30px;
		padding : 5px;
		border : none;
		border-bottom : 2px solid #ccc;
		margin-left : 30px;
	}
	#content_sub ul li select {
		width : 100px;
		height : 30px;
		padding : 5px;
		border : none;
		border-bottom : 2px solid #ccc;
		margin-left : 30px;
	}

</style>
</head>
<body>
	<div id="right_wrapping">
						<h1>예약 정보 입력<span style="font-size: 15pt;"> (단체예약은 전혈만 가능합니다)</span></h1>
						<form method="post" action="/group/reservation/info/input">
							<div id="content">
								<div id="content_sub">
									<p style="font-weight:900; font-size:20px; text-align: center;"> ${memberName } 님</p>
									<div style="border:1px solid lightgray; background-color : lightgray; hegith:1px; width:950px;"> </div>
									<ul>
										<li>예약 날짜<input type="text" id="datepicker" name="date" required><br><br></li>
										<li>예약 시간
											<select name="beforetime" onclick="beforetest(this)" onchange="beforetest(this)">
												<c:forEach var="i" begin="9" end="14">
													<option><c:out value="${i}" /></option>
												</c:forEach>
											</select>&nbsp;&nbsp;&nbsp;시&nbsp;&nbsp;-
											<select name="aftertime" onclick="aftertest(this)" onchange="aftertest(this)" style="margin-left : 0;">
												<c:forEach var="i" begin="14" end="18">
													<option><c:out value="${i}" /></option>
												</c:forEach>
											</select>&nbsp;&nbsp;시
										</li>
										<li>예약 인원<input type="number" name="people" onclick="peopleNum(this)" onchange="peopleNum(this)" min="5" required><br><br></li>
										<li>장소 선택
                                            <input type="text" id="postCode" style="width:200px; display:inline-block;" placeholder="우편번호" name="postCode" readonly>
         									<button id="addrSearchBtn" onclick="addSearch();">주소검색</button>
         									<br>
         									<input type="text" id="roadAddr" style="width:385px;display:inline-block; float:right; margin-right:245px;" placeholder="도로명주소" name="roadAddr" required>
         									<br>
         									<input type="text" id="detailAddr" style="width:385px;display:inline-block; float:right; margin-right:245px;" placeholder="상세주소" name="detailAddr">
                                        </li>
										<p>예약시간은 <b>최대 6시간</b>, 예약인원은 1시간에 <b>최대 5명</b> 가능합니다.</p>
									</ul>
								</div>
							</div>

			
							
							
							<div id="content_btn">
								<input type="reset" value="취소" style="width:70px; height:35px;">
								<input type="submit" class="confirm" value="예약하기" style="width:90px; height:35px;">
							</div>
	
						</form>
					</div>
	<script>
        
        function addSearch(){
        //주소찾기 API가 한 일은 주소검색창 팝업 띄어주고 검색결과 항목을 선택했을 때 선택한 값에 대한 데이터를 보내주는 역할
         new daum.Postcode({
             oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭하면 그 때의 데이터가 data매개변수로 넘어오고 console.log로 그 data를 출력해봄
                console.log(data);
                console.log(data.zonecode);
                console.log(data.address);
                // 내가 선택한 input태그에 api서버가 응답한 값을 셋팅을 해줌
                $("#postCode").val(data.zonecode);
                $("#roadAddr").val(data.address);
                $("#jibunAddr").val(data.jibunAddress);
                 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                 // 예제를 참고하여 다양한 활용법을 확인해 보세요.
             }
         }).open();
     }
        
		var before=9;
		var after=14;
		var people;
		function beforetest(e) {
			before = $(e)[0].value;
			console.log(before);
			//console.log($(e));
		}
		function aftertest(e){
			after = $(e)[0].value;
			console.log(after);
		}
		
		function peopleNum(e){
			people = $(e)[0].value;
			console.log(people);
		}
		
		$(".confirm").click(function(){
			console.log(after-before);
			if(after-before>6){
				alert("예약시간을 확인해주세요");
				return false;
			}
			else if(after-before < 4){
				alert("예약시간을 확인해주세요");
				return false;
			}
			else{
				if(people > ((after-before) * 5)){
					alert("예약인원을 확인해주세요");
					return false;
				}
				else{
					return true;
				}
			}
		});
		
		
		
		/*function confirm(){
			if(totalTime>6){
				alert("예약시간을 확인해주세요");
				return false;
			}
		}*/
	</script>
	
</body>
</html>