<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.table {
      border-collapse: collapse;
      border-top: 3px solid #168;
      margin-left: 40px;
    }  
    .table th {
      color: #168;
      /*background: #f0f6f9;*/
      background: #dcdcdc;
      text-align: center;
      
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
      text-align: center;
      width: 155px;
      height: 10px;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    .table tr{
    	height: 60px;
    }
</style>
</head>
<body>
	<div id="right_whole">
		<div id="right_head">
			<h1 style="font-weight: 900;">단체예약정보 확인</h1>
	    </div>
		<div id="right_middle">
			<table class="table">
				<thead>
					<tr>
						<th>No.</th>
						<th>예약자</th>
						<th>날짜</th>
						<th>시간</th>
						<th>장소</th>
						<th>예약인원</th>
						<th>헌혈종류</th>
						<th>예약상태</th>
						<th>취소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gList }" var="reservationInfo" varStatus="index">
						<tr>
							<td>${reservationInfo.rgNo }</td>
							<td>${reservationInfo.userId }</td>
							<td>${reservationInfo.rgDate }</td>
							<td>${reservationInfo.rgTime }</td>
							<td>${reservationInfo.rgPlace }</td>
							<td>${reservationInfo.rgPeople }</td>
							<td>전혈</td>
							<td>${reservationInfo.rgState }</td>
							<td class="btn">
								<c:if test="${reservationInfo.rgState eq '승인' }">
									<div id="btn1">
										<input type="button" value="취소" onclick="location.href='/group/reservation/cancle?groupReservationNo=${reservationInfo.rgNo }'">
									</div>
								</c:if>
								<c:if test="${reservationInfo.rgState eq '대기' }">
									<div id="btn1">
										<input type="button" value="취소" onclick="location.href='/group/reservation/cancle?groupReservationNo=${reservationInfo.rgNo }'">
									</div>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="9" align="center">${groupPagaDataNavi }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>