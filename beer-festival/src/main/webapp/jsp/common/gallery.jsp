<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties.pagecontent" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Gallery</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--====================================== Script =================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--====================================== Link ===================================================-->
<link href="css/main.css" rel="stylesheet">
<!--===============================================================================================-->
<style type="text/css">
body {
	background-image: url("images/login_backgroung.jpg");
	background-size: 100%;
	background-color: #cccccc;
}
</style>
</head>
<body>
	<!--=============== header ===============-->
	<jsp:include page="/jsp/${fn:toLowerCase(user.role)}/header.jsp" />
	<br>
	<br>
	<br>
	<br>
	<!--============== page container ========-->
	<audio src="media/1.mp3" autoplay></audio>
	<div class="container">
		<!------------ Carousel ------------>
		<div id="myCarousel" class="carousel slide">
			<!------------ Indicators ------------>
			<ol class="carousel-indicators">
				<li data-target="#carousel" data-slide-to="0" class="active"></li>
				<li data-target="#carousel" data-slide-to="1"></li>
				<li data-target="#carousel" data-slide-to="2"></li>
				<li data-target="#carousel" data-slide-to="3"></li>
				<li data-target="#carousel" data-slide-to="4"></li>
			</ol>
			<!------------ Wrapper for slides ------------>
			<div class="carousel-inner">
				<!--======== 0 ===============================================================-->
				<div class="item active">
					<img src="images/gallery/gallery4.jpg" alt="Sign in"
						style="width: 80%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.welcome" />
						</h3>
					</div>
				</div>
				<!--======== 1 ===============================================================-->
				<div class="item">
					<img src="images/gallery/gallery1.jpg" alt="Sign up"
						style="width: 80%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.welcome" />
						</h3>
					</div>
				</div>
				<!--======== 2 ===============================================================-->
				<div class="item">
					<img src="images/gallery/gallery2.jpg" alt="Sign up"
						style="width: 80%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.welcome" />
						</h3>
					</div>
				</div>
				<!--======== 3 ===============================================================-->
				<div class="item">
					<img src="images/gallery/gallery5.jpg" alt="Sign up"
						style="width: 80%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.welcome" />
						</h3>
					</div>
				</div>
				<!--======== 4 ===============================================================-->
				<div class="item">
					<img src="images/gallery/gallery6.jpg" alt="Sign up"
						style="width: 80%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.welcome" />
						</h3>
					</div>
				</div>
			</div>
			<!------------ Left and right controls ------------>
			<a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<!--=============== FOOTER ===============-->
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>