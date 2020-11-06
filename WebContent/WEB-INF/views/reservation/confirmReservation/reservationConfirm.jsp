<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>


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
	
	#section_right>#section_right_top{
		width: 1200px;
		height: 500px;
		border-bottom: 1px solid black;
	}
	
	#section_right>#section_right_bottom{
		width: 1200px;
		height: 500px;
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
					<div id="section_right_top"><%@ include file="../personal/personalReservationConfirm.jsp" %></div>
					<div id="section_right_bottom"><%@ include file="../group/groupReservationConfirm.jsp" %></div>
				</div>

			</section>
			<br>
			<br>

			<footer id="footers"></footer>
		</div>
	</div>
</body>
</html>