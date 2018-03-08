<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<title>Home</title>
<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/font-awesome.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/ionicons.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.carousel.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.theme.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/settings.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/custom.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/mycss/pkt-index.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/layui/css/layui.css" type="text/css" media="all">
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>

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
									<li><a href="<%=basePath%>/seller/login">卖家版</a></li>
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
									<li><a href="<%=basePath%>/seller/login">卖家版</a></li>
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
						<form>
							<input type="search" class="top-search-input" name="s" placeholder="想吃点什么?请输入..." />
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
		<%--images--%>
		<div class="section">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-12 p-0">
						<div id="rev_slider" class="rev_slider fullscreenbanner">
							<ul>
								<li data-transition="fade" data-slotamount="default" data-hideafterloop="0" data-hideslideonmobile="off" data-easein="default" data-easeout="default" data-masterspeed="300" data-rotate="0" data-saveperformance="off" data-title="换页">
									<img src="<%=basePath%>/resources/images/slider/slide_bg_4.jpg" alt="" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat" data-bgparallax="off" class="rev-slidebg" />
									<div class="tp-caption rs-parallaxlevel-2"
										 data-x="center" data-hoffset=""
										 data-y="center" data-voffset="-80"
										 data-width="['none','none','none','none']"
										 data-height="['none','none','none','none']"
										 data-type="image" data-responsive_offset="on"
										 data-frames='[{"delay":0,"speed":300,"frame":"0","from":"opacity:0;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"opacity:0;","ease":"Power3.easeInOut"}]'
										 data-textAlign="['inherit','inherit','inherit','inherit']"
										 data-paddingtop="[0,0,0,0]" data-paddingright="[0,0,0,0]"
										 data-paddingbottom="[0,0,0,0]"
										 data-paddingleft="[0,0,0,0]">
											<img src="<%=basePath%>/resources/images/slider/slide_6.png" alt="" />
									</div>
									<div class="tp-caption rs-parallaxlevel-1"
										 data-x="center" data-hoffset=""
										 data-y="center" data-voffset="-80"
										 data-width="['none','none','none','none']"
										 data-height="['none','none','none','none']"
										 data-type="image" data-responsive_offset="on"
										 data-frames='[{"delay":0,"speed":300,"frame":"0","from":"opacity:0;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"opacity:0;","ease":"Power3.easeInOut"}]'
										 data-textAlign="['inherit','inherit','inherit','inherit']"
										 data-paddingtop="[0,0,0,0]" data-paddingright="[0,0,0,0]"
										 data-paddingbottom="[0,0,0,0]" data-paddingleft="[0,0,0,0]">
											<img src="<%=basePath%>/resources/images/slider/slide_7.png" alt="" />
									</div>
									<a class="tp-caption btn-2 hidden-xs" href="<%=basePath%>/pkt/shop"
										 data-x="['center','center','center','center']" 
										 data-y="['center','center','center','center']" data-voffset="['260','260','260','260']"
										 data-width="['auto']" data-height="['auto']"
										 data-type="button" data-responsive_offset="on"
										 data-responsive="off"
										 data-frames='[{"delay":0,"speed":300,"frame":"0","from":"opacity:0;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"opacity:0;","ease":"Power3.easeInOut"},{"frame":"hover","speed":"300","ease":"Power0.easeIn","to":"o:1;rX:0;rY:0;rZ:0;z:0;","style":"c:rgb(95,189,116);bg:rgba(51, 51, 51, 0);"}]'
										 data-textAlign="['inherit','inherit','inherit','inherit']"
										 data-paddingtop="[16,16,16,16]" data-paddingright="[30,30,30,30]"
										 data-paddingbottom="[16,16,16,16]" data-paddingleft="[30,30,30,30]">开始购物
									</a>
								</li>
								<li data-transition="fade" data-slotamount="default" data-hideafterloop="0" data-hideslideonmobile="off" data-easein="default" data-easeout="default" data-masterspeed="300" data-rotate="0" data-saveperformance="off" data-title="换页">
									<img src="<%=basePath%>/resources/images/slider/slide_bg_5.jpg" alt="" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat" data-bgparallax="off" class="rev-slidebg" />
									<div class="tp-caption rs-parallaxlevel-1"
										 data-x="center" data-hoffset=""
										 data-y="center" data-voffset="-120"
										 data-width="['none','none','none','none']"
										 data-height="['none','none','none','none']"
										 data-type="image" data-responsive_offset="on"
										 data-frames='[{"delay":0,"speed":1500,"frame":"0","from":"z:0;rX:0;rY:0;rZ:0;sX:0.9;sY:0.9;skX:0;skY:0;opacity:0;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"opacity:0;","ease":"Power3.easeInOut"}]'
										 data-textAlign="['inherit','inherit','inherit','inherit']"
										 data-paddingtop="[0,0,0,0]" data-paddingright="[0,0,0,0]"
										 data-paddingbottom="[0,0,0,0]" data-paddingleft="[0,0,0,0]">
											<img src="<%=basePath%>/resources/images/slider/slide_8.png" alt="" />
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%--all produces--%>
		<div class="section pt-12 pb-9">
				<div class="container">
					<div class="row">
						<div class="col-sm-12">
							<div class="text-center mb-1 section-pretitle">发现</div>
							<h2 class="text-center section-title mtn-2">更多产品</h2>
							<div class="organik-seperator center">
								<span class="sep-holder"><span class="sep-line"></span></span>
								<div class="sep-icon"><i class="organik-flower"></i></div>
								<span class="sep-holder"><span class="sep-line"></span></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 p-0">
							<div class="text-center">
								<ul class="masonry-filter">
									<li><a href="#" data-filter="" class="active">所有</a></li>
									<li><a href="#" data-filter=".meat">荤菜</a></li>
									<li><a href="#" data-filter=".soup">汤类</a></li>
									<li><a href="#" data-filter=".starch">小炒</a></li>
									<li><a href="#" data-filter=".hotPot">火锅</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="product-grid masonry-grid-post">
							<c:forEach var="item" items="${result}">
								<c:choose>
									<c:when test="${item.goods.goods_classify==1}">
										<div class="col-md-3 col-sm-6 product-item masonry-item text-center meat">
									</c:when>
									<c:when test="${item.goods.goods_classify==2}">
										<div class="col-md-3 col-sm-6 product-item masonry-item text-center soup">
									</c:when>
									<c:when test="${item.goods.goods_classify==3}">
										<div class="col-md-3 col-sm-6 product-item masonry-item text-center starch">
									</c:when>
									<c:when test="${item.goods.goods_classify==4}">
										<div class="col-md-3 col-sm-6 product-item masonry-item text-center hotPot">
									</c:when>
								</c:choose>
								<div class="product-thumb" style="height: 265px;width: 265px">
									<a  onclick="toShop(this)" style="cursor: pointer" class="${item.goods.goods_id}">
										<div class="badges">
											<c:choose>
												<c:when test="${item.hotSale==true}">
													<span class="hot">热卖</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${item.promotion==true}">
													<span class="onsale">促销</span>
												</c:when>
											</c:choose>
										</div>
										<img src="http://123.207.250.128:8888/${item.list.get(0)}" alt="" />
									</a>
									<div class="product-action">
									<span class="add-to-cart">
										<a onclick="shopCart(this)" class="${item.goods.goods_id}" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="购物车"></a>
									</span>
										<span class="wishlist">
										<a href="#" data-toggle="tooltip" data-placement="top" title="收藏"></a>
									</span>
									</div>
								</div>
								<div class="product-info">
									<a  onclick="toShop(this)" style="cursor: pointer" class="${item.goods.goods_id}">
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
						<div class="loadmore-contain">
							<a class="organik-btn small" href="<%=basePath%>/pkt/shop"> 查看更多 </a>
						</div>
					</div>
				</div>
			</div>
					</div>

		<%--hot seal--%>
		<div class="section section-bg-2 section-cover pt-14">
				<div class="container">
					<div class="row">
						<div class="col-sm-6">
							<div class="text-center">
								<div class="mb-1 section-pretitle white">热卖促销商品</div>
								<h2 class="section-title mtn-2 mb-3">来自自然的馈赠</h2>
								<p class="white mb-4">
									这是热卖促销专栏
								</p>
								<div class="countdown-wrap mb-4">
									<div class="countdown-content">
										<div class="pl-clock countdown-bar" data-time="2018/5/18"></div>
									</div>
								</div>
								<a class="organik-btn brown"  href="<%=basePath%>/pkt/shopDetail/${result.get(0).goods.goods_id}">剁手</a>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="text-center floating">
								<div class="product-thumb">
									<div class="badges">
										<span class="hotSale">¥${result.get(0).goods.goods_price_now}</span>
										<%--<img src="<%=basePath%>/resources/images/can.png" alt="" />--%>
										<img src="http://123.207.250.128:8888/${result.get(0).list.get(0)}" alt="" />
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

		<%--about us--%>
		<div class="section section-bg-3 pt-6 pb-6">
				<div class="container">
					<div class="row">
						<div class="col-sm-12">
							<div class="text-center mb-1 section-pretitle">我们专注于</div>
							<h2 class="text-center section-title mtn-2">专业的社区私厨平台</h2>
							<div class="organik-seperator mb-9 center">
								<span class="sep-holder"><span class="sep-line"></span></span>
								<div class="sep-icon"><i class="organik-flower"></i></div>
								<span class="sep-holder"><span class="sep-line"></span></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="organik-special-title">
								<div class="number">01</div>
								<div class="title">永远新鲜</div>
							</div>
							<p>保证新鲜的食物</p>
							<div class="mb-8"></div>
							<div class="organik-special-title">
								<div class="number">02</div>
								<div class="title">永远健康</div>
							</div>
							<p>使用无添加无污染食物</p>
						</div>
						<div class="col-md-4">
							<div class="mb-8"></div>
						</div>
						<div class="col-md-4">
							<div class="organik-special-title align-right">
								<div class="number">03</div>
								<div class="title" style="margin-left: 100px;">食物可溯源</div>
							</div>
							<p>每一份食物都可以找到源头</p>
							<div class="mb-8"></div>
							<div class="organik-special-title align-right">
								<div class="number">04</div>
								<div class="title">速度保证</div>
							</div>
							<p>半小时内吃到家的味道</p>
						</div>
					</div>
				</div>
			</div>

		<%--Our goal--%>
		<div class="section border-bottom mt-6 mb-5">
				<div class="container">
					<div class="row">
						<div class="organik-process">
							<div class="col-md-3 col-sm-6 organik-process-small-icon-step">
								<div class="icon">
									<i class="organik-delivery"></i>
								</div>
								<div class="content">
									<div class="title">免费送货</div>
									<div class="text">订单超过50￥</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 organik-process-small-icon-step">
								<div class="icon">
									<i class="organik-hours-support"></i>
								</div>
								<div class="content">
									<div class="title">客户支持</div>
									<div class="text">一周7天24小时随时待命</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 organik-process-small-icon-step">
								<div class="icon">
									<i class="organik-credit-card"></i>
								</div>
								<div class="content">
									<div class="title">安全支付</div>
									<div class="text">支持支付宝微信</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 organik-process-small-icon-step">
								<div class="icon">
									<i class="organik-lettuce"></i>
								</div>
								<div class="content">
									<div class="title">用心制造</div>
									<div class="text">选取最优食材</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		<%--why choose us--%>
		<div class="section bg-light pt-10 pb-10">
				<div class="container">
					<div class="row">
						<div class="col-sm-12">
							<div class="text-center mb-1 section-pretitle">为什么选择我们</div>
							<h2 class="text-center section-title mtn-2">更专业的社区私厨平台?</h2>
							<div class="organik-seperator center mb-6">
								<span class="sep-holder"><span class="sep-line"></span></span>
								<div class="sep-icon"><i class="organik-flower"></i></div>
								<span class="sep-holder"><span class="sep-line"></span></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="accordion icon-left" id="accordion1">
								<div class="accordion-group toggle">
									<div class="accordion-heading toggle_title active">
										<a class="accordion-toggle" data-toggle="collapse" aria-expanded="true" aria-controls="collapse1" href="<%=basePath%>/resources#collapse1">
											品质至上
										</a>
										<span class="icon"><i class="ion-heart"></i></span>
									</div>
									<div id="collapse1" class="accordion-body collapse in">
										<div class="accordion-inner">
											<p>
												品质放心，有最好的食物来源，每一份食物都可以溯源
											</p>
										</div>
									</div>
								</div>
								<div class="accordion-group toggle">
									<div class="accordion-heading toggle_title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="collapse2" href="<%=basePath%>/resources#collapse2">
											绿色健康
										</a>
										<span class="icon"><i class="ion-chatboxes"></i></span>
									</div>
									<div id="collapse2" class="accordion-body toggle_content collapse">
										<div class="accordion-inner">
											<p>
												保证所有食材全部纯天然，无任何添加
											</p>
										</div>
									</div>
								</div>
								<div class="accordion-group toggle">
									<div class="accordion-heading toggle_title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="collapse3" href="<%=basePath%>/resources#collapse3">
											多样性
										</a>
										<span class="icon"><i class="ion-lightbulb"></i></span>
									</div>
									<div id="collapse3" class="accordion-body toggle_content collapse">
										<div class="accordion-inner">
											<p>
												每天更新上百种新鲜食物，总能找到你要的
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="text-center app-desc floating">
								<img src="<%=basePath%>/resources/images/app-desc.png" alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>

		<%--logos--%>
		<div class="section pt-2 pb-4">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="client-carousel" data-auto-play="true" data-desktop="5" data-laptop="3" data-tablet="3" data-mobile="2">
							<div class="client-item">
								<a href="#" target="_blank">
									<img src="<%=basePath%>/resources/images/client/client_1.png" alt="" />
								</a>
							</div>
							<div class="client-item">
								<a href="#" target="_blank">
									<img src="<%=basePath%>/resources/images/client/client_2.png" alt="" />
								</a>
							</div>
							<div class="client-item">
								<a href="#" target="_blank">
									<img src="<%=basePath%>/resources/images/client/client_3.png" alt="" />
								</a>
							</div>
							<div class="client-item">
								<a href="#" target="_blank">
									<img src="<%=basePath%>/resources/images/client/client_4.png" alt="" />
								</a>
							</div>
							<div class="client-item">
								<a href="#" target="_blank">
									<img src="<%=basePath%>/resources/images/client/client_5.png" alt="" />
								</a>
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
<script type="text/javascript" src="<%=basePath%>/resources/js/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/isotope.pkgd.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.isotope.init.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.themepunch.tools.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.themepunch.revolution.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.video.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.slideanims.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.actions.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.kenburn.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.navigation.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.migration.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/extensions/revolution.extension.parallax.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/cartShop.js"></script>
</body>
</html>