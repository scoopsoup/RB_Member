<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList,resResult.model.vo.ResPriv"%>
    
<%
       ArrayList<ResPriv> list = (ArrayList<ResPriv>)request.getAttribute("list");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RB - 문진결과조회</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a332c1cec6.js" crossorigin="anonymous"></script>
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

<script type="text/javascript">
   // banner script
   $(document).ready(
      
      // banner 설정 및 시간차 조정 
      function() {
         var $banner = $(".banner").find("ul");
         // 이미지 폭
         var $bannerWidth = $banner.children().outerWidth();
         // 이미지 높이
         var $bannerHeight = $banner.children().outerHeight(); 
         // 이미지 갯수
         var $length = $banner.children().length;
         var rollingId;
   
         //다음 이미지로 롤링 애니메이션 할 시간차
         rollingId = setInterval(function() {
            rollingStart();
         }, 5000);
   
         function rollingStart() {
            $banner.css("width", $bannerWidth * $length   + "px");
            $banner.css("height", $bannerHeight + "px");
            
            // 배너의 좌측 위치 이동
            $banner.animate({
               left : -$bannerWidth + "px"
            }, 1500, function() { 
               
               // 첫번째 이미지를 마지막 끝에 복사해서 추가
               $(this).append(
                     "<li>"+ $(this).find("li:first").html() + "</li>"
               );
               // 뒤로 복사 후 첫번재 이미지를 삭제
               $(this).find("li:first").remove();
               // 다음을 위해서 배너 좌측의 위치값 초기화
               $(this).css("left", 0);
      
            });
         }
   });
</script>

 <script>
        function check(status) {
           var list = document.getElementById("bottom");
             list.style.display = 'block';
           
           $.ajax({
             url : '<%=request.getContextPath()%>/ResResult',
             data : {num:status},
             success:function(data){
                console.log(data)
                $('#rB').text(data.ResB);
                $('#rC').text(data.ResC);
                $('#rALT').text(data.ResAlt);
                $('#rPro').text(data.ResPro);
                $('#rAST').text(data.ResAst);
                $('#rCOL').text(data.ResCol);
             }
          })
        }
        $(function(){
           $('#tb_bottom .fa-times').click(function(){
              var list = document.getElementById("bottom");
              list.style.display = 'none';
           })
        })
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
   height: 1050px;
   border-right: 1px solid black;
   box-sizing: border-box;
   float: left;
}

/* 왼쪽 네브바 css */
#left_mainNav{
   position: relative;
   background-color: #f3e7e9;
   display: block;
   width: 275px;
   height: 80px;
   margin-top: 0;
}

.left_subNav{
   position: relative;
   border-bottom: 2.7px solid #cfe2ff;
   display: block;
   width: 275px;
   height: 70px;
   
}

.subA:hover{
   background-color: #e3eeff;
}
.subA{
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
   height: 1050px;
   margin-left: 300px;
}

/*----------------------------------------------*/
/*section-right꾸미기*/
.table {
   border-collapse: collapse;
   border-top: 3px solid #168;
   margin-left: 40px;
   margin-top: 30px;
   position: absolute;
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

.table tr td:first-child {
   text-align: center;
}

.table tbody tr:hover {
   background-color: #eeeeee;
   color: #168;
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
   
   /* ******************************************* */
 
        p {
            text-align: center;
            font-size : 25px;
            font-weight: 900;
        }
        table {
            width : 800px;
            margin : 0 auto;
            text-align: center;
            border-collapse: collapse;
            border-bottom: 2px solid lightgray;
            border-top : 2px solid lightgray;
            margin-top : 30px;
        }
        tr {
            height : 50px;
        }
        th {
            border-bottom : 3px solid lightgray;
            height : 40px;
        }
        .col1 {
            width : 10%;
        }
        #bottom {
            display : none;
        }

        fieldset {
           width : 900px;
           height : 400px;
           margin : 0 auto;
           margin-top : 60px;
           border : 4px solid lightgray;
           }
      legend {
         text-align : center;
         font-size : 30px;
         font-weight : 900;
      }
      #tb_bottom fieldset{
          position: relative;
      }
      #tb_bottom .fa-times{
          position: absolute;
          top: 25px; right: 10px;
          font-size: 25px;
          cursor: pointer;
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
         <div id="left_mainNav"><h1 style="margin-left:20px; position: absolute;">마이페이지</h1></div>
               <div class="left_subNav"><a href="/ResList" class="subA">헌혈기록 및 검사결과 조회</a></div>
               <div class="left_subNav"><a href="/modify/form" class="subA">정보수정 및 탈퇴</a></div>
         </div>
         <div id="section_right">
            <div id="top">
               <div id="tb_top">
               <fieldset>
               <legend>헌혈 검사 확인</legend>
                  <table>
                        <thead>
                     <tr>
                        <th></th><th>헌혈날짜</th><th>결과</th><th>상세보기</th>
                     </tr>
                        </thead>
                     <tbody>
                        <% for(int i=0; i < list.size(); i++){ %>
                           <tr>
                              <td class="col1"><%=list.get(i).getRpNo() %></td>
                              <td><%=list.get(i).getRpDate()%> <%=list.get(i).getRpTime()%></td>
                              <td><%=list.get(i).getRpState() %></td><td><input type="submit" value="상세확인" onclick= "check(<%=list.get(i).getRpNo()%>)"></td>
                        <% }%>
                        </tbody>
                  </table>
                  </fieldset>
               </div>   
            </div>
            <div id="bottom" style="display: none">
            <div id="bottom_div">
                <div id="tb_bottom">
                <fieldset style="height:450px;">
                  <i class="fas fa-times"></i>
                   <legend>헌혈 검사 결과 확인</legend>
                     <table>
                           <thead>
                        <tr>
                           <th>검사항목</th><th>검사결과</th><th>정상치</th>
                        </tr>
                           </thead>
                        <tbody>
                           <tr>
                           <td>B형간염</td><td id="rB"></td><td>-</td>
                        </tr>
                           <tr>
                           <td>C형간염</td><td id="rC"></td><td>-</td>
                        </tr>
                           <tr>
                           <td>ALT</td><td id="rALT"></td><td>0~33</td>
                        </tr>
                           <tr>
                           <td>Protein</td><td id="rPro"></td><td>6.6~8.7</td>
                        </tr>
                           <tr>
                           <td>AST</td><td id="rAST"></td><td>0~32</td>
                        </tr>
                        <tr>
                        <td>col</td><td id="rCOL"></td><td>0~60</td>
                        </tr>
                               
                           </tbody>
                     </table>
                  </fieldset>
               </div>
               
               <script>
                     
               </script>
            </div>
         </div>
         </div>
      </section><br><br>
      
      <footer id="footers"></footer>
   </div>
</div>
</body>
</html>