<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<%@ page isErrorPage="true"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />
<!DOCTYPE html>
<html>
<head>
<title>Block</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--================================= Modal script ================================================-->
<script>
	$(document).ready(function() {
		$("#myModalBox").modal('show');
	});
</script>
<!--================================= Style ======================================================-->
<style type="text/css">
body {
	background-image: url("images/block.jpg");
	background-size: 100%;
	background-color: #cccccc;
}

#myModalBox {
	text-align: center;
}

.modal {
	text-align: center;
}

@media screen and (min-width: 768px) {
	.modal:before {
		display: inline-block;
		vertical-align: middle;
		content: " ";
		height: 100%;
	}
}

.modal-dialog {
	display: inline-block;
	text-align: center;
	vertical-align: middle;
}

.modal-body {
	background-color: #FFFFFF;
}

.modal-content {
	border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
}

.modal-footer {
	background-color: #FFFFFF;
}

.modal-header {
	background-color: #ff0000;
	color: white;
}

.btn btn-primary center-block {
	color: #0066ff;
}
</style>
</head>
<body>
	<!--========== Modal window ==========-->
	<div id="myModalBox" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
			<audio src="media/error.mp3" autoplay></audio>
				<!------- modal header 1 ------->
				<div class="modal-header">
					<h4 class="modal-title">
						<fmt:message key="label.welcome" />
					</h4>
				</div>
				<!------- modal header 2 ------->
				<div class="modal-header">
					<h4 class="modal-title">
						<fmt:message key="label.user_block" />
					</h4>
				</div>
				<!------- modal body ------->
				<fmt:message key="label.details" />
				<!------- footer ------->
				<div class="modal-footer">
				<form name="blockForm" method="POST" action="controller">
					<button name="command" value="logout" type="submit"
						class="btn btn-danger btn-block center-block">
						<fmt:message key="label.back" />
					</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<button type="button" onclick='history.back()' value="BACK"
		class="btn btn-danger btn-block center-block">
		<fmt:message key="label.back" />
	</button>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>