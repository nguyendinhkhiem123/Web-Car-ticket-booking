<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
p {
	text-align: center;
}

table {
	marign-top: 10px;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid black;
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
					<li class="nav-item"><a class="nav-link" href="home/${id}.htm">TRANG
							CHỦ </a></li>
					<li class="nav-item active"><a class="nav-link"
						href="home/${id}/book.htm">CHI TIẾT <span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link"
						href="home/${id}/chitiet.htm">VÉ XE ĐÃ ĐĂNG KÝ</a></li>
					<li class="nav-item"><a class="nav-link" href="login.htm">ĐĂNG
							XUẤT</a></li>
					<li class="nav-item" id="liTen">${hovaten}<img
						src="image/${image}" id="styImg"></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<p>${message}</p>
	<div class="container">
		<table>
			<tr>

				<th>MÃ TUYẾN</th>
				<th>TÊN XE</th>
				<th>ĐIỂM ĐI</th>
				<th>ĐIỂM ĐẾN</th>
				<th>THỜI GIAN</th>
				<th>GIÁ VÉ</th>
				<th>SỐ LƯỢNG VÉ CÒN LẠI</th>
				<th>SỐ LƯỢNG VÉ</th>
				<th>TRANG THÁI</th>
			</tr>
			<c:forEach var="l" items="${ds}">
				<tr>

					<td>${l.maTuyen}</td>
					<td>${l.tenXe}</td>
					<td>${l.diemDi}</td>
					<td>${l.diemDen}</td>
					<td>${l.thoiGian}</td>
					<td><fmt:formatNumber value="${l.giaVe}" pattern="#,##0"/></td>
					<c:set var="sum" value="${0}" />
					<c:if test="${not empty l.ve}">
			
						<c:forEach var="ds1" items="${l.ve}">
							<c:set var="sum" value="${sum + ds1.soLuongVe}" />
						</c:forEach>
					</c:if>	
					<td>${l.soVe-sum}</td>
					<td>
						<input id="number" type="number" min="1" max="${l.soVe-sum}" step="1" value="1" onchange="onChange(${id}, ${l.maTuyen},${l.soVe-sum})">
					</td>
					<td>
						<jsp:useBean id="now" class="java.util.Date"/>
						<c:choose>
							<c:when test="${l.thoiGian lt now }">Không thể đặt</c:when>
							<c:when test="${l.soVe == sum}">HẾT VÉ</c:when>
							<c:otherwise><a id="link" href="home/${id}/book/${l.maTuyen}/1.htm?lnkInsert">Đặt vé</a></c:otherwise>
						</c:choose>
					
					</td>
				</tr>
			</c:forEach>
		</table>
		${message1}
	</div>

	<!-- Bootstrap core JavaScript -->
	<script src="template/user/vendor/jquery/jquery.min.js"></script>
	<script src="template/user/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
	<script type="text/javascript">	
	function onChange(id,maTuyen){
		 var number = document.getElementById("number").value;
		 document.getElementById("link").href = "home/"+id+"/book/"+maTuyen+"/"+number+".htm?lnkInsert";
		 
	
	}
</script>
</html>