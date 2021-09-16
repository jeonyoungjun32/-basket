<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<!-- 개 하나의 상세 정보를 출력해주는 뷰 페이지 입니다! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 뷰 페이지!!</title>
<style type="text/css">
#listForm {
	margin: 0 auto;
	width: 640px;
	border: 1px solid red;
}

h2 {
	text-align: center;
}

img {
	width: 280px;
	height: 280px;
	border: none;
}

#content_main {
	height: 300px;
}

#content_left {
	width: 300px;
	float: left;
}

#content_right {
	width: 340px;
	float: left;
}
#desc{
	height: 170px;
	background: #F8E0F7;
}
#commandList{
	text-align: center;
}

</style>
</head>
<body>
	<!-- //책 -752- 두번째 그림(digList.jsp) 그림을 보면서 해야 된다  -->
	<%
		//request
	%>
	<section id="">
		<!-- kind : 푸들, 진돗개, 개의종류 -->
		<h2>${dog.kind }의상세정보</h2>
		<section id="content_main">
			<section id="content_left">
				<img alt="" src="images/${dog.image }"> <!-- 시팔 이렇게도 이미지 넣네?? -->

			</section>
			<section id="content_right">
				<b>품종 : ${dog.kind } <br/>
				<b>가격 : ${dog.price } <br/>
				<b>신장 : ${dog.height } <br/>
				<b>체중 : ${dog.weight } <br/>
				<b>원산지 : ${dog.country } <br/>
				<p id="desc">
					<b>내용 : </b> ${dog.kind } <br/>
				</p>
			</section>
		</section>
		
		<div style="clear: both"></div>
		<a href="dogList.jsp">쇼핑 계속하기</a>
		<a href="dogCartAdd.dog?id=${dog.id }">장바구니에 담기</a> <!-- 보낼때 dog.id를 담아 보내겠다 -->
		장바구니에 담기 
		
		
</body>
</html>










