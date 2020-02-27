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
<html>
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--======================================== Script================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--==============================================================================================-->
<script>
	$(document).ready(function() {
		$("#myModalBox").modal('show');
	});
</script>
<script type="text/javascript">
	$(window).on('load', function() {
		$('#uploadFileModal').modal('show');
	});
</script>
<!--======================================== Link ==============================================-->
<link href="css/main.css" rel="stylesheet">
<!--============================================================================================-->
</head>
<!--======================================== Body ==============================================-->
<body>
	<!--=============== header ===============-->
	<jsp:include page="/jsp/${fn:toLowerCase(user.role)}/header.jsp" />
	<br>
	<br>
	<br>
	<br>
	<!--=============== page container =======-->
	<audio src="media/3.mp3" autoplay></audio>
	<div class="jumbotron">
		<h1>
			<fmt:message key="label.welcome" />
		</h1>
		<p>
			<fmt:message key="label.main_info1" />
		</p>
		<p>
			<fmt:message key="label.main_info2" />
		</p>
		<a data-toggle="collapse" data-target="#demo"
			class="btn btn-lg btn-primary" href="#" role="button"><fmt:message
				key="label.info" /> &raquo;</a>
		<div id="demo" class="collapse">
			<p>
				<fmt:message key="label.main_info3" />
		</div>
		<p>
			<!--=============== accordion ===============-->
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse1"> <fmt:message key="label.concerts" />
						</a>
					</h4>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
					<div class="panel-body">
						<fmt:message key="label.concerts_info" />
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse2"> <fmt:message key="label.bars" />
						</a>
					</h4>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
					<div class="panel-body">
						<fmt:message key="label.bars_info" />
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapse3"> <fmt:message key="label.partners" />
						</a>
					</h4>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
					<div class="panel-body">
						<fmt:message key="label.partners_info" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--=============== upload file modal ===============-->
	<c:if test="${message != null}">
		<div class="modal fade" id="uploadFileModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h2 class="modal-title" id="exampleModalLabel">
						<audio src="media/error.mp3" autoplay></audio>
							<fmt:message key="label.warning" />
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
	<!-------==================== FOOTER ====================------->
	<br>
	<br>
	<br>
	<br>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>