<%@ page
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath }/">
</head>
<style>
#styImg{
		height: 40px;
		width: 50px;
		border-radius: 50px;
	}
#liTen{
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
					<li class="nav-item active"><a class="nav-link" href="home/${id}.htm">TRANG CHỦ
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="home/${id}/book.htm">CHI TIẾT</a></li>
					<li class="nav-item"><a class="nav-link" href="home/${id}/chitiet.htm">VÉ XE ĐÃ ĐĂNG KÝ</a></li>
					<li class="nav-item"><a class="nav-link" href="login.htm"> ĐĂNG XUẤT </a></li>
					<li class="nav-item" id ="liTen">${hovaten} <img src="image/${image}" id ="styImg"></li>
				</ul>
			</div>
		</div>
	</nav>
	
</body>
</html>