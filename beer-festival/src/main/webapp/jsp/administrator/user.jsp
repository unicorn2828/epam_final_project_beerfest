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
<title>User</title>
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
</head>
<body>
	<!--=============== header ===============-->
	<%@ include file="/jsp/administrator/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<!--=============== pagination =================-->
	<ul class="pagination pull-left" style="position: fixed">
		<c:forEach begin="1" end="${maxValue}" varStatus="loop">
			<form class="page-item" id="update" action="controller" method="POST">
				<input type="hidden" name="pageNumber" value="${loop.count-1}" /> 
				<input type="hidden" name="command" value="users" />
				<button type="submit" class="page-link button_success">${loop.count}</button>
			</form>
		</c:forEach>
	</ul>
	<!--=============== user loop =================-->
	<c:forEach items="${user_list}" var="user" varStatus="loop">
		<div class="well" style="text-align: left">
			<p>
				<fmt:message key="label.role" />
				:
				<c:out value="${user.role}" />
			<p>
				<fmt:message key="label.id" />
				:
				<c:out value="${user.userId}" />
			<p>
				<fmt:message key="label.login" />
				:
				<c:out value="${user.login}" />
				<c:choose>
					<c:when test="${user.status == 'BLOCK'}">
						<div style="color: red">
							<p>
								<fmt:message key="label.status" />
								:
								<c:out value="${user.status}" />
						</div>
					</c:when>
					<c:otherwise>
						<div style="color: green">
							<p>
								<fmt:message key="label.status" />
								:
								<c:out value="${user.status}" />
						</div>
					</c:otherwise>
				</c:choose>
				<c:if test="${user.order.orderId != '0'}">
					<p>
						<fmt:message key="label.order" />
						:
						<c:out value="${user.order}" />
				</c:if>
				<c:if test="${user.role ne 'ADMINISTRATOR'}">
					<c:if
						test="${user.avatarUrl ne 'images/avatar/default_avatar.png'}">
						<p>
							<fmt:message key="label.avatar" />
							:
							<c:out value="${user.avatarUrl}" />
					</c:if>
				</c:if>
			<p>
				<!--============= block/unblock =============-->
				<c:if test="${user.role ne 'ADMINISTRATOR'}">
					<form name="blockForm" method="POST" action="controller">
						<c:choose>
							<c:when test="${user.status eq 'VALID'}">
								<input type="hidden" value="${user.userId}" name="userId">
								<button name="command" value="block_user" type="submit"
									class="btn btn-lg btn-danger center-block">
									<fmt:message key="label.block" />
								</button>
							</c:when>
							<c:otherwise>
								<input type="hidden" value="${user.userId}" name="userId">
								<button name="command" value="block_user" type="submit"
									class="btn btn-lg btn-success center-block">
									<fmt:message key="label.unblock" />
								</button>
							</c:otherwise>
						</c:choose>
					</form>
				</c:if>
		</div>
		<p>
	</c:forEach>
	<!-------========== footer ==========------->
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>