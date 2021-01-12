<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath }/">
<title>Đăng Nhập</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="template/login/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="template/login/css/style.css">
<style>
		*[id$=errors]{
			color: red;
			font-style: italic;
		}
	</style>
</head>
<body> 
	<c:if test="${not empty message}">
	    <h1>${message }</h1>
	</c:if>
	<div class="main">
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="template/login/images/signin-image.jpg"
								alt="sing up image">
						</figure>
						<a href="createaccount.htm" class="signup-image-link">Create an account</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Sign up</h2>
						<form:form method="Post" class="register-form" id="login-form" action="login.htm" modelAttribute="loginbean">
							<div class="form-group">
								<label for="your_name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <form:input
									type="text" path="Username" id="your_name"
									placeholder="Your Username" />
								<form:errors path="Username"></form:errors>
							</div>
							<div class="form-group">
								<label for="your_pass"><i class="zmdi zmdi-lock"></i></label> <form:input
									type="password" path="Password" id="your_pass"
									placeholder="Password" />
								<form:errors path="Password"></form:errors>
							</div>
							
							<div class="form-group form-button">
								<button id="signin" 
									class="form-submit" >Sign in</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
	<script src="template/login/vendor/jquery/jquery.min.js"></script>
	<script src="template/login/js/main.js"></script>
	<script type="text/javascript">
	
	</script>
</html>