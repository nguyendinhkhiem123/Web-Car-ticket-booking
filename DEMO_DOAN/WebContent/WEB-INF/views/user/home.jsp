<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
   <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet" />
    <link href="template/search/css/main.css" rel="stylesheet" />
</head>

<body>

	<!-- Navigation -->
	<jsp:include page="./../commons/user/header.jsp"></jsp:include>
	
	<!-- Page Content -->
	<div class="s002">
		<form action="home/${id}/book.htm" method="post" >
			<fieldset>
				<legend>TÌM KIẾM VÉ XE</legend>
			</fieldset>
			<div class="inner-form">
				<div class="input-field first-wrap">
					<div class="icon-wrap">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
	                		<path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
	                	</svg>
					</div>
					<input id="search" name="Diemdi"type="text" placeholder="ĐIỂM ĐI" />
				</div>
				<div class="input-field first-wrap">
					<div class="icon-wrap">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
							 <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
						 </svg>
					</div>
					<input id="search" name="Diemden" type="text" placeholder="ĐIỂM ĐẾN" />
				</div>
				 <div class="input-field fouth-wrap">
					<div class="icon-wrap">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
             			   <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
              			</svg>
					</div>
					<input class="datepicker" name="Thoigian" id="return" type="text"  placeholder="30 Aug 2018" />
				</div>
				<div class="input-field fifth-wrap">
					<button class="btn-search" >TÌM</button>
				</div>
			</div>
		</form>
	</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<jsp:include page="./../commons/user/footer.jsp"></jsp:include>
	<!-- Bootstrap core JavaScript -->
	<script src="template/user/vendor/jquery/jquery.min.js"></script>
	<script src="template/user/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	  <script src="template/search/js/extention/choices.js"></script>
    <script src="template/search/js/extention/flatpickr.js"></script>
    <script>
      flatpickr(".datepicker",
      {});

    </script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>
</body>

</html>
