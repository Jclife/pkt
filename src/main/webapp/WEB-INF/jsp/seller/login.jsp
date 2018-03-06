<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="网站关键词">
    <meta name="Description" content="网站介绍">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/base.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/iconfont.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/reg.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css">
</head>
<body>
<div id="ajax-hook"></div>
<div class="wrap">
    <div class="wpn">
        <div class="form-data pos">
            <div class="change-login">
                <p class="account_number on">账号登录</p>
            </div>
            <div class="form1">
                <p class="p-input pos">
                    <label for="account">请输入账号</label>
                    <input type="text" id="account">
                </p>
                <p class="p-input pos">
                    <label for="password">请输入密码</label>
                    <input type="password" id="password"/>
                </p>
            </div>
            <div class="r-forget cl">
                <a href="<%=basePath%>/seller/register" class="z">账号注册</a>
                <a href="<%=basePath%>/seller/forget_pass" class="y">忘记密码</a>
            </div>
            <button class="lang-btn off log-btn" onclick="login()">登录</button>
            <div class="third-party">
                <a href="#" class="log-qq icon-qq-round"></a>
                <a href="#" class="log-qq icon-weixin"></a>
                <a href="#" class="log-qq icon-sina1"></a>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath%>/resources/backLoginRe/js/jquery.js"></script>
<script src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script src="<%=basePath%>/resources/backLoginRe/js/agree.js"></script>
<script src="<%=basePath%>/resources/myJs/seller/login.js"></script>
</body>
</html>