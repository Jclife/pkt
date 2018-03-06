<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html>
<head>
	<title>登录/注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=basePath%>/resources/mycss/style.css">
	<link href="<%=basePath%>/resources/mycss/popup-box.css" rel="stylesheet" type="text/css" media="all" />
	<link href="<%=basePath%>/resources/js/layer/skin/layer.css" rel="stylesheet" type="text/css" media="all" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<script src="<%=basePath%>/resources/js/jquery.min.js"></script>
	<script src="<%=basePath%>/resources/myJs/sys/jquery.magnific-popup.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/modernizr.custom.53451.js"></script>
</head>
<body>
	<h1>PKT登录页面</h1>
	<div class="w3layouts">
		<div class="signin-agile">
			<h2>登录</h2>
			<input type="text" name="name" class="name" placeholder="账号" id="login_phone">
			<input type="password" name="password" class="password" placeholder="密码" id="login_password">
			<div class="clear"></div>
			<input type="submit" value="登录" onclick="click_login()">
		</div>
		<div class="signup-agileinfo">
			<h3>关于PKT</h3>
			<p>PKT是一个社区私厨平台，致力于提供优质卫生的新鲜高端外卖服务，让用户放心是我们的追求</p>
			<div class="more">
				<a class="book popup-with-zoom-anim button-isi zoomIn animated" data-wow-delay=".5s" href="#small-dialog">点击注册</a>				
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="footer-w3l">
		<p class="agileinfo"> 2018@copy Liujun</p>
	</div>
	<div class="pop-up"> 
	<div id="small-dialog" class="mfp-hide book-form">
		<h3>注册</h3>
		<input type="text" name="Phone" placeholder="手机号" id="register_phone"/>
		<input type="text" name="Name" placeholder="用户名" id="register_name"/>
		<input type="text" name="Email" class="email" placeholder="邮件" id="register_email"/>
		<input type="password" name="Password" class="password" placeholder="密码" id="register_password"/>
		<input type="password" name="Password" class="password" placeholder="重复密码" id="repeat_password"/>
		<input type="submit" value="点击注册" onclick="click_register()">
	</div>
	</div>
</body>
<script src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script>
    addEventListener("load", function() {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar(){
        window.scrollTo(0,1);
    }
    $(document).ready(function() {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in'
        });

    });
</script>
<script type="text/javascript" src="<%=basePath%>/resources/myJs/sys/login.js"></script>
</html>