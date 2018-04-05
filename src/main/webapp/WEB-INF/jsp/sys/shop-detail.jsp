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
<title>Shop Detail</title>

<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/font-awesome.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/ionicons.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.carousel.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.theme.css" type="text/css" media="all">
<link rel='stylesheet' href='<%=basePath%>/resources/css/prettyPhoto.css' type='text/css' media='all'>
<link rel='stylesheet' href='<%=basePath%>/resources/css/slick.css' type='text/css' media='all'>
<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/custom.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/mycss/shopDetail.css" type="text/css" media="all">
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
							<li><a href="<%=basePath%>/pkt/shop">购物</a></li>
							<li>商品详情</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="section pt-7 pb-7">
			<div class="container">
				<div class="row">
					<div class="col-md-9 col-md-push-3">
						<div class="single-product">
							<div class="col-md-6">
								<div class="image-gallery">
									<div class="image-gallery-inner">
										<c:forEach var="item" items="${img}" varStatus="index">
											<div>
												<div class="image-thumb">
													<a href="http://123.207.250.128:8888/${item}" data-rel="prettyPhoto[gallery]">
														<img src="http://123.207.250.128:8888/${item}" alt="" style="width: 380px;height: 320px;"/>
													</a>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<div class="image-gallery-nav">
									<c:forEach var="item" items="${img}" varStatus="index">
										<div class="image-nav-item">
											<div class="image-thumb">
												<img src="http://123.207.250.128:8888/${item}" alt="" style="width: 105px;height: 70px;"/>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="col-md-6">
								<div class="summary">
									<h1 class="product-title">${goods.goods_name}</h1>
									<div class="product-rating">
										<div class="star-rating">
											<span style="width:100%"></span>
										</div>
										<i>(${commentNum}条评论)</i>
									</div>
									<div class="product-price">
										<c:choose>
											<c:when test="${goods.goods_price!=goods.goods_price_now}">
												<del style="color: #212020;">¥ ${goods.goods_price}</del>
												<ins>¥ ${goods.goods_price_now}</ins>
											</c:when>
											<c:otherwise>
												<ins>¥ ${goods.goods_price_now}</ins>
											</c:otherwise>
										</c:choose>
									</div>
									<div class="mb-3">
										<p id="remain_num" remainNum="${goods.goods_sale_count}">已售${goods.goods_sale_count}</p>
									</div>
									<div class="mb-3">
										<p>${goods.goods_simp_desc}</p>
									</div>
									<form class="cart" style="margin-bottom: 0px">
										<div class="quantity-chooser">
											<div class="quantity">
												<span class="qty-minus" data-min="1"><i class="ion-ios-minus-outline"></i></span>
												<input type="text" name="quantity" value="1" title="Qty" id="setAddNum" class="input-text qty text" size="4">
												<span class="qty-plus" data-max=""><i class="ion-ios-plus-outline"></i></span>
											</div>
										</div>
									</form>
									<button type="button" class="${goods.goods_id}" onclick="shopCart(this)" style="padding: 12px 29px;border: 1px solid;border-color: #5fbd74;border-radius: 3px;background: #5fbd74;color: #fff;font-size: 15px;line-height: 1;font-weight: bold;letter-spacing: .05em;">添加到购物车</button>
									<div class="product-meta">
										<table>
											<tbody>
											<tr>
												<td class="label">类别</td>
												<c:choose>
													<c:when test="${goods.goods_classify==1}">
														<td><p>荤菜</p></td>
													</c:when>
													<c:when test="${goods.goods_classify==2}">
														<td><p>汤类</p></td>
													</c:when>
													<c:when test="${goods.goods_classify==3}">
														<td><p>小炒</p></td>
													</c:when>
													<c:when test="${goods.goods_classify==4}">
														<td><p>火锅</p></td>
													</c:when>
													<c:otherwise>
														<td><p>未知</p></td>
													</c:otherwise>
												</c:choose>
											</tr>
											<tr>
												<td class="label">菜系</td>
												<td><p>${goods.goods_cuisine}</p></td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="commerce-tabs tabs classic">
									<ul class="nav nav-tabs tabs">
										<li class="active">
											<a data-toggle="tab" href="#tab-description" aria-expanded="true">描述</a>
										</li>
										<li class="">
											<a data-toggle="tab" href="#tab-reviews" aria-expanded="false">评论</a>
										</li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade active in" id="tab-description">
											<p>
												${goods.goods_desc}...........
											</p>
										</div>
										<div id="tab-reviews" class="tab-pane fade">
											<div class="single-comments-list mt-0">
												<div class="mb-2">
													<h2 class="comment-title">${commentNum}条评论</h2>
												</div>
												<ul class="comment-list">
													<c:forEach var="list" items="${shopComment}" varStatus="num">
														<li>
															<div class="comment-container">
																<div class="comment-author-vcard">
																	<img alt="" src="<%=basePath%>/resources/images/avatar/avatar.png" />
																</div>
																<div class="comment-author-info">
																	<span class="comment-author-name">${userComment[num.count-1].user_name}</span>
																	<a href="#" class="comment-date">${list.shopTime}</a>
																	<p>${list.content}</p>
																</div>
																<div class="reply">
																	<a class="comment-reply-link" onclick="remind()">回复</a>
																</div>
															</div>
														</li>
													</c:forEach>
												</ul>
											</div>
											<div class="single-comment-form mt-0">
												<div class="mb-2">
													<h2 class="comment-title">购买后前往<a href="<%=basePath%>/pkt/personal" style="cursor: pointer">个人中心</a>进行评论</h2>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="related">
									<div class="related-title">
										<div class="text-center mb-1 section-pretitle fz-34">相关</div>
										<h2 class="text-center section-title mtn-2 fz-24">商品</h2>
									</div>
									<div class="product-carousel p-0" data-auto-play="true" data-desktop="3" data-laptop="2" data-tablet="2" data-mobile="1">

										<c:forEach items="${aboutResult}" var="item">
											<div class="product-item text-center">
												<div class="product-thumb"  style="width: 237px;height: 150px;">
													<a  class="${item.goods.goods_id}" onclick="toShop(this)" style="cursor:pointer;">
														<div class="badges">
															<c:choose>
																<c:when test="${item.hotSale==true}">
																	<span class="hot">热卖</span>
																</c:when>
															</c:choose>
														</div>
														<img src="http://123.207.250.128:8888/${item.list.get(0)}" style="width: 237px;height: 150px;"/>
													</a>
												</div>
												<div class="product-info">
													<a  class="${item.goods.goods_id}" onclick="toShop(this)" style="cursor:pointer;">
														<h2 class="title">${item.goods.goods_name}</h2>
														<span class="price">
														<c:choose>
															<c:when test="${item.promotion==true}">
																<del>¥ ${item.goods.goods_price}</del>
																<ins>¥ ${item.goods.goods_price_now}</ins>
															</c:when>
															<c:otherwise>
																<ins>¥ ${item.goods.goods_price_now}</ins>
															</c:otherwise>
														</c:choose>
														</span>
													</a>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-md-pull-9">
						<div class="sidebar">
							<div class="widget widget-product-categories">
								<h3 class="widget-title">商品分类</h3>
								<ul class="product-categories">
									<li><a href="<%=basePath%>/pkt/shop/1/1/6">荤菜</a> <span class="count">${number.meatNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/2/1/6">汤类</a> <span class="count">${number.soupNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/3/1/6">小炒</a> <span class="count">${number.starchNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/4/1/6">火锅</a> <span class="count">${number.hotPotNum}</span></li>
								</ul>
							</div>
							<div class="widget widget-products">
								<h3 class="widget-title">商品</h3>
								<ul class="product-list-widget">
									<c:forEach var="item" items="${otherResult}">
										<li>
											<a onclick="toShop(this)" class="${item.goods.goods_id}" style="cursor: pointer">
												<img src="http://123.207.250.128:8888/${item.list.get(0)}" alt="" style="width: 80px;height: 80px;"/>
												<span class="product-title">${item.goods.goods_name}</span>
											</a>
											<c:choose>
												<c:when test="${item.promotion==true}">
													<del>¥ ${item.goods.goods_price}</del>
													<ins>¥ ${item.goods.goods_price_now}</ins>
												</c:when>
												<c:otherwise>
													<ins>¥ ${item.goods.goods_price_now}</ins>
												</c:otherwise>
											</c:choose>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="widget widget-tags">
								<h3 class="widget-title">商品标签</h3>
								<div class="tagcloud">
									<a href="#">早餐</a> <a href="#">食物</a> <a href="#">水果</a> <a href="#">绿色</a> <a href="#">健康</a> <a href="#">自然</a> <a href="#">有机物</a> <a href="#">蔬菜</a>
								</div>
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
							<li><a href="<%=basePath%>/pkt/about">关于我们</a></li>
							<li><a href="#">我们的团队</a></li>
							<li><a href="<%=basePath%>/pkt/about">关于我们</a></li>
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
					Copyright &copy; 2018.Test name liujun.
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
<script type='text/javascript' src='<%=basePath%>/resources/js/slick.min.js'></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/cartShop.js"></script>
</body>
</html>