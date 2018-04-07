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
<title>个人中心</title>
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
<link rel="stylesheet" href="<%=basePath%>/resources/mycss/sys/style.css" type="text/css">

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
												<span class="quantity"><p style="display: inline-block;">${mymap.value.num}</p> × ￥<p style="display: inline-block;" class="price">${mymap.value.price}</p></span>
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
		<div class="section pt-7 pb-7">
			<div class="container">
				<div class="personal">
					<p class="title">个人中心</p>
					<ul class="per_nav">
						<li class="active">基本信息</li>
						<li>待付款</li>
						<li>待发货</li>
						<li>待收货</li>
						<li>历史订单</li>
						<li>我的优惠卷</li>
						<li>密码修改</li>
					</ul>
					<ul class="per_nav2">
						<li class="active">订单</li>
						<li>信息</li>
						<li>优惠卷</li>
						<li>留言</li>
					</ul>
					<div class="per_box per_info">
							<p>
								<span>昵称</span>
								<input type="text" disabled="disabled"  value="${userName}"/>
							</p>
							<p>
								<span>邮箱地址</span>
								<input type="email" disabled="disabled"  value="${userEmail}"/>
							</p>
							<p>
								<span>手机号码</span>
								<input type="tel" disabled="disabled"  value="${userPhone}" />
							</p>
					</div>

					<%--待付款--%>
					<div class="per_box"  style="display: none;">
						<ul class="per_navul">
							<li style="width: 20%">
								图片
							</li>
							<li style="width: 20%">名称</li>
							<li style="width: 20%">数量*价格</li>
							<li style="width: 20%">合计</li>
							<li style="width: 20%">状态</li>
						</ul>
						<c:forEach items="${cartLists}" var="mymap" varStatus="num">
							<ul class="per_listul">
								<li style="width: 20%">
									<img src="http://123.207.250.128:8888/${mymap.value.imgs}" style="vertical-align: middle;height: 80px;width: 80px"/>
								</li>
								<li style="width: 20%">${mymap.value.name}</li>
								<li style="width: 20%">${mymap.value.num} * ¥${mymap.value.price}</li>
								<li style="width: 20%">¥${priceLists[num.count-1]}</li>
								<li style="width: 20%"><a href="<%=basePath%>/pkt/cart" style="cursor: pointer">待付款</a></li>
							</ul>
						</c:forEach>
					</div>

					<%--待发货--%>
					<div class="per_box"  style="display: none;">
						<ul class="per_navul">
							<li style="width: 20%">
								图片
							</li>
							<li style="width: 20%">名称</li>
							<li style="width: 20%">数量*价格</li>
							<li style="width: 20%">合计</li>
							<li style="width: 20%">时间</li>
						</ul>
						<c:forEach items="${sendCart}" var="sendCart" varStatus="num">
							<ul class="per_listul">
								<li style="width: 20%">
									<img src="http://123.207.250.128:8888/${sendCart.imgs}" style="vertical-align: middle;height: 80px;width: 80px"/>
								</li>
								<li style="width: 20%">${sendCart.name}</li>
								<li style="width: 20%">${sendCart.num}  *  ¥${sendCart.price}</li>
								<li style="width: 20%">¥${sendCart.totalPrice}</li>
								<li style="width: 20%">${sendCart.time}</li>
							</ul>
						</c:forEach>
					</div>

					<%--待收货--%>
					<div class="per_box"  style="display: none;">
						<ul class="per_navul">
							<li style="width: 15%">
								图片
							</li>
							<li style="width: 15%">名称</li>
							<li style="width: 15%">数量*价格</li>
							<li style="width: 15%">合计</li>
							<li style="width: 15%">时间</li>
							<li style="width: 12%">查看</li>
							<li style="width: 12%">操作</li>
						</ul>
						<c:forEach items="${waitCart}" var="waitCart" varStatus="num">
							<ul class="per_listul">
								<li style="width: 15%">
									<img src="http://123.207.250.128:8888/${waitCart.imgs}" style="vertical-align: middle;height: 80px;width: 80px"/>
								</li>
								<li style="width: 15%">${waitCart.name}</li>
								<li style="width: 15%">${waitCart.num}  *  ¥${waitCart.price}</li>
								<li style="width: 15%">¥${waitCart.totalPrice}</li>
								<li style="width: 15%">${waitCart.time}</li>
								<li style="width: 12%">
									<button type="button" gid="${waitCart.com_id}" onclick="checkMap(this)" class="btn btn-primary">正在配送中</button>
								</li>
								<li style="width: 12%">
									<button type="button" gid="${waitCart.com_id}" onclick="confirmReceipt(this)" class="btn btn-primary">确认收货</button>
								</li>
							</ul>
						</c:forEach>
					</div>

					<%--历史订单--%>
					<div class="per_box" style="display: none;width: 80%;margin-left: 5%;">
						<ul class="layui-timeline" >
							<ul class="per_navul">
								<li style="width: 18%">
									图片
								</li>
								<li style="width: 16%">名称</li>
								<li style="width: 16%">数量*价格</li>
								<li style="width: 16%">合计</li>
								<li style="width: 16%">时间</li>
								<li style="width: 18%">评论</li>
							</ul>
							<c:forEach items="${histCart}" var="hisList" varStatus="num">
								<ul class="per_listul">
									<li style="width: 18%">
										<img src="http://123.207.250.128:8888/${hisList.imgs}" style="vertical-align: middle;height: 80px;width: 80px"/>
									</li>
									<li style="width: 16%">${hisList.name}</li>
									<li style="width: 16%">${hisList.num}  *  ¥${hisList.price}</li>
									<li style="width: 16%">¥${hisList.totalPrice}</li>
									<li style="width: 16%">${hisList.time}</li>
									<li style="width: 18%">
										<c:choose>
											<c:when test="${hisList.content==null}">
												<p class="popover-options" style="margin-top: 14px">
													<a  onclick="closePop(this)" type="button" class="btn btn-success" title="<h4>添加评论</h4>"
														data-container="body" data-toggle="popover"
														data-content="<input type='text' tid='${hisList.id}' cid='${hisList.com_id}' class='form-control' style='width:248px;height:146px'>
											            <button class='btn btn-info btn-sm' onclick='getComment(this)' style='margin: 5 32%;'>确定添加</button>">
														添加评论
													</a>
												</p>
											</c:when>
											<c:when test="${hisList.content!=null}">
												<p class="popover-options" style="margin-top: 14px">
													<a  onclick="closePop(this)" type="button" class="btn btn-success" title="<h4>查看评论</h4>"
														data-container="body" data-toggle="popover"
														data-content="<input type='text' value='${hisList.content}' class='form-control' style='width:248px;height:146px'>">
														查看评论
													</a>
												</p>
											</c:when>
										</c:choose>
									</li>
								</ul>
							</c:forEach>
						</ul>
					</div>
					<div class="per_box per_coupon" style="display: none;">
						<ul class="per_couponul">
							<li class="active">未使用(4)</li>
							<li>已使用(1)</li>
							<li>已过期(2)</li>
						</ul>
						<div class="per_coupon_box">
							<div class="per_coupon_list">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-3-11——2018-7-22</p>
							</div>
							<div class="per_coupon_list">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-3-11——2018-7-22</p>
							</div>
							<div class="per_coupon_list">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-3-11——2018-7-22</p>
							</div>
							<div class="per_coupon_list">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-3-11——2018-7-22</p>
							</div>
						</div>
						<div class="per_coupon_box" style="display: none;">
							<div class="per_coupon_list per_coupon_old">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2017-11-11——2018-2-22</p>
							</div>
						</div>
						<div class="per_coupon_box" style="display: none;">
							<div class="per_coupon_list per_coupon_old">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-1-11——2018-2-22</p>
							</div>
							<div class="per_coupon_list per_coupon_old">
								<p>￥40</p>
								<p>【消费满200元可用】</p>
								<p>2018-1-11——2018-2-22</p>
							</div>
						</div>
					</div>
					<div class="per_box per_info" style="display: none;">
						<form action="" method="post">
							<p>
								<span>旧密码</span>
								<input type="password" id="oldPass"/>
							</p>
							<p>
								<span>新密码</span>
								<input type="password" id="newPass"/>
							</p>
							<p>
								<span>确认新密码</span>
								<input type="password" id="repeatPass"/>
							</p>
							<p class="per_info_btn" onclick="changePass()">保存</p>
						</form>
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
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/personal.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/cartShop.js"></script>
</body>
</html>