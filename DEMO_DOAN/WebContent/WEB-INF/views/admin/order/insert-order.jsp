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

*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<body id="page-top">
	<jsp:include page="./../../commons/admin/menu.jsp"></jsp:include>

	<div class="main" class="container-fluid">
		<!-- Sign up form -->
		<h1>${message}</h1>
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">THÊM TUYẾN XE</h2>
						<form:form method="POST" class="register-form" id="register-form"
							modelAttribute="tuyen" action="admin/${id}/list-order/insert.htm">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="tenXe" id="name"
									placeholder="Tên xe" />
								<form:errors path="tenXe"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="diemDi" id="name"
									placeholder="Điểm đi" />
								<form:errors path="diemDi"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="text" path="diemDen" id="name"
									placeholder="Điểm đến" />
								<form:errors path="diemDen"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="date" path="thoiGian" 
									placeholder="Thời gian" />
								<form:errors path="thoiGian"></form:errors>
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="number" path="giaVe" min="1000" step="1000" value="1000"
									placeholder="Gía vé" />
							<form:errors path="giaVe"></form:errors>
							</div>
								<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label>
								<form:input type="number" path="soVe" min="1" step="1" value="1" 
									placeholder="Số vé" />
								<form:errors path="soVe"></form:errors>
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
						<a href="admin/${id}/list-order.htm" class="signup-image-link">BACK TO ORDER PAGE
							</a>
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