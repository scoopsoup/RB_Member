<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의 소리</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


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

<style type="text/css">
/* 시멘틱 구조영역 스타일 */
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

#section_right {
	position: absolute;
	display: block;
	width: 1200px;
	height: 1000px;
	/* 	border: 1px solid black; */
	margin-left: 300px;
	clear: both;
}
/* 검색기 */
#search {
	float: right;
	margin-right:40px;
	margin-bottom: 50px;  
}
/* 게시글 리스트 */
#title {
	margin-left: 30px;
}

#mainboard table {
	border-collapse: collapse;
	border: 1px solid #ddd;
	border-top: 3px solid #168;
	width: 1100px;
	height: 600px;
	margin-left: 60px;
}

.table tr, .table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	width: 155px;
	height: 45px;
}

#mainboard thead {
	color: #168;
	padding: 10px;
}

#mainboard thead th {
	height: 40px;
}

#mainboard tbody tr:hover {
	background-color: #eeeeee;
	color: firebrick;
}

A:link {
	text-decoration: none;
	color: #000000;
}

A:visited {
	text-decoration: none;
	color: #000000;
}

A:active {
	text-decoration: none;
	color: #000000;
}

A:hover {
	text-decoration: none;
	color: firebrick;
}
/* 글쓰기 버튼 */
#submit_btn {
	width: 100px;
	height: 40px;
	line-height: 20px;
	float: right;
	text-align: center;
	margin-right: 100px;
	margin-top: 20px;
	background-color: gray;
	border: 2px solid gray;
	clear: both;
}

#submit_btn a {
	margin-top: 10px;
	font-size: 1.5em;
	display: block;
	height: 40px;
	color: white;
	text-decoration: none;
}

#submit_btn:hover {
	background-color: white;
	border: 2px solid firebrick;
}

#submit_btn:hover a {
	color: firebrick;
}
/* pagination */

 #page {
        position: relative;
        text-align: center;
       
        margin-bottom: 10px;
        height: 30px;
        align-content: center;
        clear: both;
    }
    #page>li {
        display: inline-block;
        align-content: center;
    }
    #page a {
    display: inline-block;
        color: gray;
        font-size: 1.5em;
        background-color: white; 
        margin-right: 5px;
    }
    #page>li:hover{
        /*font-style: firebrick;*/
        font-weight: bold;
        /* transform: scale(1.3); */
    }
    #page a:hover {
        color: firebrick;
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
					<!-- 페이지 제목-->
					
					<div id="title">
						<ul>
							<h2>고객의 소리</h2>
							<br>

							<h3>고객의 소리 검색</h3>
							<div  id="search">
								<form action="/memboard/search" method="post">
									<select name="searchSelect">
										<option value='both' selected>[내용+제목]</option>
										<option value='content'>[내용]</option>
										<option value='title'>[제목]]</option>
									</select> <input type="text" name="search"> <input type="submit"
										value="검색">
								</form>
							</div>


						</ul>
					</div>
					
					<form action="test()">

						<!-- 게시글 리스트-->
						<div id="mainboard">
							<table class="table table-striped">
								<thead>
									<tr>
										<th style="background-color: lightgray; text-align: center;">번호</th>

										<th style="background-color: lightgray; text-align: center;">제목</th>

										<th style="background-color: lightgray; text-align: center;">작성자</th>

										<th style="background-color: lightgray; text-align: center;">작성일</th>




									</tr>
								</thead>
								<tbody>

									<c:forEach items="${mList }" var="memboard" varStatus="index">
										<tr>
											<td>${memboard.mbNo }</td>

											<td><a href="/memboard/select?mbNo=${memboard.mbNo}">${memboard.mbTitle }</a></td>
											<td>${memboard.userId }</td>
											<td>${memboard.mbDate }</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>


					</form>
					<!-- 버튼-->
					<div id="submit_btn">
						<a href="/memboard/writeForm">글쓰기</a>
					</div>

					<!-- pagination -->
					<div id="pagediv">

						<ul id="page">
							<li>${pageNavi}</li>
						</ul>

					</div>



				</div>

			</section>

			<footer id="footers"></footer>
		</div>
	</div>


</body>