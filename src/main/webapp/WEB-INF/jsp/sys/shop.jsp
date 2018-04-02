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
<title>Shop</title>

<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/font-awesome.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/ionicons.min.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.carousel.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/owl.theme.css" type="text/css" media="all">
<link rel='stylesheet' href='<%=basePath%>/resources/css/prettyPhoto.css' type='text/css' media='all'>
<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/css/custom.css" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>/resources/layui/css/layui.css" type="text/css" media="all">
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
							<li>列表</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="section pt-7 pb-7">
			<div class="container">
				<div class="row">
					<div class="col-md-9 col-md-push-3">
						<div class="shop-filter">
							<div class="col-md-6">
								<p class="result-count"> 共${number.allNum}个结果</p>
							</div>
						</div>
						<div class="category-carousel-2 mb-3" data-auto-play="true" data-desktop="3" data-laptop="3" data-tablet="2" data-mobile="1">
							<div class="cat-item">
								<div class="cats-wrap" data-bg-color="#f8c9c2">
									<a href="<%=basePath%>/pkt/shop/1/1/6">
										<img src="<%=basePath%>/resources/myImgs/shop/meat.png" alt="" />
										<h2 class="category-title"> 
											荤菜 <mark class="count">${number.meatNum}</mark>
										</h2>
									</a>
								</div>
							</div>
							<div class="cat-item">
								<div class="cats-wrap" data-bg-color="#ebd3c3">
									<a href="<%=basePath%>/pkt/shop/2/1/6">
										<img src="<%=basePath%>/resources/myImgs/shop/soup.png" alt="" />
										<h2 class="category-title"> 
											汤类 <mark class="count">${number.soupNum}</mark>
										</h2>
									</a>
								</div>
							</div>
							<div class="cat-item">
								<div class="cats-wrap" data-bg-color="#c6e6f6">
									<a href="<%=basePath%>/pkt/shop/3/1/6">
										<img src="<%=basePath%>/resources/myImgs/shop/starch.png" alt="" />
										<h2 class="category-title"> 
											小炒 <mark class="count">${number.starchNum}</mark>
										</h2>
									</a>
								</div>
							</div>
							<div class="cat-item">
								<div class="cats-wrap" data-bg-color="#e0d1a1">
									<a href="<%=basePath%>/pkt/shop/4/1/6">
										<img src="<%=basePath%>/resources/myImgs/shop/hotpot.png" alt="" />
										<h2 class="category-title"> 
											火锅 <mark class="count">${number.hotPotNum}</mark>
										</h2>
									</a>
								</div>
							</div>
						</div>
						<div class="product-grid" id="goods_grid">
							<c:forEach var="item" items="${result}">
								<div class="col-md-4 col-sm-6 product-item text-center mb-3">
									<div class="product-thumb" style="width: 255px;height: 255px;">
										<a onclick="toShop(this)" class="${item.goods.goods_id}">
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
											<a style="cursor:pointer;" onclick="shopCart(this)" class="${item.goods.goods_id}" style="cursor:pointer;" data-toggle="tooltip" data-placement="top" title="购物车"></a>
										</span>
										<span class="wishlist">
											<a href="#" data-toggle="tooltip" data-placement="top" title="收藏"></a>
										</span>
										</div>
									</div>
									<div class="product-info">
										<a onclick="toShop(this)" class="${item.goods.goods_id}">
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
						<div id="page" style="padding:3% 15% 0 15%;"></div>
					</div>
					<div class="col-md-3 col-md-pull-9">
						<div class="sidebar">
							<div class="widget widget-product-categories">
								<h3 class="widget-title">Product Categories</h3>
								<ul class="product-categories">
									<li><a href="<%=basePath%>/pkt/shop/1/1/6">荤菜</a> <span class="count">${number.meatNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/2/1/6">汤类</a> <span class="count">${number.soupNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/3/1/6">小炒</a> <span class="count">${number.starchNum}</span></li>
									<li><a href="<%=basePath%>/pkt/shop/4/1/6">火锅</a> <span class="count">${number.hotPotNum}</span></li>
								</ul>
							</div>
							<div class="widget widget-products">
								<h3 class="widget-title">其他产品</h3>
								<ul class="product-list-widget">
									<c:forEach var="item" items="${otherResultList}">
										<li>
											<a onclick="toShop(this)" class="${item.goods.goods_id}">
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
							<li><a href="<%=basePath%>/pkt/shop">热卖榜</a></li>
							<li><a href="<%=basePath%>/pkt/cart">我的购物车</a></li>
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
<script type="text/javascript" src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/core.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/widget.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/mouse.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/slider.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.ui.touch-punch.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/price-slider.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/cartShop.js"></script>
</body>

<script type="text/javascript">


	function getCount() {
		return ${number.allNum};
    }
    layui.use(['laypage', 'layer'], function() {
        var laypage = layui.laypage, layer = layui.layer;
        var pathName = window.location.pathname;
        var curr = pathName.split("/")[4];
        var limit = pathName.split("/")[5];
        laypage.render({
            elem: 'page'
            ,count:getCount()
			,curr:curr==undefined?1:curr
            ,limit:limit==undefined?6:limit
            ,limits: [6, 12, 18, 24]
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
            ,jump: function(obj,first){
                var path = window.location.pathname;
                var urlList = path.split("/");
                var type,page,limit;
                if (!first){
                    if (urlList.length>3){
						type = urlList[3];
						page = obj["curr"];
						limit = obj["limit"];
                    }else {
                        type = 5;
                        page = obj["curr"];
                        limit = obj["limit"];
                    }
                    if ((limit*(page-1))>getCount()){
                        var url = "pkt/shop/"+type+"/1/"+limit;
                        $(location).prop('href', url);
					}else {
                        var url = "pkt/shop/"+type+"/"+page+"/"+limit;
                        $(location).prop('href', url);
                    }
				}
            }
        });
    });


</script>
</html>