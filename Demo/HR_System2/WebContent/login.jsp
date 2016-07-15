<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<script type="text/javascript" src="js/jquery-1.12.1.js"></script>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			if ($('#flag').val() == 0) {
				alert('输入的用户名或密码错误');
			}
		})
	</script>

	<textarea id="flag" style="display:none">
<%=request.getAttribute("flag")%>
</textarea>
	<form class="box login" action="LoginServlet" method="post">
		<fieldset class="boxBody">
			<label>用户名</label> <input type="text" tabindex="1"
				placeholder="login-name" name="name" required> <label>密码</label>
			<input type="password" name="psd" tabindex="2"
				placeholder="login-password" required>
		</fieldset>
		<footer> <input type="submit" class="btnLogin" value="Login"
			tabindex="4"> </footer>
	</form>
	<footer id="main"> welcome to use </footer>
</body>
</html>