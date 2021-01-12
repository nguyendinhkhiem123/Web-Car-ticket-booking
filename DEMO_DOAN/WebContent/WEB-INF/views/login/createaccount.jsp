<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath }/">
<title>Tạo tài khoản</title>

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
	<h1>${message}</h1>
	<div class="main">
		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign Up</h2>
						<form:form method="POST" class="register-form" id="register-form" modelAttribute="login" action="createaccount.htm"  enctype="multipart/form-data">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <form:input
									type="text" path="userName" id="name" placeholder="User Name" />
									<form:errors path="userName"></form:errors>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <form:input
									type="password" path="passWord" id="pass" placeholder="Password" />
										<form:errors path="passWord"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <form:input
									type="text" path="hovaten" id="name" placeholder="Name" />
									<form:errors path="hovaten"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <form:input
									type="text" path="sdt" id="name" placeholder="Phone number" />
										<form:errors path="sdt"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <form:input
									type="text" path="address" id="name" placeholder="Address" />
									<form:errors path="address"></form:errors>
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label>
								<form:input type="email" path="email" id="email"
									placeholder="Your Email" />
									<form:errors path="email"></form:errors>
							</div>
						
							<div class="form-group">
								<div>Choose image</div>
								<input type="file" name="file"/>
								<form:errors path="image"></form:errors>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Submit" />
							</div>
							
							
						</form:form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="template/login/images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="login.htm" class="signup-image-link">Back to login</a>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
	<script src="template/login/vendor/jquery/jquery.min.js"></script>
    <script src="template/login/js/main.js"></script>
    
    
</html>