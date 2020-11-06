<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전자문진</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
	body {
		box-sizing: border-box;
	}
	#wrapping{
		width:1300px;
		height: 820px;
		margin: 0 auto;
		background-color: lightgray;
		border-radius: 5px;
	}
	#cont_top {
		width : 1200px;
		height : 600px;
		margin : 0 auto;
		padding-top : 40px;
		padding-left : 25px;
		background : white;
		border-radius: 5px;
		margin : 0 auto;
	}
	h1, h4 {
		text-align: center;
	}
	h1 {
		padding-top :15px;
	}
	h4 {
		margin-top : -15px;
	}
	.pclass {
		font-weight: 900;
		font-size : 20px;
		display : inline-block;
	}
	.label_div {
		float : right;
		margin-right : 10%;
		display : inline-block;
	}
	#btn_submit {
		float : right;
		width : 80px;
		height : 40px;
		padding : 5px;
		margin-top : 15px;
		margin-right : 38px;
		border : 2px solid #ccc;
		-webkit-border-radius : 5px;
		border-radius : 5px;
	}
	.ques {
		padding-bottom : 50px;
	}
	.line {
		display : inline-block;
		height : 5px;
		margin-left : 20px;
		margin-right : 20px;
		border : none;
		border-top: 1px dashed lightgray;
	}
</style>
</head>
<body>
	<div id="wrapping">
		<h1>자가문진</h1>
		<h4>아래의 질문에 답해주세요</h4>
		<div id="content">
			<form method="post" action="/self/paper/weight/input" name="YN">
				<input type="hidden" name="memberName" value="${memberName }">
				<input type="hidden" name="memberNum" value="${memberNum }">
				<input type="hidden" name="onLine" value="${onLine }">
				<input type="hidden" name="reservation" value="${reservation }">
				
				<div id="cont_top">
					<div id="qst_01" class="ques">
						<div class="pclass">1. 오늘의 몸상태는 괜찮은가요?</div>
						<div class="line" style="width:630px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r1" value="Y" class="agree">네</label>
							<label><input type="radio" name="r1" value="N" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_02" class="ques">
						<div class="pclass">2. 최근 3일 이내에 발열, 인후통, 설사의 증상이 있었습니까?</div>
						<div class="line" style="width:370px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r2" value="N" class="agree">네</label>
							<label><input type="radio" name="r2" value="Y" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_03" class="ques">
						<div  class="pclass">3. 최근 1년이내에 사마귀/점제거, 피어싱(귀뚫음 등), 문신(반영구화장 포함)을 한 경험이 있습니까?</div>
						<div class="line" style="width:20px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r3" value="N" class="agree">네</label>
							<label><input type="radio" name="r3" value="Y" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_04" class="ques">
						<div  class="pclass">4. 오늘 감기약, 두통약, 구충제(예방목적), 해열진통소염제를 섭취하였습니까?</div>
						<div class="line" style="width:220px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r4" value="N" class="agree">네</label>
							<label><input type="radio" name="r4" value="Y" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_05" class="ques">
						<div class="pclass">5. 인천(강화군), 강원(철원군), 경기(파주시, 연천군), 북한(백두한 제외 전지역)<br>
						최근 1년이내에 1일이상 6개월 미만 숙박,<br> 최근 2년이내에 6개월 이상거주 혹은 군복무를 한 적이 있습니까?</div>
						<div class="line" style="width:210px;"></div>
						<div class="label_div" style="margin-top : 50px;">
							<label><input type="radio" name="r5" value="N" class="agree">네</label>
							<label><input type="radio" name="r5" value="Y" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_06" class="ques">
						<div  class="pclass">6. 최근 1년이내에 해외를 방문한 적이 있습니까?</div>
						<div class="line" style="width:480px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r6" value="N" class="agree">네</label>
							<label><input type="radio" name="r6" value="Y" class="agree">아니오</label>
						</div>
					</div>
					<div id="qst_07" class="ques">
						<div class="pclass">7. 가족 중 B형, C형간염을 앓으신 분(보유자)이 있습니까?</div>
						<div class="line" style="width:400px;"></div>
						<div class="label_div">
							<label><input type="radio" name="r7" value="N" class="agree">네</label>
							<label><input type="radio" name="r7" value="Y" class="agree">아니오</label>
						</div>
					</div>					
				</div>
				
				<div id="cont_bottom">
					<div id="btn">
						<input type="submit" value="제출" id="btn_submit">
					</div>
			</div>
			</form>
		</div>
		
	</div>
	
	<script>
        $(document).ready(function(){
            $("form[name=YN]").submit(function(){
                return f();
            });
        });
        
        function f(){
            var radio_name = new Array;
            $(".agree").each(function(index){
                radio_name.push($(this).attr('name'));
            });
            radio_name = unique(radio_name)
            for(var i=0; i<radio_name.length; i++){
            	var result = $('input[name='+radio_name[i]+']:checked').val();
                if(result != 'Y' && result != 'N'){
                   alert('체크해주세요.');
                    $('input[name='+radio_name[i]+']:eq(0)').focus();
                    return false;
                   }
            }
            return true;
        }
        function unique(list){
            var result = [];
            $.each(list, function(i, e){
                if($.inArray(e, result) == -1)result.push(e);
            });
            return result;
        }
        
        /*
        function f(){
            var radio_name = new Array;
            $(".agree").each(function(index){
                radio_name.push($(this).attr('name'));
            });
            radio_name = unique(radio_name)
            for(var i=0; i<radio_name.length; i++){
                if($('input[name='+radio_name[i]+']:checked').val() != '1'){
                   alert('체크해주세요.');
                    $('input[name='+radio_name[i]+']:eq(0)').focus();
                    return false;
                
                   }
            }
            return true;
        }
        
        function unique(list){
            var result = [];
            $.each(list, function(i, e){
                if($.inArray(e, result) == -1)result.push(e);
            });
            return result;
        }*/
        
    </script>
</body>
</html>