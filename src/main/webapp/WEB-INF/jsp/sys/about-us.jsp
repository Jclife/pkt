<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<link rel="icon" href="<%=basePath%>/resources/images/logo.png" type="image/x-icon"/>
<title>关于我们</title>

<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/font-awesome.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/ionicons.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.carousel.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.theme.css" type="text/css" media="all">
<link rel='stylesheet' href='<%=basePath%>/resources/css/prettyPhoto.css' type='text/css' media='all'>
<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/custom.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/mycss/pkt-index.css" type="text/css" media="all">
<link href="http://fonts.googleapis.com/css?family=Great+Vibes%7CLato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="http://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
<div class="site">
	<div class="topbar">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="topbar-text">
						<span> 工作日： 早上9:00 至 晚上 6:00</span>
						<span> 周 末: 早上10:00 至 晚上 7:00</span>
					</div>
				</div>
				<div class="col-md-6">
					<div class="topbar-menu">
						<ul class="topbar-menu">
							<c:choose>
								<c:when test="${userName==null}">
									<li>请先<a href="<%=basePath%>/pkt/login">登录</a></li>
								</c:when>
								<c:otherwise>
									<li class="dropdown">
										<p id="sessionUserName" style="display: inline">${userName}</p>,欢迎
										<ul class="sub-menu">
											<li><a href="<%=basePath%>/pkt/personal">个人中心</a></li>
											<li><a href="<%=basePath%>/pkt/cart">购物车</a></li>
											<li><a href="<%=basePath%>/pkt/checkOut">结账</a></li>
											<li><a href="<%=basePath%>/pkt/about">关于我们</a></li>
											<li><a style="cursor:pointer;" onclick="quitLogin()">退出登录</a></li>
										</ul>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<header id="header" class="header header-desktop header-2">
		<div class="top-search">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<form onkeydown="if(event.keyCode==13) return searchGoods()">
							<input type="search" id="searchValue" onchange="searchGoods()" name="goods" class="top-search-input" placeholder="想吃点什么?请输入..." />
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<a href="<%=basePath%>/pkt/index" id="logo">
						<img class="logo-image" src="<%=basePath%>/resources/images/logo.png" alt="PKT" />
					</a>
				</div>
				<div class="col-md-9">
					<div class="header-right">
						<nav class="menu">
							<ul class="main-menu">
								<li><a href="<%=basePath%>/pkt/index">主页</a></li>
								<li><a href="<%=basePath%>/pkt/shop">所有商品</a></li>
							</ul>
						</nav>
						<div class="btn-wrap">
							<div class="mini-cart-wrap">
								<div class="mini-cart">
									<div class="mini-cart-icon" data-count="${cartListsNum}" id="allListNumber">
										<i class="ion-bag"></i>
									</div>
								</div>
								<div class="widget-shopping-cart-content">
									<ul class="cart-list" id="cartLists">
										<c:forEach items="${cartLists}" var="mymap" >
											<li class="${mymap.value.id}">
												<a onclick="deleteCart(this)" class="remove" tValue="${mymap.value.id}">×</a>
												<a  onclick="toShop(this)" class="${mymap.value.id}" style="cursor:pointer">
													<img src="http://123.207.250.128:8888/${mymap.value.imgs}" style="width: 80px;height: 80px;"/>
														${mymap.value.name}&nbsp;
												</a>
												<span class="quantity"><p style="display: inline-block;" class="number">${mymap.value.num}</p> × ￥<p style="display: inline-block;" class="price">${mymap.value.price}</p></span>
											</li>
										</c:forEach>
									</ul>

									<p class="total">
										<strong>总计:</strong>
										<span class="amount" id="addAllPrice">${addPrice}</span>
									</p>
									<p class="buttons">
										<c:choose>
											<c:when test="${userName==null}">
												<a href="<%=basePath%>/pkt/login" class="view-cart">请先登录</a>
											</c:when>
											<c:otherwise>
												<a href="<%=basePath%>/pkt/cart" class="view-cart">购物车</a>
												<a href="<%=basePath%>/pkt/checkOut" class="checkout">支付账单</a>
											</c:otherwise>
										</c:choose>
									</p>
								</div>
							</div>
							<div class="top-search-btn-wrap">
								<div class="top-search-btn">
									<a href="javascript:void(0);">
										<i class="ion-search"></i>
									</a>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</header>
	<div id="main">
		<div class="section border-bottom pt-2 pb-2">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<ul class="breadcrumbs">
							<li><a href="<%=basePath%>/pkt/index">主页</a></li>
							<li>关于我们</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="section pt-10 pb-10">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="text-center mb-1 section-pretitle">欢迎来到PKT私厨平台</div>
						<h2 class="text-center section-title mtn-2">这是一个专业的健康外卖平台</h2>
						<div class="organik-seperator mb-9 center">
							<span class="sep-holder"><span class="sep-line"></span></span>
							<div class="sep-icon"><i class="organik-flower"></i></div>
							<span class="sep-holder"><span class="sep-line"></span></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="about-main-img col-lg-6">
						<img src="<%=basePath%>/resources/images/about_1.jpg" alt="" />
					</div>
					<div class="about-content col-lg-6">
						<div class="about-content-title">
							<h4>食物完全无污染</h4>
							<div class="about-content-title-line"></div>
						</div>
						<div class="about-content-text">
							<p>这里是关于平台的简介，可以写很多东西,balabalbala......</p>
							<p>lalalallalalalalala继续写写.......<br></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section bg-light pt-16 pb-6">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="text-center mb-1 section-pretitle">为什么选择我们的平台?</div>
						<div class="organik-seperator mb-9 center">
							<span class="sep-holder"><span class="sep-line"></span></span>
							<div class="sep-icon"><i class="organik-flower"></i></div>
							<span class="sep-holder"><span class="sep-line"></span></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-12">
						<div class="icon-boxes">
							<div class="icon-boxes-icon"><i class="ion-android-star-outline"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 可以吃的更健康.</h6>
								<p>所有食物无污染无添加.</p>
							</div>
						</div>
						<div class="icon-boxes">
							<div class="icon-boxes-icon"><i class="organik-lemon"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 我们更专业</h6>
								<p>有更专业的人负责</p>
							</div>
						</div>
						<div class="icon-boxes">
							<div class="icon-boxes-icon"><i class="organik-cucumber"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 新鲜和配送免费.</h6>
								<p>我们保证食材新鲜和24小时的免费配送服务.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-12">
						<div class="text-center">
							<img src="<%=basePath%>/resources/images/about_pic.png" alt="" />
						</div>
					</div>
					<div class="col-md-4 col-sm-12">
						<div class="icon-boxes right">
							<div class="icon-boxes-icon"><i class="organik-broccoli"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 无条件退款</h6>
								<p>承诺不满意直接退款.</p>
							</div>
						</div>
						<div class="icon-boxes right">
							<div class="icon-boxes-icon"><i class="organik-carrot"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 私人订制.</h6>
								<p>可以选择高端私人订制.</p>
							</div>
						</div>
						<div class="icon-boxes right">
							<div class="icon-boxes-icon"><i class="organik-tomato"></i></div>
							<div class="icon-boxes-inner">
								<h6 class="icon-boxes-title"> 美味.</h6>
								<p>来自自然的味道.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<img src="<%=basePath%>/resources/images/footer_logo.png" class="footer-logo" alt="" />
					<p>
						欢迎来到社区私厨平台，我们提供更优质的美味
					</p>
					<div class="footer-social">
						<a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="fa fa-twitter"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="top" title="Pinterest"><i class="fa fa-pinterest"></i></a>
						<a href="#" data-toggle="tooltip" data-placement="top" title="Instagram"><i class="fa fa-instagram"></i></a>
					</div>
				</div>
				<div class="col-md-2">
					<div class="widget">
						<h3 class="widget-title">信息</h3>
						<ul>
							<li><a href="#">新产品</a></li>
							<li><a href="#">热卖榜</a></li>
							<li><a href="#">社区</a></li>
							<li><a href="#">我的购物车</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-2">
					<div class="widget">
						<h3 class="widget-title">其余链接</h3>
						<ul>
							<li><a href="#">关于我们</a></li>
							<li><a href="#">我们的团队</a></li>
							<li><a href="#">关于我们</a></li>
							<li><a href="#">随便写的</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-3">
					<div class="widget">
						<h3 class="widget-title">联系</h3>
						<p>
							在这里输入你的邮箱绑定可以动态得到消息
						</p>
						<form class="newsletter">
							<input type="email" name="EMAIL" placeholder="邮箱地址" required="" />
							<button><i class="fa fa-paper-plane"></i></button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					Copyright &copy; 2018.Test name liujun.<a target="_blank" href="../resources/http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
				</div>
				<div class="col-md-4">
					<img src="<%=basePath%>/resources/images/footer_payment.png" alt="" />
				</div>
			</div>
		</div>
		<div class="backtotop" id="backtotop"></div>
	</div>
</div>

<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery-migrate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/modernizr-2.7.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.countdown.min.js"></script>
<script type='text/javascript' src='<%=basePath%>/resources/js/jquery.prettyPhoto.js'></script>
<script type='text/javascript' src='<%=basePath%>/resources/js/jquery.prettyPhoto.init.min.js'></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/common.js"></script>

</body>
</html>