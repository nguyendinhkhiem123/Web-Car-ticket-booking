<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<base href="${pageContext.servletContext.contextPath }/">
<title>Home</title>

<!-- Bootstrap core CSS -->
<link href="template/user/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="template/user/css/small-business.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500"
	rel="stylesheet" />


</head>
<style type="text/css">
	h2{
		text-align: center;
	}
	#styImg {
	height: 40px;
	width: 50px;
	border-radius: 50px;
}

#liTen {
	font-size: 25px;
	color: white;
}
	
</style>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home/${id}.htm">Vexemac</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="home/${id}.htm">TRANG CHỦ </a></li>
					<li class="nav-item "><a class="nav-link" href="home/${id}/book.htm">CHI TIẾT</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="home/${id}/chitiet.htm">VÉ XE ĐÃ ĐĂNG KÝ <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="login.htm">ĐĂNG XUẤT</a></li>
					<li class="nav-item" id ="liTen">${hovaten} <img src="image/${image}" id ="styImg"></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	
	<div class="container" >
		<h2>${message}</h2>
	</div>

	<!-- Bootstrap core JavaScript -->
	<script src="template/user/vendor/jquery/jquery.min.js"></script>
	<script src="template/user/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>