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
<title>Partner</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--====================================== Script ==================================================-->
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
	<!--=============== header =============-->
	<%@ include file="/jsp/administrator/header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	<!--=============== loop ===============-->
	<!--=============== partner ============-->
	<c:forEach items="${partner_list}" var="partner" varStatus="loop">
		<div class="well" style="text-align: left">
			<p>
				<fmt:message key="label.name" />
				:
				<c:out value="${partner.name}" />
			<p>
				<fmt:message key="label.id" />
				:
				<c:out value="${partner.partnerId}" />
			<p>
				<fmt:message key="label.description" />
				:
				<c:out value="${partner.description}" />
			<p>
				<fmt:message key="label.bar" />
				<fmt:message key="label.id" />
				:
				<c:out value="${partner.bar}" />
			<p>
		</div>
		<p>
	</c:forEach>
	<!--============== footer ==============-->
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>