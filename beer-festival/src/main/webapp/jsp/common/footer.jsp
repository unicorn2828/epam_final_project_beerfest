<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="properties.pagecontent" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
<script type="text/javascript">
	function startTime() {
		var tm = new Date();
		var h = tm.getHours();
		var m = tm.getMinutes();
		var s = tm.getSeconds();
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 500);
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>
<style type="text/css">
#footer {
	position: fixed;
	center: 0;
	bottom: 0;
	padding: 10px;
	background: linear-gradient(0.25turn, black, #07BFD5, black);
	color: #fff;
	width: 100%;
}

#time {
	position: fixed;
	bottom: 20px;
	left: 1%;
	line-height: 1px;
	color: #fff;
}
</style>
</head>
<body onload="startTime()">
	<div id="footer" style="text-align: center">
		&copy;
		<fmt:message key="label.footer_name" />
	</div>
	<div id="time" style="text-align: left"></div>
</body>
</html>