<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beer Festival</title>
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
<!--====================================== Style ==================================================-->
<style type="text/css">
body {
	background-image: url("images/login_backgroung.jpg");
	background-size: 100%;
	background-color: #cccccc;
}

.carousel-caption {
	top: 200px;
	text-align: left;
	position: relative max-width: 500px;
	padding: 5px;
}
</style>
</head>
<body>
	<!--==========================================================================================-->
	<div class="container">
		<!------------ Carousel ------------>
		<div id="myCarousel" class="carousel slide">
			<!------------ Indicators ------------>
			<ol class="carousel-indicators">
				<li data-target="#carousel" data-slide-to="0" class="active"></li>
				<li data-target="#carousel" data-slide-to="1"></li>
			</ol>
			<!------------ Wrapper for slides ------------>
			<div class="carousel-inner">

				<div class="item active">
					<img src="images/login_backgroung.jpg" alt="Sign in"
						style="width: 100%;">
					<div class="carousel-caption">
						<c:choose>
							<c:when test="${message == null}">
								<h3>
									<fmt:message key="label.sign_in" />
								</h3>
							</c:when>
							<c:otherwise>
								<div>
									<audio src="media/error.mp3" autoplay></audio>
									<h3>${message}</h3>
								</div>
							</c:otherwise>
						</c:choose>
						<br>
						<!------------=== FORM ===------------>
						<form name="loginForm" method="POST" action="controller">
							<!------------ login ------------>
							<div class="form-group">
								<label for="inputLogin"
									class="col-sm-2 col-form-label text-success"><fmt:message
										key="label.login" /></label> <input type="text" name="login"
									class="form-control is-valid" id="exampleInputLogin1"
									aria-describedby="loginHelp" placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.login" />"
									required="required" pattern=".{5,12}">
							</div>
							<!------------ password ------------>
							<div class="form-group">
								<label for="inputPassword"
									class="col-sm-2 col-form-label text-danger"><fmt:message
										key="label.password" /></label> <input type="password"
									name="password" class="form-control is-invalid"
									id="exampleInputPassword1" placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.password" />"
									required="required" pattern=".{5,12}">
							</div><br>
							<!------------ submit ------------>
							<button name="command" value="login" type="submit"
								class="btn btn-primary">
								<fmt:message key="label.sign_in" />
							</button>
						</form>
						<br> <br> <br>
						<!-------=============== language ===============------->
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								<fmt:message key="label.language" />
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="controller?command=language"
									class="localization-link"><fmt:message key="label.en" /></a></li>
								<li><a href="controller?command=language&language=by"
									class="localization-link"><fmt:message key="label.by" /></a></li>
								<li><a href="controller?command=language&language=ru"
									class="localization-link"><fmt:message key="label.ru" /></a></li>
							</ul>
						</div>
					</div>
				</div>
				<!--=========== Logup Form ===============================================================-->
				<div class="item">
					<img src="images/login_backgroung.jpg" alt="Sign up"
						style="width: 100%;">
					<div class="carousel-caption">
						<h3>
							<fmt:message key="label.sign_up" />
						</h3>
						<div>
							<h3>${message}</h3>
						</div>
						<form name="logupForm" method="POST" action="controller">
							<!------------ role ------------>
							<a href="#demo" data-toggle="collapse"><fmt:message key="label.choose_role" /></a>
							<div id="demo" class="collapse">
							<br>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="role"
										id="exampleRadios1" value="user" checked> <label
										class="form-check-label" for="exampleRadios1">
										<fmt:message key="label.guest" /></label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="role"
										id="exampleRadios2" value="partner"> <label
										class="form-check-label" for="exampleRadios2">
										<fmt:message key="label.partner" /></label>
								</div>
								<br>
							</div>
							<!------------ login ------------>
							<div class="form-group">
								<br> <label for="inputLogin"
									class="col-sm-2 col-form-label text-success"><fmt:message
										key="label.login" /></label> <input type="text" name="login"
									class="form-control is-valid" id="exampleInputLogin1"
									aria-describedby="loginHelp" placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.login" />"
									required="required" pattern="^[a-zA-Z0-9]+$">
							</div>
							<!------------ password ------------>
							<div class="form-group">
								<label for="inputPassword"
									class="col-sm-2 col-form-label text-danger"><fmt:message
										key="label.password" /></label> <input type="password"
									name="password" class="form-control is-invalid"
									id="exampleInputPassword1" placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.password" />"
									required="required" pattern="^[a-zA-Z0-9]+$">
							</div>
							<!------------ email -------------->
							<div class="form-group">
								<label for="inputEmail"
									class="col-sm-2 col-form-label text-success"><fmt:message
										key="label.mail" /></label> <input type="email" name="email"
									class="form-control" id="exampleInputEmail1"
									placeholder="<fmt:message
										key="label.enter" /> Email" required="required">
							</div>
							<!------------ submit ------------->
							<button name="command" value="register" type="submit"
								class="btn btn-primary">
								<fmt:message key="label.sign_up" />
							</button>
						</form>
					</div>
				</div>
			</div>
			<!------------ Left and right controls ------------>
			<a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only"></span>
			</a> <a class="left carousel-control" href="#myCarousel"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only"></span>
			</a>
		</div>
	</div>
	<!--===============================================================================================-->
</body>
</html>