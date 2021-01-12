<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<base href="${pageContext.servletContext.contextPath }/">
<title>ADMIN</title>

<!-- Custom fonts for this template-->
<link href="template/admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="template/admin/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="template/login/fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="template/login/css/style.css">

</head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid black;
}

.mt10 {
	margin-top: 10px;
}

*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<body id="page-top">
	<jsp:include page="./../../commons/admin/menu.jsp"></jsp:include>

	<div class="main" class="container-fluid">
		<h1>${message}</h1>
		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">UPDATE USER</h2>
						<form:form method="POST" class="register-form" id="register-form"
							modelAttribute="mem" action="admin/${id}/list-user/update.htm" enctype="multipart/form-data">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="iD" id="name" readonly="true"
									placeholder="ID" />
							
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="userName" id="name" readonly="true"
									placeholder="User Name" />
								<form:errors path="userName"></form:errors>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label>
								<form:input type="password" path="passWord" id="pass"
									placeholder="Password" readonly="true" />
								<form:errors path="passWord"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="hovaten" id="name"
									placeholder="Name" />
								<form:errors path="hovaten"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="sdt" id="name"
									placeholder="Phone number" />
								<form:errors path="sdt"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="address" id="name"
									placeholder="Address" />
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
							<img src="template/login/images/signup-image.jpg"
								alt="sing up image">
						</figure>
						<a href="admin/${id}/list-user.htm" class="signup-image-link">BACK
							TO USER PAGE</a>
					</div>
				</div>
			</div>
		</section>
	</div>


	<jsp:include page="./../../commons/admin/footer.jsp"></jsp:include>
	</div>
	</div>
	<jsp:include page="./../../commons/admin/ScrollToTop.jsp"></jsp:include>




	<!-- Bootstrap core JavaScript-->
	<script src="template/admin/vendor/jquery/jquery.min.js"></script>
	<script
		src="template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="template/admin/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="template/admin/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="template/admin/js/demo/chart-area-demo.js"></script>
	<script src="template/admin/js/demo/chart-pie-demo.js"></script>

	<script src="template/login/vendor/jquery/jquery.min.js"></script>
	<script src="template/login/js/main.js"></script>


</body>

</html>