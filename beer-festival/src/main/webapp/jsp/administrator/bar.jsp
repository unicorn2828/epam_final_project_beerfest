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
<!--===============================================================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<!--====================================== Link ===================================================-->
<link href="css/main.css" rel="stylesheet">
<!--===============================================================================================-->
<script type="text/javascript">
	$(window).on('load', function() {
		$('#barModal').modal('show');
	});
</script>
<!--===============================================================================================-->
</head>
<body>
	<!--=============== header ===============-->
	<%@ include file="/jsp/administrator/header.jsp"%>
	<br><br><br><br>
	<!--=============== loop ===============-->
	<!--=============== bar ===============-->
	<c:forEach items="${bar_list}" var="bar" varStatus="loop">
		<div class="well">
			<p>
				<a data-toggle="collapse" data-target="#${loop.index+1}"
					class="btn btn-lg btn-primary" href="#" role="button">${bar.name}
					&raquo;</a>
			<div id="${loop.index+1}" class="collapse">
				<p>
				<p>
					<fmt:message key="label.bar" />
					<fmt:message key="label.id" />
					:
					<c:out value="${bar.barId}" />
				<p>
					<fmt:message key="label.seats" />
					:
					<c:out value="${bar.seats}" />
				<p>
					<fmt:message key="label.description" />
					:
					<c:out value="${bar.description}" />
				<p>
					<fmt:message key="label.status" />
					:
					<c:out value="${bar.status}" />
				<p>
					<fmt:message key="label.name" />
					:
					<c:out value="${bar.name}" />
				<p>
			</div>
		</div>
		<p>
	</c:forEach>
	<!--=============== footer ===============-->
	<br><br><br><br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>