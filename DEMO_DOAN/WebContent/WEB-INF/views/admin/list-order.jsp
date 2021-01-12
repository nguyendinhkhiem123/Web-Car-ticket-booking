<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

</head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid black;
}
.btnThem{
	margin-left : 30px;
	margin-top:10px;
}
</style>
<body id="page-top">
	<jsp:include page="./../commons/admin/menu.jsp"></jsp:include>
	<div class="container-fluid">
		<table>
			<tr>
				<th>MÃ TUYẾN</th>
				<th>TÊN XE</th>
				<th>ĐIỂM ĐI</th>
				<th>ĐIỂM ĐẾN</th>
				<th>THỜI GIAN</th>
				<th>SỐ LƯỢNG</th>
				<th>GIÁ VÉ</th>
				<th>EDIT</th>
			</tr>
			<c:forEach var="l" items="${ds}" >
				<tr>
				
					<td>${l.maTuyen}</td>
					<td>${l.tenXe}</td>
					<td>${l.diemDi}</td>
					<td>${l.diemDen}</td>
					<td>${l.thoiGian}</td>
					<td>${l.soVe}</td>
					<td>
						<fmt:formatNumber value="${l.giaVe}" pattern="#,##0"/>
					</td>
					<td>
						<a href="admin/${id}/list-order/update/${l.maTuyen}.htm?lnkUpdate">
							Sửa
						</a>
						<a href="admin/${id}/list-order/delete/${l.maTuyen}.htm?lnkDelete">
							Xóa
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<button class="btnThem"><a href="admin/${id}/list-order/insert.htm" id="signup">THÊM TUYẾN ĐI</a></button>
	</div>
  		<jsp:include page="./../commons/admin/footer.jsp"></jsp:include>
	</div>
</div>
   	<jsp:include page="./../commons/admin/ScrollToTop.jsp"></jsp:include>
  

   

    <!-- Bootstrap core JavaScript-->
    <script src="template/admin/vendor/jquery/jquery.min.js"></script>
    <script
		src="template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script
		src="template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="template/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="template/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="template/admin/js/demo/chart-area-demo.js"></script>
    <script src="template/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>