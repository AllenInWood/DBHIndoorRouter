<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DBH indoor routing</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/search.css" />
</head>
<body>

<svg class="hidden">
	<defs>
		<symbol id="icon-stack" viewBox="0 0 32 32">
			<title>stack</title>
			<path class="path1" d="M29.143 11.071l-13.143-6.571-13.143 6.571 13.143 6.571 13.143-6.571zM16 6.681l8.781 4.39-8.781 4.39-8.781-4.39 8.781-4.39zM26.51 14.684l2.633 1.316-13.143 6.571-13.143-6.571 2.633-1.316 10.51 5.255zM26.51 19.612l2.633 1.316-13.143 6.571-13.143-6.571 2.633-1.316 10.51 5.255z"></path>
		</symbol>
		<symbol id="icon-elevator" viewBox="0 0 32 32">
			<title>elevator</title>
			<path class="path1" d="M6.179 9.958c-0.345-0.345-0.518-0.765-0.518-1.258s0.173-0.913 0.518-1.258c0.345-0.345 0.765-0.518 1.258-0.518s0.913 0.173 1.258 0.518c0.345 0.345 0.518 0.765 0.518 1.258s-0.173 0.913-0.518 1.258-0.765 0.518-1.258 0.518c-0.493 0-0.913-0.173-1.258-0.518zM3.904 18.449c0.111 0.086 0.24 0.13 0.388 0.13s0.277-0.043 0.388-0.13 0.167-0.228 0.167-0.425v-4.81c0-0.074 0.037-0.123 0.111-0.148s0.136-0.037 0.185-0.037 0.111 0.012 0.185 0.037c0.074 0.025 0.111 0.074 0.111 0.148v11.063c0 0.222 0.080 0.407 0.24 0.555s0.351 0.222 0.574 0.222c0.222 0 0.413-0.074 0.574-0.222s0.24-0.333 0.24-0.555v-6.771c0-0.123 0.049-0.203 0.148-0.241s0.173-0.055 0.222-0.055 0.117 0.019 0.204 0.055 0.13 0.117 0.13 0.241v6.771c0 0.222 0.080 0.407 0.24 0.555s0.351 0.222 0.573 0.222c0.247 0 0.444-0.074 0.592-0.222s0.222-0.333 0.222-0.555l0.037-11.063c0-0.074 0.037-0.123 0.111-0.148s0.136-0.037 0.185-0.037 0.111 0.012 0.185 0.037 0.111 0.074 0.111 0.148v4.81c0 0.197 0.056 0.339 0.166 0.425s0.234 0.13 0.37 0.13c0.136 0 0.259-0.043 0.37-0.13s0.166-0.228 0.166-0.425v-5.106c0-0.493-0.166-0.937-0.499-1.332s-0.783-0.592-1.351-0.592h-3.885c-0.567 0-0.981 0.197-1.239 0.592s-0.389 0.839-0.389 1.332v5.106c0 0.197 0.055 0.339 0.167 0.425zM23.088 4.056c0.222 0.21 0.333 0.475 0.333 0.795v22.052c0 0.321-0.111 0.586-0.333 0.796s-0.493 0.315-0.814 0.315h-21.127c-0.321 0-0.592-0.105-0.814-0.315s-0.333-0.475-0.333-0.796v-22.052c0-0.321 0.111-0.586 0.333-0.795s0.493-0.314 0.814-0.314h21.127c0.321 0 0.592 0.105 0.814 0.314zM21.571 5.592h-19.721v20.572h19.721v-20.572zM15.947 10.476c0.493 0 0.913-0.173 1.258-0.518s0.518-0.765 0.518-1.258-0.173-0.913-0.518-1.258c-0.345-0.345-0.765-0.518-1.258-0.518s-0.913 0.173-1.258 0.518c-0.345 0.345-0.518 0.765-0.518 1.258s0.173 0.913 0.518 1.258 0.765 0.518 1.258 0.518zM12.414 18.449c0.111 0.086 0.241 0.13 0.389 0.13s0.277-0.043 0.389-0.13 0.166-0.228 0.166-0.425v-4.81c0-0.074 0.037-0.123 0.111-0.148s0.136-0.037 0.185-0.037 0.111 0.012 0.185 0.037 0.111 0.074 0.111 0.148v11.063c0 0.222 0.080 0.407 0.241 0.555s0.351 0.222 0.573 0.222 0.413-0.074 0.573-0.222c0.16-0.148 0.241-0.333 0.241-0.555v-6.771c0-0.123 0.049-0.203 0.148-0.241s0.173-0.055 0.222-0.055 0.123 0.019 0.222 0.055c0.099 0.037 0.148 0.117 0.148 0.241v6.771c0 0.222 0.074 0.407 0.222 0.555s0.345 0.222 0.592 0.222c0.222 0 0.413-0.074 0.573-0.222s0.241-0.333 0.241-0.555v-11.063c0-0.074 0.037-0.123 0.111-0.148s0.136-0.037 0.185-0.037 0.111 0.012 0.185 0.037c0.074 0.025 0.111 0.074 0.111 0.148v4.81c0 0.197 0.055 0.339 0.166 0.425s0.241 0.13 0.389 0.13 0.277-0.043 0.389-0.13 0.166-0.228 0.166-0.425v-5.106c0-0.493-0.173-0.937-0.518-1.332s-0.802-0.592-1.369-0.592h-3.848c-0.567 0-0.987 0.197-1.258 0.592s-0.407 0.839-0.407 1.332v5.106c0 0.197 0.056 0.339 0.166 0.425zM31.669 23.692c-0.64 0-1.658 0-1.658 0s0-0.276 0-0.7c0-1.308 0-3.855 0-4.83 0 0 0.008-0.228-0.288-0.228s-2.111 0-2.524 0c-0.415 0-0.342 0.273-0.342 0.273 0 1.004 0 3.465 0 4.821 0 0.473 0 0.781 0 0.781s-1.27 0-1.795 0c-0.523 0-0.041 0.456-0.041 0.456l3.185 3.849c0 0 0.261 0.306 0.535 0.031 0.377-0.378 3.085-3.974 3.085-3.974s0.481-0.477-0.157-0.477zM25.149 8.654c0.64 0 1.658 0 1.658 0s0 0.276 0 0.7c0 1.308 0 3.855 0 4.83 0 0-0.008 0.228 0.288 0.228s2.111 0 2.524 0c0.415 0 0.342-0.273 0.342-0.273 0-1.004 0-3.465 0-4.821 0-0.473 0-0.781 0-0.781s1.27 0 1.795 0c0.523 0 0.041-0.456 0.041-0.456l-3.185-3.849c0 0-0.261-0.306-0.535-0.031-0.377 0.378-3.085 3.974-3.085 3.974s-0.481 0.477 0.157 0.477z"></path>
		</symbol>
		<symbol id="icon-angle-up" viewBox="0 0 21 32">
			<title>angle-up</title>
			<path class="path1" d="M19.196 21.143q0 0.232-0.179 0.411l-0.893 0.893q-0.179 0.179-0.411 0.179t-0.411-0.179l-7.018-7.018-7.018 7.018q-0.179 0.179-0.411 0.179t-0.411-0.179l-0.893-0.893q-0.179-0.179-0.179-0.411t0.179-0.411l8.321-8.321q0.179-0.179 0.411-0.179t0.411 0.179l8.321 8.321q0.179 0.179 0.179 0.411z"></path>
		</symbol>
		<symbol id="icon-angle-down" viewBox="0 0 21 32">
			<title>angle-down</title>
			<path class="path1" d="M19.196 13.143q0 0.232-0.179 0.411l-8.321 8.321q-0.179 0.179-0.411 0.179t-0.411-0.179l-8.321-8.321q-0.179-0.179-0.179-0.411t0.179-0.411l0.893-0.893q0.179-0.179 0.411-0.179t0.411 0.179l7.018 7.018 7.018-7.018q0.179-0.179 0.411-0.179t0.411 0.179l0.893 0.893q0.179 0.179 0.179 0.411z"></path>
		</symbol>
	</defs>
</svg>

<div class="container">
	<div class="row">
		<div class="main">
			<nav class="navbar navbar-light bg-light">
				<a class="navbar-brand">DBH Indoor routing</a>
				<form class="form-inline">
					<input class="form-control mr-sm-2" id="autocomplete" type="text" placeholder="Search" aria-label="Search" style="width: 500px;">
					<button class="btn btn-outline-success my-2 my-sm-0" id="fulltext" type="button">Search</button>
				</form>
			</nav>
		<div class="mall">
			<div class="surroundings">
				<img class="surroundings__map" src="img/surroundings.svg" alt="Surroundings"/>
			</div>
			<div class="levels">
				<div class="level level--1" aria-label="Level 1">
					<canvas  style="background-image: url(img/newMapDemo.png);" id="myCanvas-1" width=1050 height=950></canvas>
				</div>
				<div class="level level--2" aria-label="Level 2">
					<canvas  style="background-image: url(img/newMapDemo.png);" id="myCanvas-2" width=1050 height=950></canvas>
				</div>
				<div class="level level--3" aria-label="Level 3">
					<canvas  style="background-image: url(img/newMapDemo.png);" id="myCanvas-3" width=1050 height=950></canvas>
				</div>
				<div class="level level--4" aria-label="Level 4">
					<canvas  style="background-image: url(img/newMapDemo.png);" id="myCanvas-4" width=1050 height=950></canvas>
				</div>
			</div>
			<!-- /levels -->
		</div>
		<!-- /mall -->
		<nav class="mallnav mallnav--hidden" style="top: 60px;">
			<button class="boxbutton mallnav__button--up" aria-label="Go up"><svg class="icon icon--angle-down"><use xlink:href="#icon-angle-up"></use></svg></button>
			<button class="boxbutton boxbutton--dark mallnav__button--all-levels" aria-label="Back to all levels"><svg class="icon icon--stack"><use xlink:href="#icon-stack"></use></svg></button>
			<button class="boxbutton mallnav__button--down" aria-label="Go down"><svg class="icon icon--angle-down"><use xlink:href="#icon-angle-down"></use></svg></button>
		</nav>
	</div>
	</div>
		<!-- /main -->

</div>

<%--demo--%>
<%--<div class="container">--%>
	<%--<div class="row">--%>
		<%--<canvas  style="background-image: url(img/newMapDemo.png);" id="myCanvas-2" width=1050 height=950></canvas>--%>
	<%--</div>--%>
<%--</div>--%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.6/jquery.autocomplete.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="js/modernizr-custom.js"></script>
<script src="js/index.js"></script>
<script src="js/classie.js"></script>
<script src="js/main.js"></script>





</body>
</html>