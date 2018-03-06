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
    <title>找回密码</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="网站关键词">
    <meta name="Description" content="网站介绍">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/base.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/iconfont.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/reg.css">
</head>
<body>
<div id="ajax-hook"></div>
<div class="wrap">
    <div class="wpn">
        <div class="form-data find_password">
            <h4>找回密码</h4>
            <p class="right_now">已有账号，<a href="<%=basePath%>/seller/login">马上登录</a></p>
            <p class="p-input pos">
                <label for="pc_tel">手机号/邮箱</label>
                <input type="text" id="pc_tel">
                <span class="tel-warn pc_tel-err hide"><em>最多五个字</em><i class="icon-warn"></i></span>
            </p>
            <p class="p-input pos pc-very">
                <label for="veri-code">输入验证码</label>
                <input type="number" id="veri-code">
                <a href="javascript:;" class="send">发送验证码</a>
                <span class="time hide"><em>120</em>s</span>
                <span class="tel-warn error hide"><em>验证码错误，请重新输入</em><i class="icon-warn"></i></span>
            </p>
            <p class="p-input pos code pc-code">
                <label for="veri">请输入验证码</label>
                <input type="text" id="veri">
                <img src="">
                <span class="tel-warn img-err hide"><em>最多五个字</em><i class="icon-warn"></i></span>
                <!-- <a href="javascript:;">换一换</a> -->
            </p>
            <button class="lang-btn next">下一步</button>
        </div>
    </div>
</div>
<script src="<%=basePath%>/resources/backLoginRe/js/jquery.js"></script>
<script src="<%=basePath%>/resources/backLoginRe/js/agree.js"></script>
</body>
</html>