<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>-->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의 소리</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>

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
/* 게시글 리스트 */
#title {
	margin-left: 30px;
}

#mainboard table {
	border-collapse: collapse;
	border: 1px solid #ddd;
	border-top: 3px solid #168;
	width: 1100px;
	margin-left: 60px;
}

.table tr, .table td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: left;
	width: 155px;
	height: 45px;
}
.table td{
	border: 1px solid #ddd;
	height: 40px;
}
#mainboardt thead {
	color: #168;
	padding: 10px;
}

#mainboard thead th {
	height: 40px;
}

/* #mainboard tbody tr:hover {
	background-color: #eeeeee;
	color: firebrick;
} */

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

/* A:hover {
	text-decoration: none;
	color: firebrick;
} */

/* 버튼 */
#btn {
	float: right;
	display: inline-block;
	margin-right: 52px;
	margin-top: 20px;
}

.btn {
	width: 100px;
	height: 40px;
	line-height: 20px;
	text-align: center;
	display: inline-block;
	margin: 5px;
	background-color: gray;
	border: 2px solid gray;
	clear: both;
}

.btn a {
	margin-top: 10px;
	font-size: 1.5em;
	display: block;
	height: 40px;
	color: white;
	text-decoration: none;
}

.btn:hover {
	background-color: white;
	border: 2px solid firebrick;
}

.btn:hover a {
	color: firebrick;
}
/*commentContent*/
#commentBoard {
	width: 1100px;
	height: 300px;
	margin-left: 60px;
	clear: both;
	position: relative;
	border: 1px solid #ddd;
}

#commentTable {
	width: 1000px;
	table-layout: fixed;
}

#commentList {
	overflow: scroll;
	height: 200px;
}

#commentList thead {
	height: 45px;
}

#commentList tr, #commentList td {
	border: 1px solid #ddd;
}

/* #comNo, #userId {
	width: 10%;
} */
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
							<br>
						</ul>
					</div>


					<!-- content -->
					<form action="test()">
						<input type="hidden" id="mbNo" value="${content.mbNo }">
						<input type="hidden" id="userId" value="${content.userId }">
						<div id="mainboard">
							<table class="table table-striped">
								<thead>
									<tr>
										<th colspan="3">&nbsp;&nbsp;글제목 : ${content.mbTitle }</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td style="height:40px;">글번호 : ${content.mbNo }</td>
										<td style="height:40px;">작성자 : ${content.userId }</td> 
										<td style="height:40px;">작성일 : ${content.mbDate }</td>
									</tr>
									<tr>
										<td colspan="3" style="height:520px;">${content.mbCont }</td>
									</tr>
								</tbody>

							</table>
						</div>

					</form>
					
					<!-- 버튼-->
					<div id="btn_div">
						<input type="button"
							onclick="location.href='/memboard/delete?mbNo=${content.mbNo }'"
							value="삭제"></input>
							 <input type="button"
							onclick="location.href='/memboard/modifyForm?mbNo=${content.mbNo }'"
							value="수정"></input> <input type="button"
							onclick="location.href='/memboard/list'" value="목록"></input>
					</div>


					<!-- 덧글 출력 폼 -->
					<div id="commentBoard">
						<div id="commentList">
							<table id="commentTable">
								<thead>
									<tr>
										<th id="comNo"
											style="width: 5%; background-color: lightgray; text-align: center;">번호</th>

										<th
											style="width: 90%; background-color: lightgray; text-align: center;">내용</th>

										<th id="userId"
											style="width: 10%; background-color: lightgray; text-align: center;">작성자</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${mcList }" var="comment" varStatus="index">
										<tr>
											<td style="text-align: center;">${index.count }</td>
											<td>${comment.commentContent }</td>
											<td style="text-align: center;">${comment.userId}</td>
											<td>
												

												<form action="/memboard/commentDelete" method="get">
													<input type="hidden" name="noticeNo"
														value="${content.mbNo }"> <input type="hidden"
														name="comNo" value="${comment.memNo}"> <input
														type="submit" value="delete">
												</form>

											</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>
						<!--  덧글 입력 폼 -->
						<div id="commentInsert">
							<form action="/memboard/insertComment" method="post">
								<input type="hidden" id="noticeNo" name="noticeNo"
									value="${content.mbNo }">
								<div id="comment">
									<!-- 세션 값이 null 아닐시 댓글작성가능 -->
									<%-- <c:if test="${session.userID != null }">
 --%>
									<textarea name="boardCommentContent" id="commentContent"
										placeholder="댓글을 작성하세요"
										onKeyUp="javascript:fnChkByte(this,'100')"></textarea>
									<script type="text/javascript">
									$(document).ready(function() {

										$('#commentContent').on('keyup',function() {
											if ($(this).val().length > 100) {
											$(this).val(
											$(this).val().substring(0,100));
															
											}
										});
									});
									</script>

									<br>

									<button type="submit" id="writeComment">댓글작성</button>
									<hr>
									<div class="box-footer"></div>
								</div>
							</form>
						</div>
					</div>




				</div>

			</section>

			<footer id="footers"></footer>
		</div>
	</div>


</body>
</html>