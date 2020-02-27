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
<html>
<head>
<meta charset="UTF-8">
<title>Bar</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--====================================== Script =================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--====================================== Modal Script ===========================================-->
<script type="text/javascript">
	$(window).on('load', function() {
		$('#barModal').modal('show');
	});
</script>
<!--====================================== Link ===================================================-->
<link href="css/main.css" rel="stylesheet">
<!--====================================== Style ==================================================-->
</head>
<body>
	<!--=============== header ===============-->
	<%@ include file="/jsp/user/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<!--=============== loop =================-->
	<!--=============== bar ==================-->
	<c:forEach items="${bar_list}" var="bar" varStatus="loop">
		<div class="well">
			<p>
				<a data-toggle="collapse" data-target="#${loop.index+1}"
					class="btn btn-lg btn-primary" href="#" role="button"><c:out
						value="${bar.name}" /> &raquo;</a>
			<div id="${loop.index+1}" class="collapse">
				<p>
					<fmt:message key="label.name" />
					:
					<c:out value="${bar.name}" />
				<p>
					<fmt:message key="label.seats" />
					:
					<c:out value="${bar.seats}" />
				<p>
					<fmt:message key="label.description" />
					:
					<c:out value="${bar.description}" />
				<p>
					<fmt:message key="label.seats" />
					<c:choose>
						<c:when test="${bar.seats > 0}">
							: <fmt:message key="label.free_seats1" />
						</c:when>
						<c:otherwise>
							: <fmt:message key="label.free_seats2" />
						</c:otherwise>
					</c:choose>
				<p>
					<!-------========== order ==========------->
					<c:if test="${bar.seats > 0}">
						<a data-toggle="collapse" data-target="#loop${loop.index+1}"
							class="btn btn-lg btn-primary" href="#" role="button"><fmt:message
								key="label.reserve" /> &raquo;</a>
						<div id="loop${loop.index+1}" class="collapse">
							<c:if test="${user.order.orderId != 0}">
								<p>
									<fmt:message key="label.have_order1" />
								<p>
									<fmt:message key="label.have_order2" />
									<fmt:message key="label.order" />
							</c:if>
							<c:if test="${user.order.orderId == 0 && bar.seats > 0}">
								<form name="loginForm" method="POST" action="controller">
									<!------------ seats ------------>
									<div class="form-group">
										<label for="exampleInputLogin1"><fmt:message
												key="label.seat" /></label> <input type="text" name="seats"
											class="form-control" id="exampleInputLogin1"
											aria-describedby="loginHelp"
											placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.seat" />"
											style="text-align: center" required="required"
											pattern="\d{1,3}">
									</div>
									<!------------ comment ------------>
									<div class="form-group">
										<label for="exampleInputLogin1"><fmt:message
												key="label.comment" /></label> <input type="text"
											name="comment" class="form-control" id="exampleInputLogin1"
											aria-describedby="loginHelp"
											placeholder="<fmt:message
										key="label.enter" /> <fmt:message key="label.comment" />"
											required="required" style="text-align: center">
									</div>
									<input type="hidden" value="${bar.barId}" name="bar">
									<!-------========== submit button ==========------->
									<button name="command" value="order_register" type="submit"
										class="btn btn-primary">
										<fmt:message key="label.submit" />
									</button>
								</form>
							</c:if>
						</div>
					</c:if>
					<!-------========== order end ==========------->
			</div>
		</div>
		<p>
	</c:forEach>
	<!-----=============== bar modal ===============----->
	<c:if test="${message != null}">
		<div class="modal fade" id="barModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h2 class="modal-title" id="exampleModalLabel">
							<fmt:message key="label.order" />
						</h2>
					</div>
					<div class="modal-body">
						<p>${message}
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-primary btn-block center-block"
							data-dismiss="modal">
							<fmt:message key="label.close" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-------========== footer ==========------->
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>