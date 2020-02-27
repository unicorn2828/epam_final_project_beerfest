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
<title>User header</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<script type="text/javascript">
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
	background-image: url("images/user_home_page.jpg");
	background-size: 100%;
	background-color: #cccccc;
	background-attachment: fixed;
}
</style>
</head>
<body>
	<!-----------------=============== Navy bar ===============----------------->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="controller?command=home"> <fmt:message
						key="label.welcome" /></a>
			</div>
			<ul class="nav navbar-nav">
				<!-------=============== back button ===============------->
				<li class="active"><a href="#" onclick='history.back()'><fmt:message
							key="label.back" /></a></li>
				<!-------=============== bar ===============------->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><fmt:message key="label.bar" />
						<span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li class="localization-item"><a
							href="controller?command=bar" class="localization-link"><fmt:message
									key="label.search_order" /></a></li>
					</ul></li>
				<!-------=============== order ===============------->
				<li><a href=# data-toggle="modal" data-target="#orderModal"><fmt:message
							key="label.order" /></a></li>
				<!-------=============== gallery ===============------->
				<li><a href="controller?command=gallery"><fmt:message
							key="label.gallery" /></a></li>
				<!-------=============== language ===============------->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><fmt:message
							key="label.language" /><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="localization-item"><a
							href="controller?command=language" class="localization-link"><fmt:message
									key="label.en" /></a></li>
						<li class="localization-item"><a
							href="controller?command=language&language=by"
							class="localization-link"><fmt:message key="label.by" /></a></li>
						<li class="localization-item"><a
							href="controller?command=language&language=ru"
							class="localization-link"><fmt:message key="label.ru" /></a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<!-------========== custom tag ==========------->
				<li><a class="navbar-brand"><ctg:role role="${user.role}" />
				</a></li>
				<!--=============== avatar ===============-->
				<c:if test="${user.role == 'USER'}">
					<li><img data-scr="${user.avatarUrl}" src="${user.avatarUrl}"
						alt="Avatar" class="avatar"></li>
					<li><a></a></li>
				</c:if>
				<!--=============== logo =================-->

				<li><a class="navbar-brand" href=# data-toggle="modal"
					data-target="#avatarModal"><c:out value="${user.login}" /></a></li>

				<!--=============== log out ==============-->
				<li><a href="controller?command=logout"><span
						class="glyphicon glyphicon-log-out"></span> <fmt:message
							key="label.logout" /></a></li>
			</ul>
		</div>
	</nav>
	<!--===================================== MODALS ========================================-->
	<!--========== order modal ==========-->
	<div class="modal fade" id="orderModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="exampleModalLabel">
						<fmt:message key="label.order" />
					</h2>
				</div>
				<div class="modal-body">
					<c:if test="${user.order.orderId == 0}">
						<p>
							<fmt:message key="label.search_order_text1" />
						<p>
							<fmt:message key="label.search_order_text2" />
					</c:if>
					<c:if test="${user.order.orderId != 0}">
						<p>
							<fmt:message key="label.order" />
							#
							<c:out value="${user.order.orderId}" />
						<p>
							<fmt:message key="label.seat" />
							:
							<c:out value="${user.order.seats}" />
						<p>
							<fmt:message key="label.bar" />
							:
							<c:out value="${user.order.bar.name}" />
						<p>
							<fmt:message key="label.comment" />
							:
							<c:out value="${user.order.comment}" />
					</c:if>
				</div>
				<div class="modal-footer">
					<c:if test="${user.order.orderId == 0}">
						<form name="bar" method="POST" action="controller">
							<button name="command" value="bar" type="submit"
								class="btn btn-primary btn-block center-block">
								<fmt:message key="label.search_order" />
							</button>
						</form>
						<p>
					</c:if>
					<p>
						<button type="button"
							class="btn btn-primary btn-block center-block"
							data-dismiss="modal">
							<fmt:message key="label.close" />
						</button>
						<!--========== delete order ==========-->
						<c:if test="${user.order.orderId != 0}">
							<button type="button" data-toggle="modal"
								data-target="#deleteModal"
								class="btn btn-danger btn-block center-block">
								<fmt:message key="label.delete" />
							</button>
						</c:if>
						<!--==================================-->
				</div>
			</div>
		</div>
	</div>
	<!--==================== delete modal ====================-->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="exampleModalLabel">
						<fmt:message key="label.delete" />
					</h2>
				</div>
				<div class="modal-body">
					<p>
						<fmt:message key="label.delete" />
						?
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-primary btn-block center-block"
						data-dismiss="modal">
						<fmt:message key="label.no_thanks" />
					</button>
					<p>
					<form name="bar" method="POST" action="controller">
						<button name="command" value="delete_order" type="submit"
							class="btn btn-danger btn-block center-block">
							<fmt:message key="label.delete" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--================== avatar modal ==================-->
	<div class="modal fade" id="avatarModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="exampleModalLabel">
						<fmt:message key="label.load" />
					</h2>
				</div>
				<form name="bar" method="POST" action="uploadController"
					enctype="multipart/form-data">
					<div class="modal-body">
						<span class="control-fileupload center-block"> <label
							for="fileInput"></label> <input type="file"
							id="fileInput" name="file" required="required" accept="image/*"
							title="no file">
						</span>
					</div>
					<div class="modal-footer">
						<p>
							<button name="command" value="upload" type="submit"
								class="btn btn-primary btn-block center-block">
								<fmt:message key="label.load" />
							</button>
						<p>
							<button type="button"
								class="btn btn-primary btn-block center-block"
								data-dismiss="modal">
								<fmt:message key="label.close" />
							</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>