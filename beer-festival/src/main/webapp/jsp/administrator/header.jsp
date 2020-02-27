<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>

<%@ taglib prefix="ctg" uri="/WEB-INF/tld/customTagLib.tld"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />
<!DOCTYPE html>
<html>
<head>
<title>Admin header</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--====================================== Script ===================================================-->
<script>
	$(function() {
		$('input [type=file] ').change(function() {
			var t = $(this).val();
			var labelText = 'File: ' + t.substr(12, t.length);
			$(this).prev('label').text(labelText);
		})
	});
</script>
<!--====================================== Link ===================================================-->
<link href="css/main.css" rel="stylesheet">
<!--===============================================================================================-->
<style type="text/css">
body {
	background-image: url("images/admin_home_page.jpg");
	background-size: 100%;
	background-color: #cccccc;
	background-repeat: repeat;
	background-attachment: fixed;
	overflow-x: scroll;
}
</style>
</head>
<body>
	<!--==================== Navy bar ====================-->
	<nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="controller?command=home"> <fmt:message
						key="label.welcome" /></a>
			</div>
			<ul class="nav navbar-nav">
				<!--==================== back button ====================-->
				<li class="active"><a href="#" onclick='history.back()'><fmt:message
							key="label.back" /></a></li>
				<!--==================== bar ====================-->
				<li><a href="controller?command=bar"><fmt:message
							key="label.bar" /></a></li>
				<!--==================== user ====================-->
				<li><a href="controller?command=users"><fmt:message
							key="label.user" /></a></li>
				<!--==================== user ====================-->
				<li><a href="controller?command=order"><fmt:message
							key="label.order" /></a></li>
				<!--==================== partner ====================-->
				<li><a href="controller?command=partners"><fmt:message
							key="label.partner" /></a></li>
				<!--==================== gallery ====================-->
				<li><a href="controller?command=gallery"><fmt:message
							key="label.gallery" /></a></li>
				<!--==================== language ====================-->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><fmt:message
							key="label.language" /><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="localization-item"><a
							href="controller?command=language"
							class="localization-link"><fmt:message key="label.en" /></a></li>
						<li class="localization-item"><a
							href="controller?command=language&language=by"
							class="localization-link"><fmt:message key="label.by" /></a></li>
						<li class="localization-item"><a
							href="controller?command=language&language=ru"
							class="localization-link"><fmt:message key="label.ru" /></a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<!-------========== logo ==========------->
				<li><a class="navbar-brand"><ctg:role role="${user.role}"/> </a></li>
				<!-------========== logo ==========------->
				<li><a class="navbar-brand"><c:out value="${user.login}" /></a></li>
				<!-------========== log out ==========------->
				<li><a href="controller?command=logout"><span
						class="glyphicon glyphicon-log-out"></span> <fmt:message
							key="label.logout" /></a></li>
			</ul>
		</div>
	</nav>
</body>
</html>