<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--====================================== Script =================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	<div class="item active">
		<img src="images/login_backgroung.jpg" alt="Sign in"
			style="width: 100%;">
		<div class="carousel-caption">
			<h3>
				<fmt:message key="label.reg-form" />
			</h3>
			<br>
			<!--=============== form ==================-->
			<form name="loginForm" method="POST" action="controller">
				<!--=========== company name ==========-->
				<div class="form-group">
					<label for="exampleInputLogin1"> <fmt:message
							key="label.name" /></label> <input type="text" name="name"
						class="form-control" id="exampleInputLogin1"
						aria-describedby="loginHelp"
						placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.name" />"
						required="required">
				</div>
				<!--=========== company description ===-->
				<div class="form-group">
					<label for="exampleInputPassword1"><fmt:message
							key="label.description" /></label> <input type="text" name="description"
						class="form-control" id="exampleInputPassword1"
						placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.description" />"
						required="required">
				</div>
				<!--========== submit button ==========-->
				<div>
					<h3>${message}</h3>
				</div>
				<button name="command" value="partner_register" type="submit"
					class="btn btn-primary">
					<fmt:message key="label.submit" />
				</button>
				<!--========== back button ============-->
				<button type="button" onclick='history.back()' value="BACK"
					class="btn btn-primary">
					<fmt:message key="label.back" />
				</button>
			</form>
		</div>
	</div>
	<!--===============================================================================================-->
</body>
</html>